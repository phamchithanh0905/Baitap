package com.example.btvn.data.repository

import android.app.Activity
import com.example.btvn.model.AuthResponse
import com.example.btvn.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.FirebaseException // Import FirebaseException để xử lý lỗi Firebase cụ thể
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * FirebaseAuthRepository là một lớp Repository (kho lưu trữ) chịu trách nhiệm
 * xử lý tất cả các hoạt động liên quan đến xác thực người dùng bằng Firebase Authentication.
 * Nó hỗ trợ đăng nhập bằng Google, Email/Mật khẩu và Số điện thoại.
 *
 * @param firebaseAuth Thể hiện của FirebaseAuth được cung cấp thông qua Dependency Injection.
 */
@Singleton
class FirebaseAuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : IAuthRepository {

    // Biến lưu trữ ID xác minh nhận được khi mã SMS được gửi đi.
    // Cần thiết để xác minh mã OTP sau này.
    private var verificationId: String? = null

    // Biến lưu trữ token để có thể gửi lại mã xác minh SMS nếu cần.
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    // Một kênh (Channel) để gửi các phản hồi về trạng thái xác thực (Thành công, Lỗi, Đang tải).
    // Sử dụng Channel.BUFFERED để lưu trữ một số sự kiện nếu Flow chưa kịp thu thập.
    private val _authResponseChannel = Channel<AuthResponse>(Channel.BUFFERED)
    // Flow công khai để các thành phần khác có thể thu thập (collect) các trạng thái xác thực.
    val authResponseFlow: Flow<AuthResponse> = _authResponseChannel.receiveAsFlow()

    /**
     * Đăng nhập người dùng bằng mã ID của Google.
     *
     * @param idToken Mã ID được lấy từ quá trình đăng nhập Google.
     * @return Một đối tượng [AuthResponse] cho biết trạng thái thành công hay lỗi.
     */
    override suspend fun signInWithGoogle(idToken: String): AuthResponse {
        return try {
            // Tạo thông tin xác thực (credential) Google từ mã ID.
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            // Đăng nhập vào Firebase bằng thông tin xác thực Google này và chờ kết quả.
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                // Nếu đăng nhập thành công, tạo đối tượng User từ FirebaseUser.
                val user = User(
                    uid = firebaseUser.uid,
                    name = firebaseUser.displayName ?: "", // Lấy tên hiển thị, nếu không có thì để trống.
                    email = firebaseUser.email ?: "" // Lấy email, nếu không có thì để trống.
                )
                AuthResponse.Success(user) // Trả về trạng thái thành công với thông tin người dùng.
            } else {
                // Nếu người dùng null sau xác thực, tức là có vấn đề.
                AuthResponse.Error("Đăng nhập thất bại: Không tìm thấy người dùng sau xác thực.")
            }
        } catch (e: Exception) {
            // Bắt các ngoại lệ trong quá trình đăng nhập Google và trả về lỗi.
            AuthResponse.Error(e.localizedMessage ?: "Lỗi không xác định trong quá trình đăng nhập Google")
        }
    }

    /**
     * Trả về người dùng Firebase hiện tại đang đăng nhập.
     *
     * @return Đối tượng [FirebaseUser] hiện tại, hoặc `null` nếu không có người dùng nào đang đăng nhập.
     */
    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    /**
     * Đăng xuất người dùng hiện tại khỏi Firebase.
     */
    override fun signOut() {
        firebaseAuth.signOut()
    }

    /**
     * Cung cấp một Flow theo dõi các thay đổi trạng thái xác thực của FirebaseUser.
     * Flow này sẽ phát ra đối tượng [FirebaseUser] hiện tại mỗi khi trạng thái xác thực thay đổi.
     *
     * @return Một Flow phát ra [FirebaseUser] hiện tại (hoặc `null`) khi trạng thái auth thay đổi.
     */
    override fun getAuthStateChanges(): Flow<FirebaseUser?> = callbackFlow {
        // Tạo một AuthStateListener để lắng nghe các thay đổi trạng thái xác thực.
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser) // Gửi người dùng hiện tại vào Flow.
        }
        // Thêm listener vào Firebase Auth.
        firebaseAuth.addAuthStateListener(authStateListener)

        // awaitClose đảm bảo rằng listener sẽ được gỡ bỏ khi Flow không còn được thu thập,
        // giúp tránh rò rỉ bộ nhớ.
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }

    /**
     * Đăng ký một người dùng mới bằng email và mật khẩu.
     *
     * @param email Địa chỉ email của người dùng.
     * @param password Mật khẩu của người dùng.
     * @return Một đối tượng [AuthResponse] cho biết trạng thái thành công hay lỗi.
     */
    override suspend fun registerWithEmailPassword(email: String, password: String): AuthResponse {
        return try {
            // Tạo người dùng mới với email và mật khẩu và chờ kết quả.
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                // Nếu đăng ký thành công, tạo đối tượng User.
                val user = User(
                    uid = firebaseUser.uid,
                    name = firebaseUser.displayName ?: "",
                    email = firebaseUser.email ?: ""
                )
                AuthResponse.Success(user) // Trả về trạng thái thành công.
            } else {
                // Nếu người dùng null sau khi tạo, tức là có vấn đề.
                AuthResponse.Error("Đăng ký thất bại: Không tìm thấy người dùng sau khi tạo.")
            }
        } catch (e: Exception) {
            // Bắt các ngoại lệ trong quá trình đăng ký và trả về lỗi.
            AuthResponse.Error(e.localizedMessage ?: "Lỗi không xác định trong quá trình đăng ký")
        }
    }

    /**
     * Đăng nhập người dùng hiện có bằng email và mật khẩu.
     *
     * @param email Địa chỉ email của người dùng.
     * @param password Mật khẩu của người dùng.
     * @return Một đối tượng [AuthResponse] cho biết trạng thái thành công hay lỗi.
     */
    override suspend fun signInWithEmailPassword(email: String, password: String): AuthResponse {
        return try {
            // Đăng nhập với email và mật khẩu và chờ kết quả.
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                // Nếu đăng nhập thành công, tạo đối tượng User.
                val user = User(
                    uid = firebaseUser.uid,
                    name = firebaseUser.displayName ?: "",
                    email = firebaseUser.email ?: ""
                )
                AuthResponse.Success(user) // Trả về trạng thái thành công.
            } else {
                // Nếu người dùng null sau xác thực, tức là có vấn đề.
                AuthResponse.Error("Đăng nhập thất bại: Không tìm thấy người dùng sau xác thực.")
            }
        } catch (e: Exception) {
            // Bắt các ngoại lệ trong quá trình đăng nhập và trả về lỗi.
            AuthResponse.Error(e.localizedMessage ?: "Lỗi không xác định trong quá trình đăng nhập Email/Mật khẩu")
        }
    }

    /**
     * Bắt đầu quá trình xác minh số điện thoại bằng cách gửi mã SMS.
     *
     * @param activity Context của Activity cần thiết cho xác thực điện thoại.
     * @param phoneNumber Số điện thoại cần xác minh.
     */
    override suspend fun startPhoneVerification(activity: Activity, phoneNumber: String) {
        // Gửi trạng thái Loading vào kênh phản hồi để UI biết đang xử lý.
        _authResponseChannel.send(AuthResponse.Loading)

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            /**
             * Được gọi khi quá trình xác minh hoàn tất, thường là khi hệ thống tự động
             * truy xuất mã SMS thành công.
             *
             * @param credential Thông tin xác thực số điện thoại chứa số điện thoại và mã xác minh.
             */
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // Đăng nhập vào Firebase bằng thông tin xác thực đã cung cấp.
                firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser = task.result?.user
                            if (firebaseUser != null) {
                                // Nếu đăng nhập thành công, gửi trạng thái Success với thông tin người dùng.
                                _authResponseChannel.trySend(AuthResponse.Success(User(
                                    uid = firebaseUser.uid,
                                    name = firebaseUser.displayName ?: "",
                                    email = firebaseUser.email ?: ""
                                )))
                            } else {
                                // Nếu người dùng null sau khi tự động hoàn tất, gửi lỗi.
                                _authResponseChannel.trySend(AuthResponse.Error("Xác minh điện thoại thất bại: Người dùng null sau khi tự động hoàn tất."))
                            }
                        } else {
                            // Nếu đăng nhập thất bại, gửi lỗi.
                            _authResponseChannel.trySend(AuthResponse.Error(task.exception?.localizedMessage ?: "Xác minh điện thoại thất bại."))
                        }
                    }
            }

            /**
             * Được gọi khi quá trình xác minh thất bại (ví dụ: số điện thoại không hợp lệ, quá nhiều yêu cầu).
             *
             * @param e [FirebaseException] chi tiết lỗi.
             */
            override fun onVerificationFailed(e: FirebaseException) {
                // Gửi lỗi với thông báo đã được bản địa hóa từ ngoại lệ.
                _authResponseChannel.trySend(AuthResponse.Error(e.localizedMessage ?: "Xác minh điện thoại thất bại"))
            }

            /**
             * Được gọi khi mã xác minh SMS đã được gửi thành công đến số điện thoại.
             *
             * @param verificationId ID của yêu cầu xác minh. ID này cần thiết để xác minh mã sau này.
             * @param token Một token có thể được sử dụng để buộc gửi lại mã xác minh.
             */
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // Lưu trữ ID xác minh và token gửi lại.
                this@FirebaseAuthRepository.verificationId = verificationId
                this@FirebaseAuthRepository.resendToken = token
                // Gửi trạng thái thành công cho biết mã đã được gửi.
                _authResponseChannel.trySend(AuthResponse.Success("Mã đã được gửi đến $phoneNumber"))
            }
        }

        // Xây dựng các tùy chọn cho xác thực số điện thoại.
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber) // Đặt số điện thoại cần xác minh.
            .setTimeout(60L, TimeUnit.SECONDS) // Đặt thời gian chờ cho mã SMS là 60 giây.
            .setActivity(activity) // Yêu cầu Activity context cho xác thực điện thoại.
            .setCallbacks(callbacks) // Đặt các callback đã định nghĩa.
            .build()
        // Bắt đầu quá trình xác minh số điện thoại.
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    /**
     * Xác minh số điện thoại bằng mã SMS do người dùng nhập.
     *
     * @param verificationCode Mã SMS do người dùng nhập.
     * @return Một đối tượng [AuthResponse] cho biết trạng thái thành công hay lỗi.
     */
    override suspend fun verifyPhoneNumberWithCode(verificationCode: String): AuthResponse {
        return try {
            // Đảm bảo verificationId không phải là null trước khi sử dụng.
            if (verificationId == null) {
                return AuthResponse.Error("ID xác minh null. Vui lòng yêu cầu mã mới.")
            }

            // Tạo thông tin xác thực số điện thoại bằng verificationId và mã xác minh.
            val credential = PhoneAuthProvider.getCredential(verificationId!!, verificationCode)
            // Đăng nhập vào Firebase bằng thông tin xác thực điện thoại và chờ kết quả.
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                // Nếu đăng nhập thành công, tạo đối tượng User.
                val user = User(
                    uid = firebaseUser.uid,
                    name = firebaseUser.displayName ?: "",
                    email = firebaseUser.email ?: ""
                )
                AuthResponse.Success(user) // Trả về trạng thái thành công.
            } else {
                // Nếu người dùng null sau xác thực, tức là có vấn đề.
                AuthResponse.Error("Xác minh điện thoại thất bại: Không tìm thấy người dùng sau xác thực.")
            }
        } catch (e: Exception) {
            // Bắt các ngoại lệ trong quá trình xác minh và trả về lỗi.
            AuthResponse.Error(e.localizedMessage ?: "Lỗi không xác định trong quá trình xác minh số điện thoại")
        }
    }
}
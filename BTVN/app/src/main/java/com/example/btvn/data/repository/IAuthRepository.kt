package com.example.btvn.data.repository

import com.example.btvn.model.AuthResponse
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import android.app.Activity // Cần thiết cho xác thực số điện thoại

/**
 * Giao diện (interface) định nghĩa các phương thức cho việc xác thực người dùng
 * (đăng ký, đăng nhập, đăng xuất) sử dụng Firebase Authentication.
 */
interface IAuthRepository {

    /**
     * Thực hiện đăng nhập bằng tài khoản Google.
     *
     * @param idToken Mã ID Token nhận được từ quá trình đăng nhập Google.
     * @return Một [AuthResponse] cho biết kết quả thao tác (thành công hoặc lỗi).
     */
    suspend fun signInWithGoogle(idToken: String): AuthResponse

    /**
     * Lấy thông tin người dùng Firebase hiện tại đang đăng nhập.
     *
     * @return Đối tượng [FirebaseUser] nếu có người dùng đang đăng nhập, ngược lại là `null`.
     */
    fun getCurrentUser(): FirebaseUser?

    /**
     * Đăng xuất người dùng hiện tại khỏi Firebase.
     */
    fun signOut()

    /**
     * Cung cấp một [Flow] để lắng nghe các thay đổi trạng thái xác thực của người dùng
     * (ví dụ: khi người dùng đăng nhập hoặc đăng xuất).
     *
     * @return Một [Flow] phát ra đối tượng [FirebaseUser] (hoặc `null`) mỗi khi trạng thái thay đổi.
     */
    fun getAuthStateChanges(): Flow<FirebaseUser?>

    /**
     * Đăng ký một người dùng mới bằng địa chỉ email và mật khẩu.
     *
     * @param email Địa chỉ email để đăng ký.
     * @param password Mật khẩu cho tài khoản mới.
     * @return Một [AuthResponse] cho biết kết quả thao tác.
     */
    suspend fun registerWithEmailPassword(email: String, password: String): AuthResponse

    /**
     * Đăng nhập người dùng bằng địa chỉ email và mật khẩu.
     *
     * @param email Địa chỉ email của người dùng.
     * @param password Mật khẩu của người dùng.
     * @return Một [AuthResponse] cho biết kết quả thao tác.
     */
    suspend fun signInWithEmailPassword(email: String, password: String): AuthResponse

    /**
     * Bắt đầu quá trình xác minh số điện thoại bằng cách gửi mã OTP qua SMS.
     * Kết quả của quá trình này sẽ được phát ra thông qua `authResponseFlow`.
     *
     * @param activity Context của Activity hiện tại, cần thiết cho xác thực điện thoại.
     * @param phoneNumber Số điện thoại cần xác minh.
     */
    suspend fun startPhoneVerification(activity: Activity, phoneNumber: String)

    /**
     * Xác minh số điện thoại bằng mã OTP nhận được.
     *
     * @param verificationCode Mã OTP mà người dùng đã nhập.
     * @return Một [AuthResponse] cho biết kết quả xác minh (thành công hoặc lỗi).
     */
    suspend fun verifyPhoneNumberWithCode(verificationCode: String): AuthResponse
}
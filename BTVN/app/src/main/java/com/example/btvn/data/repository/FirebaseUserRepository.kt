package com.example.btvn.data.repository

import com.example.btvn.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseUserRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : IUserRepository {

    // Tham chiếu đến collection "users" trong Firestore, nơi lưu trữ thông tin người dùng
    private val usersCollection = firestore.collection("users")

    /**
     * Lấy thông tin hồ sơ người dùng từ Firestore dựa trên userId.
     * Phương thức này trả về một [Flow] của đối tượng [User], cho phép ứng dụng
     * nhận được các cập nhật **thời gian thực** về hồ sơ người dùng.
     * Nếu hồ sơ không tồn tại hoặc có lỗi, nó sẽ phát ra giá trị `null`.
     *
     * @param userId ID duy nhất của người dùng mà bạn muốn lấy hồ sơ.
     * @return Một [Flow] phát ra đối tượng [User] hoặc `null` nếu không tìm thấy hoặc có lỗi.
     */
    override fun getUserProfile(userId: String): Flow<User?> = callbackFlow {
        // Thêm một listener để lắng nghe các thay đổi thời gian thực trên tài liệu người dùng cụ thể
        val listenerRegistration = usersCollection.document(userId)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    // Nếu có lỗi xảy ra trong quá trình lắng nghe, gửi null vào Flow
                    trySend(null)
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    // Nếu tài liệu tồn tại, chuyển đổi nó thành đối tượng User và gửi vào Flow
                    trySend(snapshot.toObject(User::class.java))
                } else {
                    // Nếu tài liệu không tồn tại, gửi null vào Flow
                    trySend(null)
                }
            }

        // Đảm bảo rằng listener sẽ được gỡ bỏ khi Flow không còn được thu thập nữa,
        // giúp tránh rò rỉ bộ nhớ và lãng phí tài nguyên mạng.
        awaitClose { listenerRegistration.remove() }
    }

    /**
     * Lưu hoặc cập nhật thông tin hồ sơ người dùng vào Firestore.
     * Nếu hồ sơ người dùng với cùng UID đã tồn tại, nó sẽ được cập nhật.
     * Nếu chưa tồn tại, một hồ sơ người dùng mới sẽ được tạo.
     *
     * @param user Đối tượng [User] chứa dữ liệu hồ sơ cần lưu.
     * @return `true` nếu thao tác thành công, `false` nếu có lỗi.
     */
    override suspend fun saveUserProfile(user: User): Boolean {
        return try {
            // Đặt tài liệu người dùng trong Firestore, sử dụng UID của người dùng làm ID tài liệu.
            // Phương thức .await() sẽ tạm dừng coroutine cho đến khi thao tác hoàn thành.
            usersCollection.document(user.uid).set(user).await()
            true // Trả về true khi thành công
        } catch (e: Exception) {
            // Bắt bất kỳ ngoại lệ nào xảy ra trong quá trình lưu.
            // Bạn có thể ghi log lỗi ở đây để phục vụ việc debug.
            // Ví dụ: Log.e("FirebaseUserRepository", "Lỗi khi lưu hồ sơ người dùng: ${e.message}", e)
            false // Trả về false khi thất bại
        }
    }
}
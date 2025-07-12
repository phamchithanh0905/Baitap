package com.example.btvn.data.repository

import com.example.btvn.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Giao diện (interface) định nghĩa các hành động cần thiết để quản lý
 * thông tin hồ sơ người dùng (User Profile), thường là tương tác với cơ sở dữ liệu
 * như Firebase Firestore.
 */
interface IUserRepository {

    /**
     * Lấy thông tin hồ sơ của người dùng theo ID của họ.
     * Phương thức này trả về một [Flow], cho phép bạn nhận được
     * các cập nhật thời gian thực về hồ sơ người dùng nếu dữ liệu thay đổi.
     *
     * @param userId ID duy nhất của người dùng.
     * @return Một [Flow] phát ra đối tượng [User] nếu tìm thấy, hoặc `null` nếu không tồn tại hoặc có lỗi.
     */
    fun getUserProfile(userId: String): Flow<User?>

    /**
     * Lưu trữ hoặc cập nhật thông tin hồ sơ của một người dùng vào cơ sở dữ liệu.
     * Nếu hồ sơ với cùng UID đã tồn tại, nó sẽ được cập nhật; nếu không, một hồ sơ mới sẽ được tạo.
     *
     * @param user Đối tượng [User] chứa dữ liệu hồ sơ cần lưu hoặc cập nhật.
     * @return `true` nếu thao tác lưu thành công, `false` nếu xảy ra lỗi.
     */
    suspend fun saveUserProfile(user: User): Boolean
}
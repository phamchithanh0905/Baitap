package com.example.btvn.di

import com.example.btvn.data.repository.FirebaseAuthRepository
import com.example.btvn.data.repository.FirebaseUserRepository
import com.example.btvn.data.repository.IAuthRepository
import com.example.btvn.data.repository.IUserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * AppModule là một Dagger Hilt Module.
 * Nó chịu trách nhiệm cung cấp các phụ thuộc (dependencies) có phạm vi toàn cục (Singleton)
 * cho toàn bộ ứng dụng.
 */
@Module
@InstallIn(SingletonComponent::class) // Cài đặt module này trong SingletonComponent, nghĩa là các đối tượng được cung cấp sẽ tồn tại suốt vòng đời của ứng dụng.
object AppModule {

    /**
     * Cung cấp một thể hiện (instance) duy nhất của [FirebaseAuth].
     *
     * @return Thể hiện của [FirebaseAuth].
     */
    @Provides // Đánh dấu đây là một phương thức cung cấp dependency.
    @Singleton // Đảm bảo chỉ có một thể hiện của FirebaseAuth được tạo ra trong toàn bộ ứng dụng.
    fun provideFirebaseAuth(): FirebaseAuth =
        FirebaseAuth.getInstance() // Lấy thể hiện mặc định của FirebaseAuth.

    /**
     * Cung cấp một thể hiện (instance) duy nhất của [FirebaseFirestore].
     *
     * @return Thể hiện của [FirebaseFirestore].
     */
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore =
        FirebaseFirestore.getInstance() // Lấy thể hiện mặc định của FirebaseFirestore.

    /**
     * Cung cấp thể hiện của [IAuthRepository] (giao diện xác thực) bằng cách sử dụng
     * implementation [FirebaseAuthRepository].
     * Hilt sẽ tự động cung cấp [FirebaseAuth] cần thiết cho constructor của [FirebaseAuthRepository].
     *
     * @param firebaseAuth Dependency [FirebaseAuth] được Hilt cung cấp.
     * @return Một thể hiện của [IAuthRepository].
     */
    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): IAuthRepository =
        FirebaseAuthRepository(firebaseAuth) // Tạo FirebaseAuthRepository với FirebaseAuth đã được cung cấp.

    /**
     * Cung cấp thể hiện của [IUserRepository] (giao diện quản lý người dùng) bằng cách sử dụng
     * implementation [FirebaseUserRepository].
     * Hilt sẽ tự động cung cấp [FirebaseFirestore] cần thiết cho constructor của [FirebaseUserRepository].
     *
     * @param firestore Dependency [FirebaseFirestore] được Hilt cung cấp.
     * @return Một thể hiện của [IUserRepository].
     */
    @Provides
    @Singleton
    fun provideUserRepository(
        firestore: FirebaseFirestore
    ): IUserRepository =
        FirebaseUserRepository(firestore) // Tạo FirebaseUserRepository với FirebaseFirestore đã được cung cấp.
}
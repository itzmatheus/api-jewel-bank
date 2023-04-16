package com.jewelbank.api.repository

import com.jewelbank.api.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, String> {
    fun findByEmail(email: String): User?
}
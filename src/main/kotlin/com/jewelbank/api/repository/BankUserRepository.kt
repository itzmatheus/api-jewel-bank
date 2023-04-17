package com.jewelbank.api.repository

import com.jewelbank.api.entity.BankUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankUserRepository: JpaRepository<BankUser, String> {
    fun findByEmail(email: String): BankUser?
}
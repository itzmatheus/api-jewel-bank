package com.jewelbank.api.repository

import com.jewelbank.api.entity.Bank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankRepository: JpaRepository<Bank, String> {
    fun findByOrderByCreatedAtDesc(): List<Bank>
}
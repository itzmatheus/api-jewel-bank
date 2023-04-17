package com.jewelbank.api.service

import com.jewelbank.api.dto.BankUserRegisterDTO
import com.jewelbank.api.entity.BankUser
import com.jewelbank.api.repository.BankUserRepository
import com.jewelbank.api.utils.extensions.toEntity
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class BankUserService(
    private val bankUserRepository: BankUserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    private val logger = LoggerFactory.getLogger(BankUserService::class.java)

    fun register(bankUserRegisterDTO: BankUserRegisterDTO): BankUser {
        logger.info("Creating bank user with email: ${bankUserRegisterDTO.email}")
        val user = bankUserRegisterDTO.toEntity()
        user.password = passwordEncoder.encode(user.password)
        bankUserRepository.save(user)
        logger.info("Bank user successfully created!")
        return user
    }

}
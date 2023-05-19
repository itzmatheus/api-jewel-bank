package com.jewelbank.api.service

import com.jewelbank.api.dto.BankUserRegisterDTO
import com.jewelbank.api.entity.BankUser
import com.jewelbank.api.repository.BankUserRepository
import com.jewelbank.api.service.exceptions.EntityNotFoundException
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
        var user = bankUserRegisterDTO.toEntity()
        user.password = passwordEncoder.encode(user.password)
        user = bankUserRepository.save(user)
        logger.info("Bank user successfully created!")
        // add to queue create account for user #TODO
        return user
    }

    fun findById(id: String): BankUser {
        val user = bankUserRepository.findById(id)
        if (user.isEmpty) {
            throw EntityNotFoundException("BankUser not found by id: $id")
        }
        return user.get()
    }

}
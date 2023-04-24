package com.jewelbank.api.service

import com.jewelbank.api.entity.Bank
import com.jewelbank.api.repository.BankRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BankService(
    private val bankRepository: BankRepository,
) {

    private val logger = LoggerFactory.getLogger(BankService::class.java)

    fun getBank(): Bank? {
        logger.info("Getting bank")
        return bankRepository.findByOrderByCreatedAtDesc().first()
    }

}
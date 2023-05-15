package com.jewelbank.api.service

import com.jewelbank.api.entity.Account
import com.jewelbank.api.repository.AccountRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val bankService: BankService,
    private val agencyService: AgencyService,
    private val bankUserService: BankUserService,
) {

    private val logger = LoggerFactory.getLogger(AccountService::class.java)

    fun createAccountForUser(bankUserId: String): Account {

        logger.info("Get user by id: $bankUserId")
        val user = bankUserService.findById(bankUserId)
        logger.info("BankUser found id: ${user.email}")

        logger.info("Get agency")
        val agency = agencyService.getAgency()
        logger.info("Agency found: ${agency?.number}")

        logger.info("Get bank")
        val bank = bankService.getBank()
        logger.info("Bank found: ${bank?.number}")

        logger.info("Creating bank account for user ${user.email}")
        var account = Account(
            bankUser = user,
            bank = bank,
            agency = agency,
            number = (1..99999999).random().toString()
        )
        
        logger.info("try create bank")
        account = accountRepository.save(account)
        logger.info("bank account created")

        return account
    }

}
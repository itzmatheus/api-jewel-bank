package com.jewelbank.api.service

import com.jewelbank.api.dto.RequestCreateCardDTO
import com.jewelbank.api.entity.Account
import com.jewelbank.api.entity.Card
import com.jewelbank.api.entity.enumeration.Flag
import com.jewelbank.api.entity.enumeration.TypeCard
import com.jewelbank.api.repository.CardRepository
import com.jewelbank.api.utils.generateCardNumber
import com.jewelbank.api.utils.generateCardExpireDate
import com.jewelbank.api.utils.generateLimitForClient
import com.jewelbank.api.utils.generateCardSecureNumber
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CardService(
    private val cardRepository: CardRepository,
    private val accountService: AccountService,
) {

    private val logger = LoggerFactory.getLogger(CardService::class.java)

    fun create(request: RequestCreateCardDTO): Card? {

        logger.info("Get account from id: ${request.accountId}")
        val account: Account = accountService.findAccountById(request.accountId)
        logger.info("Creating card for user ${account.bankUser?.email}")
        var card = Card(
            name = request.name,
            number = generateCardNumber(),
            expireDate = generateCardExpireDate(),
            secureNumber = generateCardSecureNumber(),
            flag = Flag.values().random(),
            type = TypeCard.values().random(),
            limit = generateLimitForClient(),
            contactless = false,
            onlinePayment = false,
            atmWithdraws = false,
            account = account,
        )

        card = cardRepository.save(card)

        logger.info("Card created")
        return card
    }

}
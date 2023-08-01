package com.jewelbank.api.service

import com.jewelbank.api.repository.*
import com.jewelbank.api.utils.createAccountMock
import com.jewelbank.api.utils.createCardMock
import com.jewelbank.api.utils.createRequestCardDTOMock
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*


class CardServiceTest {

    private val cardRepository: CardRepository = mockk()
    private val accountRepository: AccountRepository = mockk()
    private val bankRepository: BankRepository = mockk()
    private val bankService: BankService = BankService(bankRepository)
    private val agencyRepository: AgencyRepository = mockk()
    private val agencyService: AgencyService = AgencyService(agencyRepository)
    private val bankUserRepository: BankUserRepository = mockk()
    private val passwordEncoder: BCryptPasswordEncoder = mockk()
    private val bankUserService: BankUserService = BankUserService(bankUserRepository, passwordEncoder)
    private val accountService: AccountService = AccountService(accountRepository, bankService, agencyService, bankUserService)
    private val cardService: CardService = CardService(cardRepository, accountService)

    @Test
    fun `Should create a card`() {

        val accountMock = createAccountMock()
        val requestMock = createRequestCardDTOMock(accountId = accountMock.id)

        every {
            accountRepository.findById(accountMock.id)
        } returns Optional.of(accountMock)

        every { cardRepository.save(any()) } returns createCardMock(account = accountMock)

        val card = cardService.create(requestMock)

        Assertions.assertEquals("1", card?.id)
        Assertions.assertEquals(accountMock.id, card?.account?.id)

        verify(exactly = 1) {
            accountRepository.findById(accountMock.id)
            cardRepository.save(any())
        }

    }
}
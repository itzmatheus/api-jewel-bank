package com.jewelbank.api.controller

import com.jewelbank.api.entity.Account
import com.jewelbank.api.entity.BankUser
import com.jewelbank.api.repository.AccountRepository
import com.jewelbank.api.repository.AgencyRepository
import com.jewelbank.api.repository.BankRepository
import com.jewelbank.api.repository.BankUserRepository
import com.jewelbank.api.utils.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test", "nosecurity")
class AccountControllerTest {

    @Autowired
    lateinit var template: TestRestTemplate
    @Autowired
    lateinit var accountRepository: AccountRepository
    @Autowired
    lateinit var agencyRepository: AgencyRepository
    @Autowired
    lateinit var bankRepository: BankRepository
    @Autowired
    lateinit var bankUserRepository: BankUserRepository

    @BeforeEach
    fun setUp() {
        bankUserRepository.deleteAll()
        accountRepository.deleteAll()
        agencyRepository.save(createAgencyMock())
        bankRepository.save(createBankMock())
    }

    @Test
    fun `Should create the account from a bank user id`() {

        val requestRegisterUser = createRequestRegisterUser()
        val responseCreateUser = template.postForEntity(ENDPOINT_BANK_USER_REGISTER, requestRegisterUser, BankUser::class.java)

        val responseCreateAccount = template.postForEntity(
            ENDPOINT_BANK_ACCOUNT_REGISTER.format(responseCreateUser.body?.id),
            null,
            Account::class.java,
        )

        Assertions.assertNotNull(responseCreateAccount.body?.id)
        Assertions.assertEquals(createAgencyMock().name, responseCreateAccount.body?.agency?.name)
        Assertions.assertEquals(createBankMock().name, responseCreateAccount.body?.bank?.name)
        Assertions.assertEquals(responseCreateUser.body?.id, responseCreateAccount.body?.bankUser?.id)

    }

}
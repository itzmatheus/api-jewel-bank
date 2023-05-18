package com.jewelbank.api.controller

import com.jewelbank.api.entity.BankUser
import com.jewelbank.api.utils.ENDPOINT_BANK_USER_REGISTER
import com.jewelbank.api.utils.ENDPOINT_LOGIN
import com.jewelbank.api.utils.createRequestLoginDTO
import com.jewelbank.api.utils.createRequestRegisterUser
import io.github.serpro69.kfaker.Faker
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BankUserControllerTest {

    @Autowired
    lateinit var template: TestRestTemplate

    private val faker = Faker()

    @Test
    fun shouldRegisterSuccessfully() {

        val requestRegisterUser = createRequestRegisterUser(
            name = faker.name.name(),
            email = faker.internet.email(),
            cpf = faker.random.randomString(11)
        )

        val response = template.postForEntity(ENDPOINT_BANK_USER_REGISTER, requestRegisterUser, BankUser::class.java)

        Assertions.assertTrue(response.statusCode.is2xxSuccessful)
        Assertions.assertNotNull(response.body?.id)
        Assertions.assertEquals(requestRegisterUser.name, response.body?.name)
        Assertions.assertEquals(requestRegisterUser.email, response.body?.email)
        Assertions.assertEquals(requestRegisterUser.cpf, response.body?.cpf)

    }

    @Test
    fun shouldNotRegisterBankUserWithEmailBlank() {

        val requestRegisterUser = createRequestRegisterUser(email = "")
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        val entity = HttpEntity(requestRegisterUser, headers)
        val response = template.exchange<Any>(ENDPOINT_BANK_USER_REGISTER, HttpMethod.POST, entity)

        Assertions.assertTrue(response.statusCode.is4xxClientError)
    }

    @Test
    fun shouldLoginSuccessfully() {
        val requestRegisterUser = createRequestRegisterUser(
            name = faker.name.name(),
            email = faker.internet.email(),
            cpf = faker.random.randomString(11)
        )

        template.postForEntity(ENDPOINT_BANK_USER_REGISTER, requestRegisterUser, BankUser::class.java)

        val loginRequest = createRequestLoginDTO(
            email = requestRegisterUser.email,
        )
        val response = template.postForEntity(ENDPOINT_LOGIN, loginRequest, String::class.java)

        Assertions.assertTrue(response.statusCode.is2xxSuccessful)
        Assertions.assertFalse(response.headers["Authorization"].isNullOrEmpty())
        Assertions.assertFalse(response.headers["TokenExpirationDate"].isNullOrEmpty())
    }

}
package com.jewelbank.api.utils

import com.jewelbank.api.dto.BankUserRegisterDTO
import com.jewelbank.api.dto.LoginDTO
import com.jewelbank.api.dto.RequestCreateCardDTO
import com.jewelbank.api.entity.*
import com.jewelbank.api.entity.enumeration.Flag
import com.jewelbank.api.entity.enumeration.TypeCard

const val NAME: String = "John Doe"
const val PASSWORD: String = "password_123"
const val EMAIL: String = "johndoe@jowelbank.com"
const val CPF: String = "00000000000"

const val ENDPOINT_LOGIN: String = "/login"
const val ENDPOINT_BANK_USER_REGISTER: String = "/bankuser/register"
const val ENDPOINT_BANK_ACCOUNT_REGISTER: String = "/account/bankuser/%s/create"
const val ENDPOINT_ACCOUNT_CARD_REGISTER: String = "/card"


fun createRequestRegisterUser(
    name: String = NAME,
    password: String = PASSWORD,
    email: String = EMAIL,
    cpf: String = CPF
) = BankUserRegisterDTO(
    name = name,
    password = password,
    email = email,
    cpf = cpf,
)

fun createRequestLoginDTO(
    email: String = EMAIL,
    password: String = PASSWORD,
) = LoginDTO(
    email = email,
    password = password,
)

fun createAgencyMock(
    name: String = "TEST AGENCY",
    number: String = "123456"
) = Agency(
    name = name,
    number = number
)

fun createBankMock(
    name: String = "TEST BANK",
    number: String = "123"
) = Bank(
    name = name,
    number = number
)

fun createCardMock(
    id: String = "1",
    name: String = "Online shop",
    number: String = "0000-0000-0000-0000",
    expireDate: String = "05/2023",
    secureNumber: String = "123",
    flag: Flag = Flag.MASTERCARD,
    type: TypeCard = TypeCard.CREDIT_AND_DEBIT_CARD,
    limit: Double = 1000.00,
    contactless: Boolean = true,
    onlinePayment: Boolean = true,
    atmWithdraws: Boolean = true,
    account: Account = createAccountMock()
) = Card(
    id = id,
    name = name,
    number = number,
    expireDate = expireDate,
    secureNumber = secureNumber,
    flag = flag,
    type = type,
    limit = limit,
    contactless = contactless,
    onlinePayment = onlinePayment,
    atmWithdraws = atmWithdraws,
    account = account
)

fun createRequestCardDTOMock(
    name: String = "Online shop",
    accountId: String = "0123"
) = RequestCreateCardDTO(
    name = name,
    accountId = accountId
)

fun createAccountMock(
    bankUser: BankUser = BankUser(),
    bank: Bank = Bank(),
    agency: Agency = Agency(),
    number: String = generateAccountNumber()
) = Account(
    bankUser = bankUser,
    bank = bank,
    agency = agency,
    number = number
)
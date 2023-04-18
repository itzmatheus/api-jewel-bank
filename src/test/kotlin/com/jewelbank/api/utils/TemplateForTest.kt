package com.jewelbank.api.utils

import com.jewelbank.api.dto.BankUserRegisterDTO
import com.jewelbank.api.dto.LoginDTO

const val NAME: String = "John Doe"
const val PASSWORD: String = "password_123"
const val EMAIL: String = "johndoe@jowelbank.com"
const val CPF: String = "00000000000"

const val ENDPOINT_LOGIN: String = "/login"
const val ENDPOINT_BANK_USER_REGISTER: String = "/bankuser/register"


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
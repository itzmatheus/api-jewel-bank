package com.jewelbank.api.utils.extensions

import com.jewelbank.api.dto.BankUserRegisterDTO
import com.jewelbank.api.entity.BankUser

fun BankUserRegisterDTO.toEntity() = BankUser(
    name = name,
    password = password,
    email = email,
    cpf = cpf,
)
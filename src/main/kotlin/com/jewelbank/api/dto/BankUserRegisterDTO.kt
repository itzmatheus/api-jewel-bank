package com.jewelbank.api.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BankUserRegisterDTO(
    val name: String = "",
    val password: String = "",
    val email: String = "",
    val cpf: String = "",
)
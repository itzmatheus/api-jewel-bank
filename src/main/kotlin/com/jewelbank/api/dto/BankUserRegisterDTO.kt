package com.jewelbank.api.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@JsonIgnoreProperties(ignoreUnknown = true)
data class BankUserRegisterDTO(
    @field:NotBlank(message = "field name required")
    val name: String = "",
    @field:NotBlank(message = "field password required")
    val password: String = "",
    @field:NotBlank(message = "field email required")
    val email: String = "",
    @field:Size(min = 11, max = 11, message = "field cpf must be 11 numbers")
    val cpf: String = "",
)
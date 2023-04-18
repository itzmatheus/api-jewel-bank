package com.jewelbank.api.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

@JsonIgnoreProperties(ignoreUnknown = true)
data class BankUserRegisterDTO(
    @field:NotBlank(message = "field name required")
    val name: String = "",
    @field:NotBlank(message = "field password required")
    val password: String = "",
    @field:NotBlank(message = "field email required")
    val email: String = "",
    @field:NotBlank(message = "field cpf required")
    @field:Min(value = 11, message = "cpf must be 11 numbers")
    @field:Max(value = 11, message = "cpf must be 11 numbers")
    val cpf: String = "",
)
package com.jewelbank.api.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@JsonIgnoreProperties(ignoreUnknown = true)
data class RequestCreateCardDTO(
    @field:NotBlank(message = "field name required")
    val name: String = "",
    @field:NotBlank(message = "field accountId required")
    val accountId: String = "",
)
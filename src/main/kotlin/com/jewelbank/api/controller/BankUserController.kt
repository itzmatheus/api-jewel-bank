package com.jewelbank.api.controller

import com.jewelbank.api.dto.BankUserRegisterDTO
import com.jewelbank.api.entity.BankUser
import com.jewelbank.api.service.BankUserService
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bankuser")
class BankUserController(
    private val bankUserService: BankUserService,
) {

    private val logger = LoggerFactory.getLogger(BankUserController::class.java)

    @PostMapping("/register")
    @ApiResponse(description = "Successfully Operation", responseCode = "201", content = [Content(mediaType = "application/json", schema = Schema(implementation = BankUser::class))])
    fun register(@Valid @RequestBody bankUserRegisterDTO: BankUserRegisterDTO): ResponseEntity<*> {
        logger.info("Call Endpoint Register BankUser")
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bankUserService.register(bankUserRegisterDTO))
    }

}
package com.jewelbank.api.controller

import com.jewelbank.api.entity.BankUser
import com.jewelbank.api.service.AccountService
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/account")
class AccountController(
    private val accountService: AccountService,
) {

    private val logger = LoggerFactory.getLogger(AccountController::class.java)

    @PostMapping("/bankuser/{id}/create")
    @ApiResponse(description = "Successfully Operation", responseCode = "201", content = [Content(mediaType = "application/json", schema = Schema(implementation = BankUser::class))])
    fun create(@PathVariable("id") bankUserId: String): ResponseEntity<*> {
        logger.info("Call Endpoint Create Bank Account")
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountService.createAccountForUser(bankUserId))
    }

}
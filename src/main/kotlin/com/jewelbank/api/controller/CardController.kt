package com.jewelbank.api.controller

import com.jewelbank.api.dto.RequestCreateCardDTO
import com.jewelbank.api.entity.BankUser
import com.jewelbank.api.service.AccountService
import com.jewelbank.api.service.CardService
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/card")
class CardController(
    private val cardService: CardService,
) {

    private val logger = LoggerFactory.getLogger(CardController::class.java)

    @PostMapping
    @ApiResponse(description = "Successfully Operation", responseCode = "201", content = [Content(mediaType = "application/json", schema = Schema(implementation = RequestCreateCardDTO::class))])
    fun create(@RequestBody request: RequestCreateCardDTO): ResponseEntity<*> {
        logger.info("Call Endpoint Create Card")
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cardService.create(request))
    }

}
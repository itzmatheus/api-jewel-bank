package com.jewelbank.api.controller.exceptions

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.IllegalStateException
import java.util.stream.Collectors

@ControllerAdvice
class ExceptionControllerAdvice {

    private val logger = LoggerFactory.getLogger(ExceptionControllerAdvice::class.java)

    @ExceptionHandler
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.BAD_REQUEST.value(),
            ex.message
        )
        logger.error(ex.message, ex)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorMessageModel> {
        val errors: List<String?> = ex.bindingResult.fieldErrors.stream().map {
            it.defaultMessage
        }.collect(Collectors.toList()).toList()
        val errorMessage = ErrorMessageModel(
            status = HttpStatus.BAD_REQUEST.value(),
            message = errors.toString()
        )
        logger.error(ex.message, ex)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

}
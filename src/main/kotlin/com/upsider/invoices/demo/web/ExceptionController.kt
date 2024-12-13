package com.upsider.invoices.demo.web


import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ExceptionController {
    private val logger = KotlinLogging.logger("ExceptionController")
    /**
     * Handle exceptions for the whole app.
     * Ang leaves Exception Logs for debug
     */
    @ExceptionHandler
    fun <T> handleException(e: Exception): ResponseEntity<Any> {
        logger.warn { e.message }
        return ResponseEntity.badRequest().body(e.message)
    }
}
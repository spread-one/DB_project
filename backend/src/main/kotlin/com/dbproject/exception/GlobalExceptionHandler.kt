package com.dbproject.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.MethodArgumentNotValidException
import jakarta.validation.ConstraintViolationException

@RestControllerAdvice
class GlobalExceptionHandler {

    // IllegalArgumentException 처리
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST) // 400 Bad Request 반환
    }

    // 기타 예외 처리 (필요 시 추가 가능)
    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity("An unexpected error occurred: ${ex.message}", HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<String> {
        val errorMessage = ex.bindingResult.allErrors.joinToString(", ") { it.defaultMessage ?: "Invalid input" }
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST) // 400 Bad Request
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<String> {
        val errorMessage = ex.constraintViolations.joinToString(", ") { it.message }
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST) // 400 Bad Request
    }
}
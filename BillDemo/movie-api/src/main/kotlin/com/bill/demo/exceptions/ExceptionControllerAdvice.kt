package com.bill.demo.exceptions

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<AppErrorModel> {
        val fieldErrors: List<FieldError> = ex.fieldErrors
        val validationErrors = fieldErrors.associate { it.field to it.defaultMessage }
        val errorDetails = AppErrorModel(
            status = HttpStatus.BAD_REQUEST.value(),
            message = "Validation Failed",
            details = validationErrors
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<AppErrorModel> {
        val errorMessage = AppErrorModel(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.localizedMessage,
            details = emptyMap()
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleAppException(appException: AppException): ResponseEntity<AppErrorModel> {
        val errorMessage = AppErrorModel(
            status = appException.status.value(),
            message = appException.localizedMessage,
            details = emptyMap()
        )
        return ResponseEntity(errorMessage, appException.status)
    }
}
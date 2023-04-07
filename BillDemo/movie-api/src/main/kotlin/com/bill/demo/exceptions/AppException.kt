package com.bill.demo.exceptions

import org.springframework.http.HttpStatus

data class AppException(
    override val message: String,
    val status: HttpStatus) : Exception(message)
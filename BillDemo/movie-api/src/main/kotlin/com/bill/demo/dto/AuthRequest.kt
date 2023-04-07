package com.bill.demo.dto

class AuthRequest {

    private val username: String? = null
    private val password: String? = null

    fun getPassword(): String? {
        return password
    }

    fun getUsername(): String? {
        return username
    }
}

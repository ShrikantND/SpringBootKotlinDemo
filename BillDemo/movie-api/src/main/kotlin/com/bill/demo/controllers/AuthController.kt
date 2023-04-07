package com.bill.demo.controllers

import com.bill.demo.dto.AuthRequest
import com.bill.demo.security.JwtService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/auth")
class AuthController {

    @Autowired
    private val jwtService: JwtService? = null

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    @PostMapping("")
    fun authenticateAndGetToken(@Valid @RequestBody authRequest: AuthRequest): String? {
        val authentication: Authentication = authenticationManager!!.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
            )
        )
        return if (authentication.isAuthenticated) {
            jwtService!!.generateToken(authRequest.getUsername()!!)
        } else {
            throw UsernameNotFoundException("Invalid request !")
        }
    }
}
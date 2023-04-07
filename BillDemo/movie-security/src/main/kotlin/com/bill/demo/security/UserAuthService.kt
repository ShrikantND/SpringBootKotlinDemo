package com.bill.demo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class UserAuthService {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(passwordEncoder: PasswordEncoder): UserDetailsService {
        val admin = User.withUsername("WriteUser")
            .password(passwordEncoder.encode("Write"))
            .roles("Write", "Read")
            .build()
        val user = User.withUsername("ReadUser")
            .password(passwordEncoder.encode("Read"))
            .roles("Read")
            .build()
        return InMemoryUserDetailsManager(admin, user)
    }
}
package com.bill.demo.security

import org.springframework.security.core.GrantedAuthority

class UserInfo {
    private val name: String? = null
    private val password: String? = null
    private val authorities: List<GrantedAuthority>? = null

    fun getPassword(): String? {
        return password
    }

    fun getName(): String? {
        return name
    }

    fun getAuthorities(): Collection<GrantedAuthority?>? {
        return authorities
    }
}
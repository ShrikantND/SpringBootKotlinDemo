package com.bill.demo.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtService {

    val SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"

    fun extractUsername(token: String?): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun extractExpiration(token: String?): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    fun <T> extractClaim(token: String?, claimsResolver: (Claims) -> T): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String?): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
    }

    private fun isTokenExpired(token: String?): Boolean? {
        return extractExpiration(token).before(Date())
    }

    fun validateToken(token: String?, userDetails: UserDetails): Boolean? {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)!!
    }


    fun generateToken(userName: String): String? {
        val claims: Map<String, Any> = HashMap()
        return createToken(claims, userName)
    }

    private fun createToken(claims: Map<String, Any>, userName: String): String? {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 30))
            .signWith(getSignKey(), SignatureAlgorithm.HS256).compact()
    }

    private fun getSignKey(): Key? {
        val keyBytes: ByteArray = Decoders.BASE64.decode(SECRET)
        return Keys.hmacShaKeyFor(keyBytes)
    }

}
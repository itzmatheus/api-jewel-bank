package com.jewelbank.api.config.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenUtil {

    private val secret = "SECRET_HASHASHASHASHAS"
    private val oneHourInMs = 3600000

    fun generateToken(username: String): Pair<String, Date> {
        val expirationDate = Date(System.currentTimeMillis() + oneHourInMs)
        val token = Jwts.builder().setSubject(username).setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray()).compact()
        return token to expirationDate
    }

    private fun getClaims(token: String) =
        Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body

    fun getEmail(token: String): String = getClaims(token).subject

    fun isTokenValid(token: String): Boolean {
        val claims = getClaims(token)
        val expirationDateToken = claims.expiration
        val now = Date(System.currentTimeMillis())
        return now.before(expirationDateToken)
    }

}
package com.jewelbank.api.config.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.jewelbank.api.config.security.UserSecurity
import com.jewelbank.api.config.security.enumeration.UserRoles
import com.jewelbank.api.dto.LoginDTO
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

class JwtAuthenticationFilter(
    private val jwtTokenUtil: JwtTokenUtil,
    private val authenticationManager: AuthenticationManager,
): UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credentials = ObjectMapper().readValue(request.inputStream, LoginDTO::class.java)
        val auth = UsernamePasswordAuthenticationToken(
             credentials.email,
            credentials.password,
            Collections.singleton(SimpleGrantedAuthority(UserRoles.USER.role))
        )
        return authenticationManager.authenticate(auth)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val username = (authResult.principal as UserSecurity).username
        val (token: String, expirationDate: Date) = jwtTokenUtil.generateToken(username)
        response.addHeader("Authorization", token)
        response.addHeader("TokenExpirationDate", "${expirationDate.time}")
        response.addHeader("Access-Control-Expose-Headers", "Authorization, TokenExpirationDate")
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        val error = BadCredentialsError()
        response.status = error.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.append(error.toString())
    }

}

private data class BadCredentialsError(
    val timestamp: Long = Date().time,
    val status: Int = 401,
    val message: String = "Email or password incorrect",
) {
    override fun toString(): String {
        return ObjectMapper().writeValueAsString(this)
    }
}
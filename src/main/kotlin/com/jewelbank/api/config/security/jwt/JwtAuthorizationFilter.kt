package com.jewelbank.api.config.security.jwt

import com.jewelbank.api.config.security.service.UserDetailsServiceImpl
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException

class JwtAuthorizationFilter(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsServiceImpl: UserDetailsServiceImpl,
    private val authManager: AuthenticationManager,
) : BasicAuthenticationFilter(authManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader("Authorization")
        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response)
            return
        }
        getAuthentication(header.substring(7))?.also {
            SecurityContextHolder.getContext().authentication = it
        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken? {
        if (!jwtTokenUtil.isTokenValid(token)) return null
        val email = jwtTokenUtil.getEmail(token)
        val user = userDetailsServiceImpl.loadUserByUsername(email)
        return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }
}
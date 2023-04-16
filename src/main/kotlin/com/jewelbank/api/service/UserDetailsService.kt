package com.jewelbank.api.service

import com.jewelbank.api.config.security.UserSecurity
import com.jewelbank.api.config.security.enumeration.UserRoles
import com.jewelbank.api.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.Collections

@Service
class UserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username) ?: throw UsernameNotFoundException("$username not found")
        return UserSecurity(
            id = user.id,
            email = user.email,
            uPassword = user.password,
            uAuthorities = Collections.singleton(SimpleGrantedAuthority(UserRoles.USER.role))
        )
    }
}
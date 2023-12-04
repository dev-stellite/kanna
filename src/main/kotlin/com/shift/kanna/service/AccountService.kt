package com.shift.kanna.service

import com.shift.kanna.dto.account.CustomUserDetails
import com.shift.kanna.dto.account.RegisterAccountDto
import com.shift.kanna.model.Account
import com.shift.kanna.repository.AccountRepository
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {
    override fun loadUserByUsername(email: String?): UserDetails {
        if (email == null) throw AuthenticationServiceException("username not provided")

        val account = accountRepository.findByEmail(email)
            ?: throw AuthenticationServiceException("user not found")

        return CustomUserDetails(account)
    }

    fun registerAccount(registerAccountDto: RegisterAccountDto): Account {
        if (accountRepository.existsByEmail(registerAccountDto.email)) {
            throw IllegalArgumentException("account with this email is already exists")
        }

        val password = passwordEncoder.encode(registerAccountDto.password)
        val account = Account(
            accountName = registerAccountDto.accountName,
            email = registerAccountDto.email,
            password = password,
        )

        return accountRepository.save(account)
    }
}
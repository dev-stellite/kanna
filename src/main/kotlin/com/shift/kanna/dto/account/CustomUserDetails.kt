package com.shift.kanna.dto.account

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import com.shift.kanna.model.Account

class CustomUserDetails(
    private val account: Account,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    val id get() = account.id

    override fun getPassword(): String = account.password

    override fun getUsername(): String = account.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
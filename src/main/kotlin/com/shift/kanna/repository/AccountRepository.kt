package com.shift.kanna.repository

import com.shift.kanna.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByEmail(email: String): Account?

    fun existsByEmail(email: String): Boolean
}
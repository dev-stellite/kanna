package com.shift.kanna.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.shift.kanna.dto.account.RegisterAccountDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Account {
    @Id @GeneratedValue
    var id: Long = 0; private set

    @Column
    lateinit var accountName: String; private set

    @Column(unique = true)
    lateinit var email: String; private set

    @Column
    @JsonIgnore
    lateinit var password: String; private set

    companion object {
        fun register(registerAccount: RegisterAccountDto, encodedPassword: String) = Account().apply {
            accountName = registerAccount.accountName
            email = registerAccount.email
            password = encodedPassword
        }
    }
}
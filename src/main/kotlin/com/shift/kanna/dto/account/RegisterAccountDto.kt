package com.shift.kanna.dto.account

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class RegisterAccountDto(
    @field:NotEmpty
    @field:Email
    val email: String,

    @field:NotEmpty
    val password: String,

    @field:NotEmpty
    val accountName: String,
)
package com.shift.kanna.controller

import com.shift.kanna.dto.account.RegisterAccountDto
import com.shift.kanna.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val accountService: AccountService,
) {
    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody dto: RegisterAccountDto) {
        accountService.registerAccount(dto)
    }
}
package com.shift.kanna.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.shift.kanna.dto.account.RegisterAccountDto
import com.shift.kanna.model.base.PrimaryKeyEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.io.Serializable

@Entity
class Account(
    @Column
    val accountName: String,
    @Column(unique = true)
    val email: String,
    @Column
    @JsonIgnore
    val password: String,
) : PrimaryKeyEntity(), Serializable
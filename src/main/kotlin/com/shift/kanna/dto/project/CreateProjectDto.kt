package com.shift.kanna.dto.project

import jakarta.validation.constraints.NotEmpty

data class CreateProjectDto(
    @field:NotEmpty
    val title: String,
)
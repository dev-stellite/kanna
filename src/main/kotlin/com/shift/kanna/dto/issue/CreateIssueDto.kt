package com.shift.kanna.dto.issue

import jakarta.validation.constraints.NotEmpty

data class CreateIssueDto(
    @field:NotEmpty
    val title: String,
)
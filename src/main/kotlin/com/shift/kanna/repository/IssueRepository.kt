package com.shift.kanna.repository

import com.shift.kanna.model.Issue
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long> {
    fun countAllByProjectId(projectId: Long): Long

    fun findAllByProjectId(projectId: Long): List<Issue>
}
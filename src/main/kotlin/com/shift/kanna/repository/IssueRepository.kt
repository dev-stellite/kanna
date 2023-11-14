package com.shift.kanna.repository

import com.shift.kanna.model.Issue
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface IssueRepository : JpaRepository<Issue, Long> {
    @Query("SELECT issue.issueKey FROM Issue issue ORDER BY issue.issueKey DESC LIMIT 1")
    fun getLatestIssueKey(projectId: Long): Long

    @Query("SELECT issue FROM Issue issue JOIN FETCH issue.reporter")
    fun findAllByProjectId(projectId: Long): List<Issue>
}
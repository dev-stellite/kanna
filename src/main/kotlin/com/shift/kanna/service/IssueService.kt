package com.shift.kanna.service

import com.shift.kanna.dto.issue.CreateIssueDto
import com.shift.kanna.model.Issue
import com.shift.kanna.repository.IssueRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class IssueService(
    private val issueRepository: IssueRepository,
) {
    @Transactional
    fun createIssue(
        projectId: Long,
        reporterId: Long,
        createIssueDto: CreateIssueDto,
    ): Issue {
        return Issue.create(projectId, reporterId, createIssueDto, issueRepository)
    }

    fun getIssues(projectId: Long): List<Issue> {
        return issueRepository.findAllByProjectId(projectId)
    }

    fun getIssue(issueId: Long): Issue? {
        return issueRepository.findById(issueId).getOrNull()
    }
}
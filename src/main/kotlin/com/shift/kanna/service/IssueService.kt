package com.shift.kanna.service

import com.shift.kanna.dto.issue.CreateIssueDto
import com.shift.kanna.model.Issue
import com.shift.kanna.repository.IssueRepository
import com.shift.kanna.repository.ProjectMemberRepository
import com.shift.kanna.repository.ProjectRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import kotlin.jvm.optionals.getOrNull

@Service
class IssueService(
    private val projectRepository: ProjectRepository,
    private val projectMemberRepository: ProjectMemberRepository,
    private val issueRepository: IssueRepository,
) {
    @Transactional
    fun createIssue(
        projectId: Long,
        reporterId: Long,
        createIssueDto: CreateIssueDto,
    ): Issue {
        val project = projectRepository.findById(projectId).getOrNull()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val reporter = projectMemberRepository.findById(reporterId).getOrNull()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        val previousIssueKey = issueRepository.getLatestIssueKey(projectId)
        val issue = Issue(
            title = createIssueDto.title,
            issueKey = previousIssueKey + 1,
            project = project,
            reporter = reporter,
        )

        return issueRepository.save(issue)
    }

    fun getIssues(projectId: Long): List<Issue> {
        return issueRepository.findAllByProjectId(projectId)
    }

    fun getIssue(issueId: Long): Issue? {
        return issueRepository.findById(issueId).getOrNull()
    }
}
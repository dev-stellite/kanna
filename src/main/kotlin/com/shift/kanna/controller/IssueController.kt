package com.shift.kanna.controller

import com.shift.kanna.controller.argument.CurrentProjectMember
import com.shift.kanna.dto.issue.CreateIssueDto
import com.shift.kanna.dto.issue.GetIssuesResponseDto
import com.shift.kanna.model.Issue
import com.shift.kanna.model.IssueStatus
import com.shift.kanna.model.ProjectMember
import com.shift.kanna.service.IssueService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/projects/{projectId}/issues")
class IssueController(
    private val issueService: IssueService,
) {
    @GetMapping
    fun getIssues(@PathVariable("projectId") projectId: Long): Map<IssueStatus, List<GetIssuesResponseDto>> {
        return issueService.getIssues(projectId)
            .map { GetIssuesResponseDto(it) }
            .groupBy { it.status }
    }

    @GetMapping("{id}")
    fun getIssue(@PathVariable("projectId") projectId: Long,
                 @PathVariable("id") issueId: Long): Issue {
        val issue = issueService.getIssue(issueId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        if (issue.project.id != projectId) throw ResponseStatusException(HttpStatus.NOT_FOUND)

        return issue
    }

    @PostMapping
    fun postIssues(@PathVariable("projectId") projectId: Long,
                   @CurrentProjectMember projectMember: ProjectMember,
                   @RequestBody dto: CreateIssueDto) {
        issueService.createIssue(projectId, projectMember.id, dto)
    }
}
package com.shift.kanna.controller

import com.shift.kanna.controller.argument.CurrentProjectMember
import com.shift.kanna.dto.issue.CreateIssueDto
import com.shift.kanna.dto.issue.GetIssuesResponseDto
import com.shift.kanna.model.Issue
import com.shift.kanna.model.ProjectMember
import com.shift.kanna.service.IssueService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class IssueController(
    private val issueService: IssueService,
) {
    @GetMapping("/projects/{projectId}/issues")
    fun getIssues(@PathVariable("projectId") projectId: Long): List<GetIssuesResponseDto> {
        return issueService.getIssues(projectId).map { GetIssuesResponseDto(it) }
    }

    @PostMapping("/projects/{projectId}/issues")
    fun postIssues(@PathVariable("projectId") projectId: Long,
                   @CurrentProjectMember projectMember: ProjectMember,
                   @RequestBody dto: CreateIssueDto) {
        issueService.createIssue(projectId, projectMember.id, dto)
    }
}
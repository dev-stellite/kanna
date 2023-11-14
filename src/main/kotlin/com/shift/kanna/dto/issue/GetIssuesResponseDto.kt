package com.shift.kanna.dto.issue

import com.shift.kanna.model.Issue

class GetIssuesResponseDto(issue: Issue) {
    val id = issue.id
    val issueKey = issue.issueKey
    val projectId = issue.project.id
    val title = issue.title
    val storypoint = issue.storypoint
    val status = issue.status
    val reporter = issue.reporter.id
    val assigneeId = issue.assignee?.id
}
package com.shift.kanna.controller

import com.shift.kanna.dto.account.CustomUserDetails
import com.shift.kanna.dto.project.CreateProjectDto
import com.shift.kanna.model.Project
import com.shift.kanna.service.ProjectService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProjectController(
    private val projectService: ProjectService,
) {
    @GetMapping("/projects")
    fun getProjects(@AuthenticationPrincipal user: CustomUserDetails): List<Project> {
        return projectService.getAllProjects(user.id)
    }

    @PostMapping("/projects")
    fun createProject(@AuthenticationPrincipal user: CustomUserDetails,
                      @RequestBody dto: CreateProjectDto) {
        projectService.createProject(user.id, dto)
    }

}
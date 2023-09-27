package com.shift.kanna.service

import com.shift.kanna.dto.project.CreateProjectDto
import com.shift.kanna.model.Project
import com.shift.kanna.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
) {
    fun getAllProjects(ownerId: Long) = projectRepository.findAllByOwnerId(ownerId)

    fun createProject(ownerId: Long, createProjectDto: CreateProjectDto): Project {
        val project = Project.create(ownerId, createProjectDto)

        return projectRepository.save(project)
    }
}
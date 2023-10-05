package com.shift.kanna.service

import com.shift.kanna.dto.project.CreateProjectDto
import com.shift.kanna.model.Project
import com.shift.kanna.model.ProjectMember
import com.shift.kanna.repository.ProjectMemberRepository
import com.shift.kanna.repository.ProjectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val projectMemberRepository: ProjectMemberRepository,
) {
    fun getAllProjects(accountId: Long): List<Project> {
        return projectRepository.findJoiningProjects(accountId)
    }

    @Transactional
    fun createProject(ownerId: Long, createProjectDto: CreateProjectDto): Project {
        val project = Project.create(ownerId, createProjectDto)
        val persistedProject = projectRepository.save(project)
        val projectMember = persistedProject.join(ownerId)
        projectMemberRepository.save(projectMember)

        return persistedProject
    }

    fun getProjectMemberByAccount(projectId: Long, accountId: Long): ProjectMember? {
        return projectMemberRepository.findByProjectIdAndAccountId(projectId, accountId)
    }
}
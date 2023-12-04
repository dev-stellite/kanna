package com.shift.kanna.service

import com.shift.kanna.dto.project.CreateProjectDto
import com.shift.kanna.model.Project
import com.shift.kanna.model.ProjectMember
import com.shift.kanna.repository.AccountRepository
import com.shift.kanna.repository.ProjectMemberRepository
import com.shift.kanna.repository.ProjectRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import kotlin.jvm.optionals.getOrNull

@Service
class ProjectService(
    private val accountRepository: AccountRepository,
    private val projectRepository: ProjectRepository,
    private val projectMemberRepository: ProjectMemberRepository,
) {
    fun getAllProjects(accountId: Long): List<Project> {
        return projectRepository.findJoiningProjects(accountId)
    }

    @Transactional
    fun createProject(ownerId: Long, createProjectDto: CreateProjectDto): Project {
        val owner = accountRepository.findById(ownerId).getOrNull()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val project = Project(
            title = createProjectDto.title,
            owner = owner,
        )
        val persistedProject = projectRepository.save(project)
        val projectMember = persistedProject.join(owner)
        projectMemberRepository.save(projectMember)

        return persistedProject
    }

    fun getProjectMemberByAccount(projectId: Long, accountId: Long): ProjectMember? {
        return projectMemberRepository.findByProjectIdAndAccountId(projectId, accountId)
    }
}
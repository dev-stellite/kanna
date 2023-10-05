package com.shift.kanna.repository

import com.shift.kanna.model.ProjectMember
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectMemberRepository : JpaRepository<ProjectMember, Long> {
    fun findByProjectIdAndAccountId(projectId: Long, accountId: Long): ProjectMember?
}
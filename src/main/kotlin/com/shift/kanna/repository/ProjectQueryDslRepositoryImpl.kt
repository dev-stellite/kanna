package com.shift.kanna.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.shift.kanna.model.Project
import com.shift.kanna.model.QAccount
import com.shift.kanna.model.QProject
import com.shift.kanna.model.QProjectMember

class ProjectQueryDslRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : ProjectQueryDslRepository {
    override fun findJoiningProjects(accountId: Long): List<Project> {
        val project = QProject.project
        val projectMember = QProjectMember.projectMember
        val account = QAccount.account

        return jpaQueryFactory
            .select(project)
            .from(project)
            .leftJoin(project.owner, account).fetchJoin()
            .leftJoin(projectMember).on(projectMember.project.id.eq(project.id))
            .where(projectMember.account.id.eq(accountId))
            .fetch()
    }
}

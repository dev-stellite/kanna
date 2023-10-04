package com.shift.kanna.repository

import com.shift.kanna.model.Project

interface ProjectQueryDslRepository {
    fun findJoiningProjects(accountId: Long): List<Project>
}
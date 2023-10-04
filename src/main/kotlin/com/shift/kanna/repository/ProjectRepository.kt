package com.shift.kanna.repository

import com.shift.kanna.model.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long>, ProjectQueryDslRepository
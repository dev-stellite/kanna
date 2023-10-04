package com.shift.kanna.repository

import com.shift.kanna.model.Issue
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long>
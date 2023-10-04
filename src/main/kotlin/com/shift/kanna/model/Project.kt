package com.shift.kanna.model

import com.shift.kanna.dto.project.CreateProjectDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Project {
    @Id @GeneratedValue
    var id: Long = 0; private set

    @Column
    lateinit var title: String; private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    lateinit var owner: Account; private set
    @Column(name = "owner_id")
    var ownerId: Long = 0; private set

    companion object {
        fun create(ownerId: Long, createProject: CreateProjectDto) = Project().apply {
            title = createProject.title
            this.ownerId = ownerId
        }
    }

    fun join(accountId: Long): ProjectMember {
        return ProjectMember(accountId, id)
    }
}
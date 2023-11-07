package com.shift.kanna.model

import com.shift.kanna.dto.project.CreateProjectDto
import com.shift.kanna.model.base.PrimaryKeyEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Project(
    @Column
    val title: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    val owner: Account,
) : PrimaryKeyEntity() {
    fun join(account: Account): ProjectMember {
        return ProjectMember(
            account = account,
            project = this,
        )
    }
}
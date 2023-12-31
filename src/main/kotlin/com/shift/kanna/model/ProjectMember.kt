package com.shift.kanna.model

import com.shift.kanna.model.base.PrimaryKeyEntity
import jakarta.persistence.*

@Entity
@Table(name = "project_member")
class ProjectMember(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    val account: Account,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    val project: Project,
) : PrimaryKeyEntity()

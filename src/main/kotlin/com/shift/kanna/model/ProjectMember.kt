package com.shift.kanna.model

import jakarta.persistence.*

@Entity
@Table(name = "project_member")
class ProjectMember() {
    constructor(accountId: Long, projectId: Long) : this() {
        this.accountId = accountId
        this.projectId = projectId
    }

    @Id @GeneratedValue
    var id: Long = 0; private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    lateinit var account: Account; private set
    @Column(name = "account_id")
    var accountId: Long = 0; private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    lateinit var project: Project; private set
    @Column(name = "project_id")
    var projectId: Long = 0; private set
}
package com.shift.kanna.model

import jakarta.persistence.*

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(columnNames = ["project_id", "issue_key"])]
)
class Issue {
    @Id @GeneratedValue
    var id: Long = 0; private set

    @Column(name = "issue_key")
    var issueKey: Long = 0; private set

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var project: Project; private set

    @Column
    lateinit var title: String; private set

    @Column
    var storypoint: Int = 0; private set

    @Column
    lateinit var status: IssueStatus; private set

    @ManyToOne(fetch = FetchType.LAZY)
    var assignee: ProjectMember? = null; private set
}

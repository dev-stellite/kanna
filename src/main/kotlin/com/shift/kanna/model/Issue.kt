package com.shift.kanna.model

import com.shift.kanna.dto.issue.CreateIssueDto
import com.shift.kanna.model.base.PrimaryKeyEntity
import com.shift.kanna.repository.IssueRepository
import jakarta.persistence.*

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(columnNames = ["project_id", "issue_key"])]
)
class Issue(
    @Column
    val title: String,

    @Column(unique = true, name = "issue_key")
    val issueKey: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    var project: Project,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    val reporter: ProjectMember
) : PrimaryKeyEntity() {
    @Column
    var storypoint: Int = 0; private set

    @Column
    var status: IssueStatus = IssueStatus.Todo; private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    var assignee: ProjectMember? = null; private set
}

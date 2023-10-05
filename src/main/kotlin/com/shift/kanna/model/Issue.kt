package com.shift.kanna.model

import com.shift.kanna.dto.issue.CreateIssueDto
import com.shift.kanna.repository.IssueRepository
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
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    lateinit var project: Project; private set
    @Column(name = "project_id")
    var projectId: Long = 0; private set

    @Column
    lateinit var title: String; private set

    @Column
    var storypoint: Int = 0; private set

    @Column
    var status: IssueStatus = IssueStatus.Todo; private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id", insertable = false, updatable = false)
    lateinit var reporter: ProjectMember; private set
    @Column(name = "reporter_id")
    var reporterId: Long = 0; private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id", insertable = false, updatable = false)
    var assignee: ProjectMember? = null; private set
    @Column(name = "assignee_id")
    var assigneeId: Long? = null; private set

    companion object {
        fun create(projectId: Long,
                   reporterId: Long,
                   createIssue: CreateIssueDto,
                   issueRepository: IssueRepository): Issue {
            return Issue().apply {
                this.projectId = projectId
                this.reporterId = reporterId
                title = createIssue.title
                issueKey = issueRepository.countAllByProjectId(projectId) + 1
            }
        }
    }
}

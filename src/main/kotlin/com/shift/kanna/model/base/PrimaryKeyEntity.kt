package com.shift.kanna.model.base

import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.domain.Persistable

@MappedSuperclass
abstract class PrimaryKeyEntity : Persistable<Long> {
    @Id
    @GeneratedValue
    private val id: Long = 0

    override fun getId(): Long = id

    override fun isNew(): Boolean = getId() <= 0
}
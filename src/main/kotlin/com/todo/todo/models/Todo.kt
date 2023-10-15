package com.todo.todo.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.Date

@Entity
@Table(name = "todo")
data class Todo(
    @Id
    @GeneratedValue
    val id: Long = 0,

    @Column(name = "title", nullable = false)
    var title: String = "",

    @Column(name = "description", nullable = false)
    var description: String = "",

    @Column(name = "progress", nullable = false)
    var progress: Boolean = false,

    @CreationTimestamp
    val createdAt: Date? = null,

    @UpdateTimestamp
    var updatedAt: Date? = null
)
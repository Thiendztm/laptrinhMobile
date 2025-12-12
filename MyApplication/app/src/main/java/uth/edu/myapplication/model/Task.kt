package uth.edu.myapplication.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val dueDate: String
)

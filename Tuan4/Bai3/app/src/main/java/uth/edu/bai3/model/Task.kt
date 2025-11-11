package uth.edu.bai3.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String = "",
    @SerialName("status") val status: String = "",
    @SerialName("priority") val priority: String = "",
    @SerialName("category") val category: String = "",
    @SerialName("dueDate") val dueDate: String = "",
    @SerialName("createdAt") val createdAt: String = "",
    @SerialName("updatedAt") val updatedAt: String = "",
    @SerialName("subtasks") val subtasks: List<Subtask> = emptyList(),
    @SerialName("attachments") val attachments: List<Attachment> = emptyList(),
    @SerialName("reminders") val reminders: List<Reminder> = emptyList()
)
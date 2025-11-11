package uth.edu.bai3.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Subtask(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("isCompleted") val isCompleted: Boolean
)
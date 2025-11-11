package uth.edu.bai3.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class TaskResponse(
    @SerialName("isSuccess") val isSuccess: Boolean,
    @SerialName("message") val message: String,
    @SerialName("data") val data: List<Task>
)
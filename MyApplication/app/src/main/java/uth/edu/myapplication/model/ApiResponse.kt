package uth.edu.myapplication.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)

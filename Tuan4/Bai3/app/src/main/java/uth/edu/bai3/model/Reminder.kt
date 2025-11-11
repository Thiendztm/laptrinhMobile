package uth.edu.bai3.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Reminder(
    @SerialName("id") val id: Int,
    @SerialName("time") val time: String,
    @SerialName("type") val type: String
)
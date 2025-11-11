package uth.edu.bai3.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Attachment(
    @SerialName("id") val id: Int,
    @SerialName("fileName") val fileName: String,
    @SerialName("fileUrl") val fileUrl: String
)
package data.model
import java.time.LocalDateTime


data class Task(
    val id: Int,
    val name: String,
    val date: LocalDateTime
)
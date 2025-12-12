package uth.edu.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import uth.edu.myapplication.model.ApiResponse
import uth.edu.myapplication.model.Task

class MainActivity : ComponentActivity() {

    private val client = HttpClient(CIO)
    {
        engine {
            requestTimeout = 30_000
        }

        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var output by remember { mutableStateOf("Đang tải...") }

            LaunchedEffect(true) {
                try {
                    val response: ApiResponse = client.get(
                        "https://amock.io/api/researchUTH/tasks"
                    ).body()

                    if (response.isSuccess) {
                        output = response.data.take(18).joinToString("\n\n") {
                            "ID: ${it.id}\n" +
                            "Title: ${it.title}\n" +
                            "Description: ${it.description}\n" +
                            "Status: ${it.status}\n" +
                            "Due Date: ${it.dueDate}"
                        }
                    } else {
                        output = "Lỗi từ API: ${response.message}"
                    }
                } catch (e: Exception) {
                    output = "Lỗi: ${e.message}"
                }
            }

            Demo(content = output)
        }
    }
}

@Composable
fun Demo(content: String) {
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp, start = 20.dp, end = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = content)
            }
        }
    }
}
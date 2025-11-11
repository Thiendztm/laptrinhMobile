package uth.edu.thuchanh1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip

data class QuoteItem(val index: Int, val quote: String)

val quoteList = List(1_000_000) { i->
    QuoteItem(
        index = i + 1,
        quote = "The only way to do great work is to love what you do"
    )
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "Screen1") {
                composable("Screen1") {
                    Screen1(onReadyClick = {
                        navController.navigate("Screen2")
                    })
                }
                composable("Screen2") {
                    Screen2(onBackClick = {
                        navController.navigate("Screen1")
                    })
                }
            }
        }
    }
}


@Composable
fun Screen1(onReadyClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.jetpack),
                contentDescription = null,
                modifier = Modifier.size(220.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Navigation",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "is a framework that simplifies the implementation of navigation between different UI components(activities, fragments, of composable) is an app",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onReadyClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(text = "Push")
            }
        }
    }
}

@Composable
fun Screen2(onBackClick: () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = "<",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.clickable { onBackClick() }

                )

                Text(
                    text = "LazyColumn",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 8.dp)
            ){
                items(quoteList) {
                    item -> QuoteRow(item)
                }
            }
        }
    }
}

@Composable
fun QuoteRow(item: QuoteItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFFB3E5FC), RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${"%02d".format(item.index)} | ${item.quote}",
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            color = Color.Black
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Go",
            tint = Color.Black,
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp)
        )
    }
}

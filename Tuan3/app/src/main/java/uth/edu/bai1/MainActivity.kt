package uth.edu.bai1

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.clickable
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

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
                    Screen2(onTextClick = {
                        navController.navigate("Screen3")
                    }, onImageClick = {
                        navController.navigate("Screen4")
                    }, onTextFieldClick = {
                        navController.navigate("Screen5")
                    }, onPassFieldClick = {
                        navController.navigate("Screen6")
                    }, onRowClick = {
                        navController.navigate("Screen7")
                    }, onColumnClick = {
                        navController.navigate("Screen8")
                    })
                }
                composable("Screen3") {
                    Screen3(onBackClick = {
                        navController.navigate("Screen2")
                    })
                }
                composable("Screen4") {
                    Screen4(onBackClick = {
                        navController.navigate("Screen2")
                    })
                }
                composable("Screen5") {
                    Screen5(onBackClick = {
                        navController.navigate("Screen2")
                    })
                }
                composable("Screen6") {
                    Screen6(onBackClick = {
                        navController.navigate("Screen2")
                    })
                }
                composable("Screen7") {
                    Screen7(onBackClick = {
                        navController.navigate("Screen2")
                    })
                }
                composable("Screen8") {
                    Screen8(onBackClick = {
                        navController.navigate("Screen2")
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
                text = "Jetpack Compose",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Jetpack Compose is a modern toolkit for building native Android applications using a declarative programming approach.",
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
                Text(text = "I'm Ready")
            }
        }
    }
}

@Composable
fun Screen2(onTextClick: () -> Unit, onImageClick: () -> Unit = {}, onTextFieldClick : () -> Unit = {}, onPassFieldClick : () -> Unit = {}, onRowClick : () -> Unit = {}, onColumnClick : () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 20.dp, end = 20.dp)
        ) {
            Text(
                text = "UI Components List",
                color = Color.Blue,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(10.dp))

            GroupTitle(
                title = "Display"
            )
            ComponentItem(
                title = "Text",
                description = "Displays text",
                onClick = onTextClick
            )
            ComponentItem(
                title = "Image",
                description = "Displays an image",
                onClick = onImageClick
            )
            Spacer(modifier = Modifier.height(10.dp))

            GroupTitle(
                title = "Input"
            )
            ComponentItem(
                title = "TextField",
                description = "Input field for text",
                onClick = onTextFieldClick
            )
            ComponentItem(
                title = "PasswordField",
                description = "Input field for passwords",
                onClick = onPassFieldClick
            )
            Spacer(modifier = Modifier.height(10.dp))

            GroupTitle(
                title ="Layout"
            )
            ComponentItem(
                title = "Column",
                description = "Arranges elements vertically",
                onClick = onColumnClick
            )
            ComponentItem(
                title = "Row",
                description = "Arranges elements horizontally",
                onClick = onRowClick
            )
            ComponentItem(
                title = "Tự tìm hiểu",
                description = "Tìm ra tất cả các thành phần UI cơ bản"
            )
        }
    }
}

@Composable
fun GroupTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 3.dp)
    )
}

@Composable
fun ComponentItem(title: String, description: String, onClick: (() -> Unit)? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFFE3F2FD), shape = RoundedCornerShape(8.dp))
            .padding(10.dp)
            .let {
                if (onClick != null) it.clickable { onClick() } else it
            }
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen1() {
    Screen1(onReadyClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen2() {
    Screen2(onTextClick = {}, onImageClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen3() {
    Screen3(onBackClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen4() {
    Screen4(onBackClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen5() {
    Screen5(onBackClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen6() {
    Screen6(onBackClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen7() {
    Screen7(onBackClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen8() {
    Screen8(onBackClick = {})
}

@Composable
fun Screen3(onBackClick: () -> Unit = {}) {
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

            )  {
                    Text(
                        text = "<",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF2196F3),
                        modifier = Modifier.clickable { onBackClick() }

                    )

                    Text(
                        text = "Text Detail",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF2196F3),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "The",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "quick",
                            style = MaterialTheme.typography.titleLarge.copy(
                                textDecoration = TextDecoration.LineThrough
                            ),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Brown",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Yellow
                        )
                        }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "fox",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "j u m p s",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "over",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "the",
                            style = MaterialTheme.typography.titleLarge.copy(
                                textDecoration = TextDecoration.Underline
                            ),
                            color = Color.Black

                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "lazy",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            ),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "dog.",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun Screen4(onBackClick : () -> Unit = {}) {
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
                    text = "Images",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                        Image(
                            painter = painterResource(id = R.drawable.uth),
                            contentDescription = null,
                            modifier = Modifier
                                .size(300.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "https://giaothongvantaitphcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        Image(
                            painter = painterResource(id = R.drawable.uth2),
                            contentDescription = null,
                            modifier = Modifier
                                .size(300.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "In App",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black
                        )
                }
            }
        }
    }
}

@Composable
fun Screen5(onBackClick : () -> Unit = {}) {
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
                    text = "TextField",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                var text by remember { mutableStateOf("") }

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Thông tin nhập") },
                        modifier = Modifier.fillMaxWidth(0.8f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = text,
                        color = Color.Red,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}

@Composable
fun Screen6(onBackClick : () -> Unit = {}) {
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
                    text = "PasswordField",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                var text by remember { mutableStateOf("") }

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Thông tin nhập") },
                        modifier = Modifier.fillMaxWidth(0.8f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = text,
                        color = Color.Red,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}

@Composable
fun Screen7(onBackClick : () -> Unit = {}) {
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
                    text = "Row Layout",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top=36.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(60.dp)
                            .background(
                                color = Color(0xFFF2F3F7),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFB3C8F9))
                            )
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF4285F4))
                            )
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFB3C8F9))
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(60.dp)
                            .background(
                                color = Color(0xFFF2F3F7),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFB3C8F9))
                            )
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF4285F4))
                            )
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFB3C8F9))
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(60.dp)
                            .background(
                                color = Color(0xFFF2F3F7),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    )   {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFB3C8F9))
                            )
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF4285F4))
                            )
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFB3C8F9))
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(60.dp)
                            .background(
                                color = Color(0xFFF2F3F7),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFB3C8F9))
                            )
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF4285F4))
                            )
                            Box(
                                modifier = Modifier
                                    .size(80.dp, 40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFB3C8F9))
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Screen8(onBackClick : () -> Unit = {}) {
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
                    text = "Column Layout",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
            Box (
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .background(
                                color = Color(0xFFF2F3F7),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        Box (
                            modifier = Modifier
                                .size(80.dp, 40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFB3C8F9))
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .background(
                                color = Color(0xFFF2F3F7),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        Box (
                            modifier = Modifier
                                .size(80.dp, 40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFB3C8F9))
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .background(
                                color = Color(0xFFF2F3F7),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        Box (
                            modifier = Modifier
                                .size(80.dp, 40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFB3C8F9))
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .background(
                                color = Color(0xFFF2F3F7),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        Box (
                            modifier = Modifier
                                .size(80.dp, 40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFB3C8F9))
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .background(
                                color = Color(0xFFF2F3F7),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        Box (
                            modifier = Modifier
                                .size(80.dp, 40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFB3C8F9))
                        )
                    }
                }
            }
        }
    }
}
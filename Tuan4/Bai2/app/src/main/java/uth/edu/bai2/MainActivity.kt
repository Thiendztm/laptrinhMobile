package uth.edu.bai2

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uth.edu.bai2.ui.theme.Bai2Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import android.R.attr.shape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation()
        }
    }
}

@Composable
fun ForgetPass(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(top = 150.dp, bottom = 32.dp, start = 8.dp , end = 8.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.uthlogo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(130.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "SmartTasks",
                color = Color(0xFF80ADD9),
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Forget Password?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Enter your email, we will sen you a verification code",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Your Email") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = "email",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("verifyCode") },
                modifier = Modifier
                    .width(350.dp)
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun VerifyCode(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(top = 70.dp, bottom = 32.dp, start = 8.dp , end = 8.dp)
            .fillMaxSize()
    ) {
        Column {
            Button(
                onClick = {navController.navigate("forgetPass")},
                modifier = Modifier
                    .size(40.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F87C4)
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "<",
                    color = Color.White,
                    fontSize = 26.sp
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.uthlogo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(130.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "SmartTasks",
                color = Color(0xFF80ADD9),
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Verify Code",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Enter the code we just send you on your register Email",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                color = Color(0xFF9CACBA),
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {navController.navigate("newPass")},
                modifier = Modifier
                    .width(350.dp)
            ) {
                Text(
                    text = "Next"
                    ,color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun NewPass(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(top = 70.dp, bottom = 32.dp, start = 8.dp, end = 8.dp)
            .fillMaxSize()
    ) {
        Column {
            Button(
                onClick = {navController.navigate("verifyCode")},
                modifier = Modifier
                    .size(40.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F87C4)
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "<",
                    color = Color.White,
                    fontSize = 26.sp
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.uthlogo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(130.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "SmartTasks",
                color = Color(0xFF80ADD9),
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Create new password",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Your new password must be different from previous used password",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Password") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = "password",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Confirm Password") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = "email",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {navController.navigate("confirm")},
                modifier = Modifier
                    .width(350.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F87C4)
                )
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun Confirm(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(top = 70.dp, bottom = 32.dp, start = 8.dp, end = 8.dp)
            .fillMaxSize()
    ) {
        Column {
            Button(
                onClick = {navController.navigate("newPass")},
                modifier = Modifier
                    .size(40.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F87C4)
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "<",
                    color = Color.White,
                    fontSize = 26.sp
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.uthlogo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(130.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "SmartTasks",
                color = Color(0xFF80ADD9),
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Confirm",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "We are here to help you!",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Enter your email") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.user1),
                        contentDescription = "user",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("........") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = "email",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("*************") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = "pass",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {navController.navigate("forgetPass")},
                modifier = Modifier
                    .width(350.dp)
            ) {
                Text(
                    text = "Submit",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "forgetPass") {
        composable("forgetPass") { ForgetPass(navController) }
        composable("verifyCode") { VerifyCode(navController) }
        composable("newPass") { NewPass(navController) }
        composable("confirm") { Confirm(navController) }
    }
}
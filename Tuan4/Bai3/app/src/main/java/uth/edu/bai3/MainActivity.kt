package uth.edu.bai3

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uth.edu.bai3.ui.theme.Bai3Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import android.util.Log
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var date by remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("confirmScreen/{email}/{name}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val encodedName = backStackEntry.arguments?.getString("name") ?: ""
            val name = URLDecoder.decode(encodedName, StandardCharsets.UTF_8.toString())

            ConfirmScreen(
                navController = navController,
                email = email,
                name = name,
                date = date,
                onDateSelected = { newDate -> date = newDate }
            )
        }
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(top = 220.dp, bottom = 32.dp, start = 8.dp, end = 8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.uthlogo),
            contentDescription = "logo",
            modifier = Modifier.size(130.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "SmartTasks",
            color = Color(0xFF80ADD9),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = "A simple and effcient to-do app ",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(140.dp))
        Text(
            text = "Welcome",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ready to explore? log in to get started.",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(36.dp))
        Button(
            onClick = {
                scope.launch { signInWithGoogle(context, navController) }
            },
            modifier = Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF80ADD9)),
            shape = RoundedCornerShape(12.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "logo",
                Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "SIGN IN WITH GOOGLE",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ConfirmScreen(navController: NavHostController, email: String, name: String, date: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, mYear, mMonth, mDayOfMonth ->
            onDateSelected("$mDayOfMonth/${mMonth + 1}/$mYear")
        }, year, month, day
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, bottom = 36.dp, start = 24.dp, end = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier
                    .size(40.dp),
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(Color(0xFF80ADD9)),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "<",
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(100.dp))

            Text(
                text = "Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF80ADD9)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.rigbycat),
                contentDescription = "logo",
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Icon(
                painter = painterResource(id = R.drawable.camera),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-100).dp, y = 20.dp)
                    .padding(4.dp)
                    .size(30.dp)
            )
        }
        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "Name",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = name,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Email",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Date of Birth",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = date,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() },
            readOnly = true,
            placeholder = { Text("Select date") },
            enabled = false,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        Spacer(modifier = Modifier.height(150.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF80ADD9)),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text(
                text = "Back",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

private suspend fun signInWithGoogle(context: Context, navController: NavHostController) {
    val credentialManager = CredentialManager.create(context)

    val googleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId("364809078535-3kd97qm6q4s319hiopb1bp7hj6hd82rj.apps.googleusercontent.com")
        .build()

    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    try {
        val result = credentialManager.getCredential(context, request)
        val credential = result.credential

        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            try {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val idToken = googleIdTokenCredential.idToken

                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)

                Firebase.auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = Firebase.auth.currentUser
                            val userEmail = user?.email ?: ""
                            val userName = user?.displayName ?: ""
                            val encodedName = URLEncoder.encode(userName, StandardCharsets.UTF_8.toString())

                            navController.navigate("confirmScreen/$userEmail/$encodedName")
                        } else {
                            Log.e("Auth", "Firebase sign-in failed", task.exception)
                        }
                    }
            } catch (e: GoogleIdTokenParsingException) {
                Log.e("Auth", "Google ID token parsing failed", e)
            }
        } else {
            Log.e("Auth", "Unexpected credential type: ${credential::class.java.name}")
        }

    } catch (e: GetCredentialException) {
        Log.e("Auth", "GetCredentialException: $e")
    }
}

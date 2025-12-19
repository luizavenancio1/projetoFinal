package com.example.ambienta.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambienta.data.UserSession
import com.example.ambienta.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit
) {

    val userName by UserSession.userName.collectAsState()
    var editing by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf(userName) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meu Perfil 👤") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        containerColor = GreenBackground
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            /* 🔝 HEADER */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(GreenPrimary),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Surface(
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.25f),
                        modifier = Modifier.size(110.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = userName,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "Usuário do Ambienta 🌱",
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .offset(y = (-30).dp)
            ) {

                /* ✏️ EDITAR NOME */
                Card(
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {

                        Text(
                            text = "📄 Informações do Perfil",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = GreenPrimary
                        )

                        Spacer(Modifier.height(16.dp))

                        if (editing) {
                            OutlinedTextField(
                                value = newName,
                                onValueChange = { newName = it },
                                label = { Text("Nome") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(Modifier.height(12.dp))

                            Button(
                                onClick = {
                                    if (newName.isNotBlank()) {
                                        UserSession.setUserName(newName)
                                        editing = false
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = GreenPrimary
                                )
                            ) {
                                Text("Salvar")
                            }
                        } else {
                            Text("Nome: $userName")
                            Spacer(Modifier.height(12.dp))
                            OutlinedButton(
                                onClick = { editing = true },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = null)
                                Spacer(Modifier.width(8.dp))
                                Text("Editar nome")
                            }
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))

                /* 🔒 LOGOUT */
                Button(
                    onClick = {
                        UserSession.clear()
                        onLogout()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Icon(Icons.Default.Logout, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Sair da conta")
                }
            }
        }
    }
}

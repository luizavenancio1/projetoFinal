package com.example.ambienta.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambienta.ui.theme.GreenBackground
import com.example.ambienta.ui.theme.GreenPrimary
import com.example.ambienta.ui.theme.GreenLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onSuccess: () -> Unit
) {
    val logged by viewModel.logged.collectAsState()
    val error by viewModel.error.collectAsState()

    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    if (logged) onSuccess()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenBackground)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔰 ÍCONE / LOGO
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Usuário",
            tint = GreenPrimary,
            modifier = Modifier.size(96.dp)
        )

        Spacer(Modifier.height(12.dp))

        // 🌱 NOME DO APP
        Text(
            text = "Ambienta",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = GreenPrimary
        )

        Text(
            text = "Sustentabilidade no seu dia a dia 🌍",
            color = Color.DarkGray
        )

        Spacer(Modifier.height(24.dp))

        // 📘 CARD INFORMATIVO
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(GreenLight)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("O que você encontra no app", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("🌱 Dicas ecológicas diárias")
                Text("🌦️ Qualidade do ar em tempo real")
                Text("♻️ Consciência ambiental")
            }
        }

        Spacer(Modifier.height(24.dp))

        // 👤 CAMPO USUÁRIO
        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Usuário") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(Modifier.height(12.dp))

        // 🔒 CAMPO SENHA
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(Modifier.height(8.dp))

        // ❌ ERRO
        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        // 🚀 BOTÃO ENTRAR
        Button(
            onClick = { viewModel.login(user, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Entrar", fontSize = 18.sp)
        }

        Spacer(Modifier.height(16.dp))

        // ℹ️ TEXTO FINAL
        Text(
            text = "Projeto educacional • Android • Sustentabilidade",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

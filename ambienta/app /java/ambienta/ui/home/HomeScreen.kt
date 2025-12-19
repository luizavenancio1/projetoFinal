package com.example.ambienta.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ambienta.data.UserSession
import com.example.ambienta.ui.tips.TipsViewModel
import com.example.ambienta.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onTips: () -> Unit,
    onAir: () -> Unit,
    onProfile: () -> Unit,
    tipsViewModel: TipsViewModel
) {

    val userName by UserSession.userName.collectAsState()
    val tips by tipsViewModel.tips.collectAsState()

    val completed = tips.count { it.completed }
    val total = tips.size
    val progress = if (total == 0) 0f else completed.toFloat() / total.toFloat()
    val progressColor = progressColor(completed)

    Scaffold(containerColor = GreenBackground) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()) // ✅ TELA ROLÁVEL
        ) {

            /* 🔝 HEADER */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(GreenPrimary)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Surface(
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.2f),
                        modifier = Modifier
                            .size(90.dp)
                            .clickable { onProfile() } // Tela do perfil
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Perfil",
                            tint = Color.White,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp)
                                .clickable { onProfile() } // Tela do perfil
                        )
                    }


                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = userName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "Bem-vindo(a) ao Ambienta 🌱",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 14.sp
                    )
                }
            }

            /* 🔽 CONTEÚDO */
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 24.dp)
            ) {

                Text(
                    text = "Funcionalidades",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = GreenPrimary
                )

                Spacer(Modifier.height(20.dp))

                FeatureCard(
                    icon = Icons.Default.Public,
                    title = "Desafios Sustentáveis",
                    description = "Pequenas ações para um futuro melhor.",
                    color = GreenPrimary,
                    onClick = onTips
                )

                Spacer(Modifier.height(16.dp))

                FeatureCard(
                    icon = Icons.Default.Cloud,
                    title = "Clima Atual",
                    description = "Informações climáticas atualizadas.",
                    color = BlueAccent,
                    onClick = onAir
                )
                Spacer(Modifier.height(24.dp))

                /* 📊 PROGRESSO DO DIA */
                Card(
                    shape = RoundedCornerShape(3.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = progressColor.copy(alpha = 0.15f)
                    ),
                    elevation = CardDefaults.cardElevation(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "📊 Progresso do Dia",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(Modifier.height(16.dp))

                        DailyProgressChart(
                            completed = completed,
                            total = total,
                            color = progressColor
                        )

                        Spacer(Modifier.height(12.dp))

                        Text(
                            text = when {
                                completed <= 3 -> "Vamos começar! 🌱"
                                completed <= 5 -> "Bom progresso! 💪"
                                completed <= 8 -> "Quase lá! 🌍"
                                else -> "Excelente! Você fez a diferença hoje! 🟢"
                            },
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                }

                Spacer(Modifier.height(32.dp))

                Text(
                    text = "Projeto educacional • Sustentabilidade • Android",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

/* 📈 GRÁFICO */
@Composable
private fun DailyProgressChart(
    completed: Int,
    total: Int,
    color: Color
) {
    val progress = if (total == 0) 0f else completed.toFloat() / total.toFloat()

    Box(
        modifier = Modifier.size(130.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progress,
            strokeWidth = 12.dp,
            color = color,
            trackColor = Color.LightGray.copy(alpha = 0.3f),
            modifier = Modifier.fillMaxSize()
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$completed / $total",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "${(progress * 100).toInt()}%",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}

/* 🎨 COR DO PROGRESSO */
@Composable
private fun progressColor(done: Int): Color =
    when {
        done <= 3 -> MaterialTheme.colorScheme.error
        done <= 5 -> Color(0xFFFF9800)
        done <= 8 -> Color(0xFFFFEB3B)
        else -> Color(0xFF4CAF50)
    }

/* 🧩 CARD PADRÃO */
@Composable
private fun FeatureCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                shape = CircleShape,
                color = color.copy(alpha = 0.15f),
                modifier = Modifier.size(52.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = color,
                    modifier = Modifier.padding(12.dp)
                )
            }

            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

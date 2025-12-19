package com.example.ambienta.ui.tips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ambienta.ui.components.AmbientaTopBar
import com.example.ambienta.ui.theme.GreenBackground
import com.example.ambienta.ui.theme.GreenPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsScreen(
    viewModel: TipsViewModel,
    onBack: () -> Unit
) {
    val tips by viewModel.tips.collectAsState()
    val progress by viewModel.progress.collectAsState()

    Scaffold(
        topBar = {
            AmbientaTopBar(
                title = "Desafios Sustentáveis 🌱",
                onBack = onBack
            )
        },
        containerColor = GreenBackground
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            // 📊 PROGRESSO
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = GreenPrimary.copy(alpha = 0.1f))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Progresso do dia: $progress% 🌍")
                    Spacer(Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = progress / 100f,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // 🔄 RESET
            OutlinedButton(
                onClick = { viewModel.resetDailyTips() },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Reiniciar desafios")
            }

            Spacer(Modifier.height(16.dp))

            // 🌱 LISTA DE DICAS
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tips) { tip ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = tip.text,
                                modifier = Modifier.weight(1f)
                            )

                            Checkbox(
                                checked = tip.completed,
                                onCheckedChange = {
                                    viewModel.toggle(tip.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

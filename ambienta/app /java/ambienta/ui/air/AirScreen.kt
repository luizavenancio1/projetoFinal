package com.example.ambienta.ui.air

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirScreen(
    viewModel: AirViewModel,
    onBack: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clima em Palmares 🌦️") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /* 🌡️ CARD CLIMA */
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = state,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            /* 🗺️ IMAGEM DO MAPA */
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                AsyncImage(
                    model = "https://www.google.com/maps/vt/data=UbVt6DzPdvQtUPrtIsH7A_ezPYok3RgmsL9-2_q35VuP2weFOppQ16eY67G1yQxSKygyjzsxSV0vc4W9mx-SegMYScDfE_fvTdzQ9mB9nJNol7ecSDczV3lfGSAH-3y6F4B6sEd5J5pp8p0y0D6g8ZAdE8zCyZy6nbnlFiusxaYDjMz0O9NnizoJvnTT5vF3eFOD5b8YCnAmnDL8og3gjpo",
                    contentDescription = "Mapa da cidade de Palmares - PE",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "📍 Palmares – Pernambuco",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

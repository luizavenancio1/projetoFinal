package com.example.sustentabilidade.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sustentabilidade.vm.TipsViewModel
import com.example.sustentabilidade.vm.UiState
import androidx.compose.foundation.lazy.items

@Composable
fun TipsScreen(nav: NavController, vm: TipsViewModel = viewModel()) {
    val state by vm.state.collectAsState()

    when(state){
        is UiState.Loading -> CircularProgressIndicator()
        is UiState.Error -> Text("Error")
        is UiState.Success -> {
            val list = (state as UiState.Success).data
            LazyColumn {
                items(list){ tip ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { nav.navigate("detail/${tip.id}") }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = tip.title,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(tip.summary)
                        }
                    }
                }
            }
        }
    }
}

package com.ejemplo.rpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ejemplo.rpg.story.Engine
import com.ejemplo.rpg.story.GameState
import com.ejemplo.rpg.story.HistoriaPrueba

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val historia = HistoriaPrueba.obtenerNodos()
        val motor = Engine(historia)

        setContent {
            MaterialTheme {
                var estado by remember { mutableStateOf(GameState("inicio")) }
                var modoEditor by remember { mutableStateOf(false) }
                
                val nodoActual = historia[estado.currentNodeId]!!
                var textoEditado by remember { mutableStateOf(nodoActual.text) }

                LaunchedEffect(estado.currentNodeId) {
                    textoEditado = nodoActual.text
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(if (modoEditor) "Modo Editor" else "Modo Jugador") },
                            actions = {
                                Button(
                                    onClick = { modoEditor = !modoEditor },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(if (modoEditor) "▶ Jugar" else "✏️ Editar")
                                }
                            }
                        )
                    }
                ) { padding ->
                    Column(modifier = Modifier.padding(padding).padding(24.dp).fillMaxSize()) {
                        
                        if (modoEditor) {
                            Text("Editando nodo: ${nodoActual.id}", style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = textoEditado,
                                onValueChange = { textoEditado = it },
                                modifier = Modifier.fillMaxWidth().weight(1f),
                                label = { Text("Texto del nodo") }
                            )
                        } else {
                            Text(
                                text = textoEditado,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f)
                            )
                            
                            nodoActual.choices.forEach { eleccion ->
                                Button(
                                    onClick = { estado = motor.makeChoice(estado, eleccion) },
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                                ) {
                                    Text(eleccion.text)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

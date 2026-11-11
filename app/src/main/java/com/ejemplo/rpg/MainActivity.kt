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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Cargamos la historia desde el archivo separado
        val historia = HistoriaPrueba.obtenerNodos()
        val motor = Engine(historia)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var estado by remember { mutableStateOf(GameState("inicio")) }
                    val nodoActual = historia[estado.currentNodeId]!!

                    Column(modifier = Modifier.padding(24.dp).fillMaxSize()) {
                        Text(
                            text = nodoActual.text,
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

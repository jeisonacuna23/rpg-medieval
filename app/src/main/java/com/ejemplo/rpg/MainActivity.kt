package com.ejemplo.rpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ejemplo.rpg.story.Choice
import com.ejemplo.rpg.story.Engine
import com.ejemplo.rpg.story.GameState
import com.ejemplo.rpg.story.StoryNode

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Creamos una mini historia de prueba en memoria
        val inicio = StoryNode(
            id = "inicio",
            text = "Estás en el pueblo. Frente a ti hay un camino al bosque oscuro.",
            choices = listOf(
                Choice("Entrar al bosque", "bosque"), 
                Choice("Quedarte en el pueblo", "pueblo")
            )
        )
        val bosque = StoryNode(
            id = "bosque",
            text = "El bosque es tenebroso. Escuchas aullidos a lo lejos.",
            choices = listOf(Choice("Volver al pueblo", "inicio"))
        )
        val pueblo = StoryNode(
            id = "pueblo",
            text = "Decides no arriesgarte. Te vas a dormir.",
            choices = listOf(Choice("Volver al inicio", "inicio"))
        )

        val historia = mapOf("inicio" to inicio, "bosque" to bosque, "pueblo" to pueblo)
        val motor = Engine(historia)

        // 2. Conectamos todo a la interfaz (Compose)
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

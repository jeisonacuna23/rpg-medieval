package com.ejemplo.rpg.story

import org.junit.Assert.assertEquals
import org.junit.Test

class EngineTest {

    @Test
    fun testAvanzarEntreNodos() {
        val nodoInicio = StoryNode(
            id = "inicio",
            text = "Estás en el pueblo.",
            choices = listOf(Choice("Ir al bosque", "bosque"))
        )
        val nodoBosque = StoryNode(
            id = "bosque",
            text = "Estás en el bosque.",
            choices = listOf(Choice("Volver", "inicio"))
        )

        val historia = mapOf("inicio" to nodoInicio, "bosque" to nodoBosque)
        val motor = Engine(historia)
        var estado = GameState(currentNodeId = "inicio")

        // Hacemos la elección
        estado = motor.makeChoice(estado, nodoInicio.choices.first())

        // Verificamos que estamos en el nodo correcto
        assertEquals("bosque", estado.currentNodeId)
    }
}

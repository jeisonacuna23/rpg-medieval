package com.ejemplo.rpg.story

object HistoriaPrueba {
    fun obtenerNodos(): Map<String, StoryNode> {
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

        return mapOf("inicio" to inicio, "bosque" to bosque, "pueblo" to pueblo)
    }
}

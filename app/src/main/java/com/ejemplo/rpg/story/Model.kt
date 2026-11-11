package com.ejemplo.rpg.story

data class Choice(
    val text: String,
    val targetNodeId: String
)

data class StoryNode(
    val id: String,
    val text: String,
    val choices: List<Choice> = emptyList()
)

data class GameState(
    val currentNodeId: String
)

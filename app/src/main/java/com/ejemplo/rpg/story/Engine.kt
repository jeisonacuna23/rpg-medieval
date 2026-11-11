package com.ejemplo.rpg.story

class Engine(private val nodes: Map<String, StoryNode>) {
    
    fun makeChoice(state: GameState, choice: Choice): GameState {
        require(nodes.containsKey(choice.targetNodeId)) { 
            "El nodo destino '${choice.targetNodeId}' no existe en la historia." 
        }
        return state.copy(currentNodeId = choice.targetNodeId)
    }
}

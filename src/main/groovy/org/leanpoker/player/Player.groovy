package org.leanpoker.player

class Player {

    static final String VERSION = 'Great and Grandious Groovy'

	static GameStateHelper helper

    static int betRequest(def gameState) {
		helper = new GameStateHelper(gameState: gameState)
        0
    }

    static void showdown(def gameState) {
    }
}

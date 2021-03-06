package org.leanpoker.player

class GameStateHelper {

	def gameState

	def getUs() {
		gameState.players.find { it.version == Player.VERSION }
	}

	int getCallAmount() {
		gameState.current_buy_in - us.bet
	}

	int getMinimumRaise() {
		gameState.minimum_raise
	}

    boolean getDuel() {
        gameState.players.count { it.status == "active" } == 2
    }
}

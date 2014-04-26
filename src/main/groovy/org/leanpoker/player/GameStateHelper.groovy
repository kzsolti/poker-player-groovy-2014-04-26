package org.leanpoker.player

class GameStateHelper {

	final def gameState

	def getUs() {
		gameState.players.find { it.version == Player.VERSION }
	}

	int getCallAmount() {
		gameState.current_buy_in - us.bet
	}

	int getMinimumRaise() {
		gameState.minimum_raise
	}
}

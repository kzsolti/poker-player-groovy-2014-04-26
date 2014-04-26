package org.leanpoker.player

import org.leanpoker.player.Player

class GameStateHelper {

	final def gameState

	def getUs() {
		gameState.players.find { it.version == Player.VERSION }
	}

	int getCallAmount() {
		gameState.current_buy_in - us.bet
	}
}

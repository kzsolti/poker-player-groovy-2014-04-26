package org.leanpoker.player

class Player {

    static final String VERSION = 'Great and Grandious Groovy'

	static GameStateHelper helper

    static int betRequest(def gameState) {
		helper = new GameStateHelper(gameState: gameState)
		switch (CardEvaluator.evaluate(helper.us.hole_cards)) {
			case 0:
				return 0
			case 1..2:
				return helper.callAmount
			case 3..10:
				return helper.callAmount + helper.minimumRaise
		}
    }

    static void showdown(def gameState) {
    }
}

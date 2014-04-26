package org.leanpoker.player

class Player {

    static final String VERSION = 'Great and Grandious Groovy 0.3'

	static GameStateHelper helper

    static int betRequest(def gameState) {
		helper = new GameStateHelper(gameState: gameState)

		// if the stakes are right, we bet as normal
		switch (CardEvaluator.evaluate(helper.us.hole_cards)) {
			case 0:
				return 0
			case 1..2:
				// if the bet is too high, we must tread cautiously
				if (helper.us.stack > 50) {
					if (helper.callAmount > helper.us.stack / 2) {
						return 0 // we fold, or call
					}
					if (helper.callAmount > helper.us.stack / 3) {
						return helper.callAmount // we call
					}
				} else {
					return helper.callAmount
				}
			case 3..10:
				return helper.us.stack
//				return helper.callAmount + helper.minimumRaise
		}
    }

    static void showdown(def gameState) {
    }
}

package org.leanpoker.player

class Player {

    static final String VERSION = 'Great and Grandious Groovy 0.6'

	static GameStateHelper helper

	static int smallBetCount = 0

    static int betRequest(def gameState) {
		helper = new GameStateHelper(gameState: gameState)

		// if the stakes are right, we bet as normal
		switch (CardEvaluator.evaluate(helper.us.hole_cards + helper.gameState.community_cards)) {
			case 0..4:
				return 0
			case 5..10:
				// if the bet is too high, we must tread cautiously
				if (helper.us.stack > 50 && !helper.duel) {
					if (helper.callAmount > helper.us.stack / 2) {
                        return 0 // we fold, or call
					}
					if (helper.callAmount > helper.us.stack / 3) {
						return helper.callAmount // we call
					}
					if (smallBetCount > 4) {
						return 0
					}
                    smallBetCount++
					return helper.callAmount + helper.minimumRaise
				} else {
					return helper.callAmount
				}
            case 11..19:
                return helper.callAmount + helper.minimumRaise * 2
			case 20..100:
				return helper.us.stack
//				return helper.callAmount + helper.minimumRaise
		}
    }

    static void showdown(def gameState) {
		smallBetCount = 0
    }
}

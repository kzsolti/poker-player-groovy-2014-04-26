package org.leanpoker.player

class Player {

    static final String VERSION = 'Great and Grandious Groovy 0.7'

	static GameStateHelper helper

	static int smallBetCount = 0

    static int betRequest(def gameState) {
		helper = new GameStateHelper(gameState: gameState)

        Log.log "Hole cards: $helper.us.hole_cards"

		// if the stakes are right, we bet as normal
		switch (CardEvaluator.evaluate(helper.us.hole_cards + helper.gameState.community_cards)) {
			case 0..4:
                Log.log "PATH: Fold"
                return 0
			case 5..10:
				// if the bet is too high, we must tread cautiously
				if (helper.us.stack > 50 && !helper.duel) {
					if (helper.callAmount > helper.us.stack * 0.50) {
                        Log.log "PATH: Fold 50%"
                        return 0 // we fold, or call
					}
					if (helper.callAmount > helper.us.stack * 0.33) {
                        Log.log "PATH: Call 33%"
						return helper.callAmount // we call
					}
					if (smallBetCount > 4) {
                        Log.log "PATH: Fold small bet count"
						return 0
					}
                    smallBetCount++
                    Log.log "PATH: callAmount minimumRaise"
					return helper.callAmount + helper.minimumRaise
				} else {
                    Log.log "PATH: callAmount"
					return helper.callAmount
				}
            case 11..19:
                Log.log "helper.callAmount + helper.minimumRaise * 2"
                if (helper.minimumRaise < helper.us.stack / 2)
                    return helper.callAmount + helper.minimumRaise

                return helper.callAmount
			case 20..100:
                Log.log "helper.us.stack"
				return helper.us.stack
//				return helper.callAmount + helper.minimumRaise
		}
    }

    static void showdown(def gameState) {
		smallBetCount = 0
    }
}

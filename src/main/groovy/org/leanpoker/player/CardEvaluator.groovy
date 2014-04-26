package org.leanpoker.player

class CardEvaluator {
    // card values             0    1    2    3    4    5    6    7    8    9    10   11   12
    static final def CARDS = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]

    static def evaluate(List<Object> holeCards) {
        def points = 0;

        points += checkForPair(holeCards)
        points += checkForBigCards(holeCards)
        points += checkForBiggerCards(holeCards)
        points += checkForSameSuits(holeCards)

        points
    }

    private static int checkForPair(List<Object> holeCards) {
        holeCards[0].rank == holeCards[1].rank ? 3 : 0
    }

    private static int checkForBigCards(List<Object> holeCards) {
        holeCards.count { CARDS.indexOf(it.rank) > 5 }
    }

    private static int checkForBiggerCards(List<Object> holeCards) {
        holeCards.count { CARDS.indexOf(it.rank) > 8 }
    }

    private static int checkForSameSuits(List<Object> holeCards) {
        holeCards.countBy{ it.suit }.size() == 1 ? 2 : 0
    }
}

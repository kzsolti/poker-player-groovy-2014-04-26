package org.leanpoker.player

class CardEvaluator {
    // card values             0    1    2    3    4    5    6    7    8    9    10   11   12
    static final def CARDS = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]

    static def evaluate(List<Object> holeCards) {
        def points = 0;

        points += checkForPair(holeCards)
        points += checkForBigCards(holeCards)
        points += checkForBiggerCards(holeCards)

        points
    }

    private static int checkForPair(List<Object> holeCards) {
        if (holeCards[0].rank == holeCards[1].rank) {
            return 3
        };

        0
    }

    private static int checkForBigCards(List<Object> holeCards) {
        if (CARDS.indexOf(holeCards[0].rank) >= 6 || CARDS.indexOf(holeCards[1].rank) >= 6)
            return 1;

        0
    }

    private static int checkForBiggerCards(List<Object> holeCards) {
        if (CARDS.indexOf(holeCards[0].rank) > 9 || CARDS.indexOf(holeCards[1].rank) > 9)
            return 1;

        0
    }
}

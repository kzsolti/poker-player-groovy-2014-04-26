package org.leanpoker.player

class CardEvaluator {
    // card values             0    1    2    3    4    5    6    7    8    9    10   11   12
    static final def CARDS = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]

    static final def VALUE_PAIR        = 3
    static final def VALUE_BIG_CARD    = 1
    static final def VALUE_BIGGER_CARD = 2
    static final def VALUE_FLUSH       = 2

    static def evaluate(List<Object> holeCards) {
        def points = 0;

        points += checkForSameRank(holeCards)
        points += checkForBigCards(holeCards)
        points += checkForBiggerCards(holeCards)
        points += checkForSameSuits(holeCards)

        points
    }

    private static int checkForSameRank(List<Object> holeCards) {
//        holeCards[0].rank == holeCards[1].rank ? VALUE_PAIR : 0
        def sameCards = holeCards.countBy{ it.rank }

        def pairs = sameCards.count { rank, count -> count == 2 }
        def threes = sameCards.count { rank, count -> count == 3 }
        def fours = sameCards.count { rank, count -> count == 4 }

        if (fours > 0) return 50
        if (threes > 0) return 25
        if (pairs > 1) return 20
        if (pairs > 0) return 5

        0
    }

    private static int checkForBigCards(List<Object> holeCards) {
        holeCards.count { CARDS.indexOf(it.rank) > 5 } * VALUE_BIG_CARD
    }

    private static int checkForBiggerCards(List<Object> holeCards) {
        holeCards.count { CARDS.indexOf(it.rank) > 8 } * (VALUE_BIGGER_CARD - VALUE_BIG_CARD)
    }

    private static int checkForSameSuits(List<Object> holeCards) {
        holeCards.countBy{ it.suit }.size() == 1 ? VALUE_FLUSH : 0
    }
}

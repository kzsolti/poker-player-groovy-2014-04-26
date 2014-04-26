package org.leanpoker.player

class CardEvaluator {
    static final def CARDS = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]

    static def evaluate(List<Object> holeCards) {
        def points = 0;

        if (holeCards[0].rank == holeCards[1].rank)
            points += 5;

        if (CARDS.indexOf(holeCards[0].rank) > 6 || CARDS.indexOf(holeCards[1].rank) > 6)
            points += 1;

        points
    }
}

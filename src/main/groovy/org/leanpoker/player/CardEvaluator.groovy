package org.leanpoker.player

class CardEvaluator {
    def cards = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]

    static def evaluate(List<Object> holeCards) {
        def points = 0;

        if (holeCards[0].rank == holeCards[1].rank)
            points += 2;

        points
    }
}

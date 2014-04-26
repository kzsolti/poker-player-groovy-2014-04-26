package org.leanpoker.player

import spock.lang.Specification
import spock.lang.Unroll

class CardEvaluatorTest extends Specification {
    def setupSpec() {
        Log.print = false
    }

    @Unroll
    def "should return correct value for each cases"() {
        expect:
        CardEvaluator.evaluate(getCards(cards)) == expected

        where:
        cards          || expected
        ["D3", "H7"]   || 0 // nothing
        ["C8", "S2"]   || CardEvaluator.VALUE_BIG_CARD // one big card
        ["C2", "DQ"]   || CardEvaluator.VALUE_BIGGER_CARD // one bigger card
        ["C5", "D5"]   || CardEvaluator.VALUE_PAIR // pair
        ["C8", "D8"]   || CardEvaluator.VALUE_PAIR + CardEvaluator.VALUE_BIG_CARD * 2 // big pair
        ["C10", "D10"] || CardEvaluator.VALUE_PAIR + CardEvaluator.VALUE_BIG_CARD * 2 // big pair
        ["CJ", "DJ"]   || CardEvaluator.VALUE_PAIR + CardEvaluator.VALUE_BIGGER_CARD * 2 // bigger pair
        ["C2", "C6"]   || CardEvaluator.VALUE_SAME_SUITS // flush
        ["C9", "C8"]   || CardEvaluator.VALUE_SAME_SUITS + CardEvaluator.VALUE_BIG_CARD * 2 // big flush
        ["CA", "CJ"]   || CardEvaluator.VALUE_SAME_SUITS + CardEvaluator.VALUE_BIGGER_CARD * 2 // bigger flush
        // Hands
        ["C2", "D4", "D2", "C3", "S3"] || CardEvaluator.VALUE_TWO_PAIRS // two pairs
        ["C2", "D4", "D2", "C3", "S2"] || CardEvaluator.VALUE_THREES // three of a kind
        ["C2", "D4", "D2", "CA", "S2"] || CardEvaluator.VALUE_THREES + CardEvaluator.VALUE_BIGGER_CARD // three of a kind with a big card
        ["C2", "D7", "S2", "D2", "H2"] || CardEvaluator.VALUE_FOURS // four of a kind
        ["C2", "D7", "S2", "D2", "H7"] || CardEvaluator.VALUE_FULL_HOUSE // full house
    }

    private ArrayList<LinkedHashMap<String, String>> getCards(List<String> cards) {
        def suits = [
            "D": "diamonds",
            "H": "hearts",
            "C": "clubs",
            "S": "spades"
        ]
        cards.collect { ["rank": it.substring(1), "suit": suits.get(it[0])] }
    }
}

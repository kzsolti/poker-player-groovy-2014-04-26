package org.leanpoker.player

import spock.lang.Specification
import spock.lang.Unroll

class CardEvaluatorTest extends Specification {
    @Unroll
    def "should return correct value for each cases"() {
        expect:
        CardEvaluator.evaluate(getCards(cards)) == expected

        where:
        cards          || expected
        ["D3", "H7"]   || 0 // nothing
        ["C8", "S2"]   || 1 // one big card
        ["C2", "DQ"]   || 2 // one bigger card
        ["C5", "D5"]   || 5 // pair
        ["C8", "D8"]   || 7 // big pair
        ["C10", "D10"] || 7 // big pair
        ["CJ", "DJ"]   || 9 // bigger pair
        ["C2", "C6"]   || 2 // flush
        ["C9", "C8"]   || 4 // big flush
        ["CA", "CJ"]   || 6 // bigger flush
        // Hands
        ["C2", "D4", "D2", "C3", "S2"] || 25 // three of a kind
        ["C2", "D4", "D2", "CA", "S2"] || 27 // three of a kind with a big card
        ["C2", "D7", "S2", "D2", "H2"] || 50 // four of a kind
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

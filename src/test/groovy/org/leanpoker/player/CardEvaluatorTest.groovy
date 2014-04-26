package org.leanpoker.player

import spock.lang.Specification
import spock.lang.Unroll

class CardEvaluatorTest extends Specification {
    @Unroll
    def "should return correct value for each cases"() {
        expect:
        CardEvaluator.evaluate(getCards(cards)) == expected

        where:
        cards        || expected
        ["D3", "H7"]   || 0 // nothing
        ["C8", "S2"]   || 1 // one big card
        ["C2", "DQ"]   || 2 // one bigger card
        ["C5", "D5"]   || 3 // pair
        ["C8", "D8"]   || 5 // big pair
        ["C10", "D10"] || 5 // big pair
        ["CJ", "DJ"]   || 7 // bigger pair
        ["C2", "C6"]   || 2 // flush
        ["C9", "C8"]   || 4 // big flush
        ["CA", "CJ"]   || 6 // bigger flush
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

package org.leanpoker.player

import spock.lang.Specification

class CardEvaluatorTest extends Specification {
    def "should return correct value for each cases"() {
        expect:
        CardEvaluator.evaluate(getCards(cards)) == expected

        where:
        cards      || expected
        ["3", "7"] || 0
        ["8", "2"] || 1
        ["2", "Q"] || 2
        ["5", "5"] || 3
        ["8", "8"] || 4
        ["Q", "Q"] || 5
    }

    private ArrayList<LinkedHashMap<String, String>> getCards(List<String> cards) {
        cards.collect { ["rank": it] }
    }
}

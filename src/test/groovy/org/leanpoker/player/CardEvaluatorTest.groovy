package org.leanpoker.player

import spock.lang.Specification

class CardEvaluatorTest extends Specification {
    def "should return 0 for no hands"() {
        given:
        def cards = getCards(["3", "7"])

        when:
        def points = CardEvaluator.evaluate(cards)

        then:
        points == 0
    }

    def "should return 1 for big cards"() {
        given:
        def cards = getCards(["8", "2"])

        when:
        def points = CardEvaluator.evaluate(cards)

        then:
        points == 1
    }

    private ArrayList<LinkedHashMap<String, String>> getCards(List<String> cards) {
        cards.collect { ["rank": it] }
    }
}

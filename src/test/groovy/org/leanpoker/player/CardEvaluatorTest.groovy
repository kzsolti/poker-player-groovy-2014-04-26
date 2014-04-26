package org.leanpoker.player

import spock.lang.Specification

class CardEvaluatorTest extends Specification {
    def "should return 0 for no hands"() {
        when:
        def points = CardEvaluator.evaluate([
                ["rank": "3"],
                ["rank": "5"]
        ])

        then:
        points == 0
    }
}

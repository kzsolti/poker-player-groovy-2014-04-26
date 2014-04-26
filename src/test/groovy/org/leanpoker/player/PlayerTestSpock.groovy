package org.leanpoker.player

import groovy.json.JsonSlurper
import spock.lang.Specification
/**
 * Java/Groovy unit test using the Spock framework.
 * https://code.google.com/p/spock/
 */
class PlayerTestSpock extends Specification {
    def setupSpec() {
        Log.print = false
    }

	def "The player should return a valid number"() {
		given:
		def gameState = new JsonSlurper().parseText(new File("src/test/resources/testdata.json").text)

		expect:
		Player.betRequest(gameState) >= 0
	}

	def "random cards does not fail"() {
		given:
		def gameState = new JsonSlurper().parseText(new File("src/test/resources/testdata.json").text)
        def helper = new GameStateHelper(gameState: gameState)
        def rand = new Random()

		when:
        (1..100000).each {
            helper.us.hole_cards = [getRandomCard(), getRandomCard()]
            helper.us.stack = rand.nextInt(1000)
            helper.us.bet = rand.nextInt(1000)

            gameState.minimum_raise = rand.nextInt(1000)
            gameState.current_buy_in = rand.nextInt(1000)
            gameState.community_cards = []

            if (rand.nextFloat() > 0.5) {
                (3..4 + rand.nextInt(3)).each {
                    gameState.community_cards += getRandomCard()
                }
            }

            Player.betRequest(gameState)
        }

        then:
        notThrown(Exception)
	}

    def getRandomCard() {
        def suits = ["hearts", "diamonds", "spades", "clubs"]
        def ranks = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]

        ['rank': ranks[new Random().nextInt(ranks.size())],
         'suit': suits[new Random().nextInt(suits.size())]]
    }
}
package org.leanpoker.player

import groovy.json.JsonSlurper
import spock.lang.Specification
/**
 * Java/Groovy unit test using the Spock framework.
 * https://code.google.com/p/spock/
 */
class PlayerTestSpock extends Specification {

	def "The player should return a valid number"() {
		given:
		def gameState = new JsonSlurper().parseText(new File("src/test/resources/testdata.json").text)

		expect:
		Player.betRequest(gameState) >= 0
	}
}
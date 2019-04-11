package singlematches

import domain.Match
import domain.Team
import domain.Winner
import spock.lang.Specification

class SingleMatchParserTest extends Specification {

    SingleMatchParser parser = new SingleMatchParser()

    def 'should parse match'() {
        given:
        def line = '20.05.18 18:00\tGórnik Zabrze\tWisła Kraków\t2:0'

        when:
        Match match = parser.parse(line);

        then:
        with(match) {
            date.toString("dd.MM.YY HH:mm") == '20.05.18 18:00'
            host == Team.GORNIK_ZABRZE
            opponent == Team.WISLA_KRAKOW
            with(result) {
                hostGoals == 2
                opponentGoals == 0
                winner == Winner.HOST
            }
        }
    }

}

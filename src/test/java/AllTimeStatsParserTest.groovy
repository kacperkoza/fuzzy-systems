import allstats.AllTimeStatsParser
import spock.lang.Specification

class AllTimeStatsParserTest extends Specification {

    AllTimeStatsParser statsParser = new AllTimeStatsParser()

    /*
        Klub            Sez  Mecze  Pkt        Bramki       Bilans Zwycięstwa           Rem Por
        Legia Warszawa	82	2249	3211	3757	2361	+1396	1105	435	661	9	563	580
    */

    def "should parse line with "() {
        given:
        String line = "Legia Warszawa	82	2249	3211	3757	2361	+1396	1105	435	661	9	563	580"

        when:
        def teamStats = statsParser.parse(line)

        then:
        teamStats.team.getName() == "Legia Warszawa"
        with(teamStats) {
            seasonsPlayed == 82
            points == 3211
        }
        with(teamStats.goals) {
            balance == 1396
            scored == 3757
            conceded == 2361
        }
        with(teamStats.matches) {
            played == 2248
            won == 1105
            drawn == 563
            lost == 580
        }
    }
}

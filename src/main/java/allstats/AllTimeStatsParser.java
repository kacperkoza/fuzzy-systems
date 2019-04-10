package allstats;

import domain.Goals;
import domain.Matches;
import domain.Team;
import domain.TeamStats;

class AllTimeStatsParser {

    TeamStats parse(String line) {
        String[] elements = line.split("\t");
        Team team = Team.from(elements[0]);
        int seasonsPlayed = integer(elements, 1);
        int points = integer(elements, 3);
        Goals goals = parseGoals(elements);
        Matches matches = parseMatches(elements);
        return new TeamStats(team, seasonsPlayed, points, goals, matches);
    }

    private Matches parseMatches(String[] elements) {
        int won = integer(elements, 7);
        int drawn = integer(elements, 11);
        int lost = integer(elements, 12);
        return new Matches(won + lost + drawn, won, lost, drawn);
    }

    private Goals parseGoals(String[] elements) {
        int scored = integer(elements, 4);
        int conceded = integer(elements, 5);
        return new Goals(scored, conceded, scored - conceded);
    }

    private int integer(String[] arr, int index) {
        return Integer.valueOf(arr[index]);
    }

}

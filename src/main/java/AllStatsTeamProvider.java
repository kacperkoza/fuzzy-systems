import domain.Team;
import domain.TeamStats;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AllStatsTeamProvider {

    private static final String ALL_TIME_STATS_FILE = "all_time.txt";

    private final Map<Team, TeamStats> teamToStats;

    public AllStatsTeamProvider() {
        LinesReader linesReader = new LinesReader();
        List<String> lines = linesReader.readLinesFromFile(ALL_TIME_STATS_FILE);
        AllTimeStatsParser statsParser = new AllTimeStatsParser();
        teamToStats = lines.stream()
                .map(statsParser::parse)
                .filter(stats -> stats.getTeam() != null)
                .collect(Collectors.toMap(
                        TeamStats::getTeam,
                        Function.identity()
                ));
    }

    public TeamStats getStats(Team team) {
        return teamToStats.get(team);
    }


}

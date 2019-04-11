package singlematches;

import domain.Match;
import domain.Team;
import util.LinesReader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SingleMatchProvider {

    private final List<Match> matches;

    public SingleMatchProvider() {
        LinesReader linesReader = new LinesReader();
        List<String> previous = linesReader.readLinesFromFile("17_18.txt");
        List<String> last = linesReader.readLinesFromFile("18_19.txt");
        SingleMatchParser parser = new SingleMatchParser();
        matches = Stream.concat(
                previous.stream().map(parser::parse),
                last.stream().map(parser::parse)
        ).collect(Collectors.toList());
    }

    public List<Match> getMatches(Team team) {
        return matches.stream()
                .filter(it -> it.getHost() == team)
                .collect(Collectors.toList());
    }

    public List<Match> getMatches(Team host, Team opponent) {
        return matches.stream()
                .filter(it -> it.getHost() == host && it.getOpponent() == opponent)
                .collect(Collectors.toList());
    }

}

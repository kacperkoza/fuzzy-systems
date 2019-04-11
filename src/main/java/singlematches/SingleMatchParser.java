package singlematches;

import domain.Match;
import domain.Result;
import domain.Team;
import domain.Winner;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class SingleMatchParser {

    private static final DateTimeFormatter PATTERN = DateTimeFormat.forPattern("dd.MM.YY HH:mm");

    public Match parse(String line){
        String[] elements = line.split("\t");
        DateTime date = getDate(elements);
        Team host = getHost(elements);
        Team opponent = getOpponent(elements);
        Result result = getResult(elements);
        return new Match(host, opponent, result,date);
    }

    private DateTime getDate(String[] elements) {
        return PATTERN.parseDateTime(elements[0]);
    }

    private Team getHost(String[] elements) {
        return Team.from(elements[1]);
    }

    private Team getOpponent(String[] elements) {
        return Team.from(elements[2]);
    }

    private Result getResult(String[] elements) {
        String[] goals = elements[3].split(":");
        int host = Integer.valueOf(goals[0]);
        int opponent = Integer.valueOf(goals[1]);
        Winner winner = evaluateWinner(host, opponent);
        return new Result(host,opponent,winner);
    }

    private Winner evaluateWinner(int host, int opponent) {
        if (host == opponent) return Winner.DRAW;
        if (host > opponent) return Winner.HOST;
        return Winner.OPPONENT;
    }

}

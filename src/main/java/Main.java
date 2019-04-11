import allstats.AllStatsTeamProvider;
import domain.Match;
import domain.Team;
import domain.TeamStats;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import singlematches.SingleMatchParser;
import singlematches.SingleMatchProvider;

import java.util.List;

public class Main {

    static AllStatsTeamProvider statsTeamProvider = new AllStatsTeamProvider();

    static SingleMatchProvider singleMatchProvider = new SingleMatchProvider();

    public static void main(String[] args) {
        String fileName = "games_played.tcl";
        FIS fis = FIS.load(fileName, true);
//        System.out.println(fis);
//        fis.getFunctionBlock("teamStrength");
//
//        TeamStats lech = statsTeamProvider.getStats(Team.LECH_POZNAN);
//        fis.setVariable("games_played", lech.getMatches().getPlayed());
//
//        TeamStats legia = statsTeamProvider.getStats(Team.LECH_POZNAN);
//        fis.setVariable("enemy_games_played", legia.getMatches().getPlayed());
//
//        fis.evaluate();
//
//        Variable games_played = fis.getVariable("games_played");
//        System.out.println(games_played);
//        Variable enemy_games_played = fis.getVariable("enemy_games_played");
//        System.out.println(enemy_games_played);
//
//        Variable chance_to_win = fis.getVariable("chance_to_win");
//        System.out.println(chance_to_win);
//
//        List<Match> matches= singleMatchProvider.getMatches(Team.LECH_POZNAN);
//        System.out.println(matches.toString());
//        JFuzzyChart.get().chart(fis);

        TeamStats lech = statsTeamProvider.getStats(Team.LECH_POZNAN);
        TeamStats arka = statsTeamProvider.getStats(Team.LEGIA_WARSZAWA);
        double lechStrength = lech.teamStrength(fis);
        System.out.println(lechStrength);
        double arkaStrength = arka.teamStrength(fis);
        System.out.println(arkaStrength);

        FunctionBlock prediction = fis.getFunctionBlock("prediction");
        prediction.setVariable("hostStrength", lechStrength);
        prediction.setVariable("guestStrength", arkaStrength);
        prediction.evaluate();
        JFuzzyChart.get().chart(prediction);
        Variable chanceToWin = prediction.getVariable("chance_to_win");
        System.out.println(chanceToWin.defuzzify());
    }
}

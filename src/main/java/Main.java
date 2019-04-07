import domain.Team;
import domain.TeamStats;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Main {

    static AllStatsTeamProvider statsTeamProvider = new AllStatsTeamProvider();

    public static void main(String[] args) {
        String fileName = "games_played.tcl";
        FIS fis = FIS.load(fileName, true);
        System.out.println(fis);


        TeamStats lech = statsTeamProvider.getStats(Team.LECH_POZNAN);
        TeamStats legia = statsTeamProvider.getStats(Team.LEGIA_WARSZAWA);
        fis.setVariable("games_played", lech.getMatches().getPlayed());
        fis.setVariable("enemy_games_played", legia.getMatches().getPlayed());
        fis.evaluate();

        Variable games_played = fis.getVariable("games_played");
        System.out.println(games_played);
        Variable enemy_games_played = fis.getVariable("enemy_games_played");
        System.out.println(enemy_games_played);

        double chance_to_win = fis.getVariable("chance_to_win").getLatestDefuzzifiedValue();
        System.out.println(chance_to_win);
    }

}

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

        TeamStats lech = statsTeamProvider.getStats(Team.LECH_POZNAN);
        TeamStats arka = statsTeamProvider.getStats(Team.LEGIA_WARSZAWA);
        double lechStrength = lech.teamStrength(fis);
        System.out.println(lechStrength);
        double miedz = arka.teamStrength(fis);
        System.out.println(miedz);

        FunctionBlock prediction = fis.getFunctionBlock("prediction");
        prediction.setVariable("hostStrength", lechStrength);
        prediction.setVariable("guestStrength", miedz);
        prediction.evaluate();
        JFuzzyChart.get().chart(prediction);
        Variable chanceToWin = prediction.getVariable("chance_to_win");
        System.out.println(chanceToWin.defuzzify());
    }
}

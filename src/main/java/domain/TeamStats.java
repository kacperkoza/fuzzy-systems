package domain;

import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

@Data
@AllArgsConstructor
public class TeamStats {
    @Nullable
    private final Team team;
    private final int seasonsPlayed;
    private final int points;
    private final Goals goals;
    private final Matches matches;

    public double teamStrength(FIS fis) {
        FunctionBlock teamStrengthCalculator = fis.getFunctionBlock("teamStrength");
        teamStrengthCalculator.setVariable("games_played", matches.getPlayed());
        teamStrengthCalculator.setVariable("won_to_all_ratio", (double) matches.getWon() / (double) matches.getPlayed());
        teamStrengthCalculator.evaluate();
        Variable strength = teamStrengthCalculator.getVariable("strength");
        return strength.defuzzify();
    }
}

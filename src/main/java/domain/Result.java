package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private final int hostGoals;
    private final int opponentGoals;
    private final Winner winner;
}

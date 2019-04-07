package domain;

import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamStats {
    @Nullable
    private final Team team;
    private final int seasonsPlayed;
    private final int points;
    private final Goals goals;
    private final Matches matches;
}

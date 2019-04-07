package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Matches {
    private final int played;
    private final int won;
    private final int lost;
    private final int drawn;
}

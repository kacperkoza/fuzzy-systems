package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@AllArgsConstructor
public class Match {
    private final Team host;
    private final Team opponent;
    private final Result result;
    private final DateTime date;
}

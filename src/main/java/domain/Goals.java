package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Goals {
    private int scored;
    private int conceded;
    private int balance;
}

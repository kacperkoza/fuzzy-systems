FUNCTION_BLOCK prediction

VAR_INPUT
    hostStrength: REAL; (* RANGE(-1 .. 1) *)
    guestStrength: REAL; (* RANGE(-1 .. 1) *)
END_VAR

VAR_OUTPUT
    chance_to_win:     REAL; (* RANGE(-1 .. 1) *)
END_VAR

FUZZIFY hostStrength
    TERM weak := (-1,0) (-1,1) (0,0);
    TERM average := (-0.5,0) (-0.25,1) (0.25, 1) (0.5, 0);
    TERM strong := (0,0) (1,1) (1,0);
END_FUZZIFY

FUZZIFY guestStrength
    TERM weak := (-1,0) (-1,1) (0,0);
    TERM average := (-0.5,0) (-0.25,1) (0.25, 1) (0.5, 0);
    TERM strong := (0,0) (1,1) (1,0);
END_FUZZIFY

FUZZIFY chance_to_win
    TERM guest := (-1, 0) (-1, 1) (0 ,0);
    TERM draw := (-0.25, 0) (0,1) (0.25,0);
    TERM host := (0,0) (1,1) (1,0);
END_FUZZIFY

DEFUZZIFY chance_to_win
    METHOD: COA;
END_DEFUZZIFY

RULEBLOCK first
    AND: MIN;
    ACCU:MAX;
    ACT : MIN;
    RULE 0: IF hostStrength IS weak AND guestStrength IS weak THEN chance_to_win IS draw;
    RULE 1: IF hostStrength IS weak AND guestStrength IS strong THEN chance_to_win IS guest;
    RULE 2: IF hostStrength IS strong AND guestStrength IS weak THEN chance_to_win IS host;
    RULE 3: IF hostStrength IS strong AND guestStrength IS strong THEN chance_to_win IS draw;
END_RULEBLOCK
END_FUNCTION_BLOCK



FUNCTION_BLOCK teamStrength

    VAR_INPUT
        games_played: REAL; (* RANGE(0 .. 2500) *)
        won_to_all_ratio: REAL; (* RANGE(-1 .. 1) *)
    END_VAR

    VAR_OUTPUT
        strength: REAL; (* RANGE(-1 .. 1) *)
    END_VAR

    FUZZIFY games_played
       TERM few := (0,0) (0,1) (500,1) (1000,0);
       TERM many := (1500,0) (2000,1) (2500,1) (2500,0);
    END_FUZZIFY

    FUZZIFY won_to_all_ratio
        TERM bad := (0,0) (0,1) (0.4,0);
        TERM average := (0.3,0) (0.4,1) (0.5, 1);
        TERM good := (0.3,0) (0.5,1) (0.5,0);
    END_FUZZIFY

    FUZZIFY strength
        TERM weak := (-1,0) (-1,1) (0,0);
        TERM average := (-0.5,0) (-0.25,1) (0.25, 1) (0.5, 0);
        TERM strong := (0,0) (1,1) (1,0);
    END_FUZZIFY

    DEFUZZIFY strength
        METHOD: COG;
    END_DEFUZZIFY

    RULEBLOCK awdawdklajl
        AND:MIN;
        ACCU:MAX;
        RULE 0: IF games_played IS few AND won_to_all_ratio IS bad THEN strength is weak;
        RULE 1: IF games_played IS few AND won_to_all_ratio IS good THEN strength IS average;
        RULE 2: IF games_played IS many AND won_to_all_ratio IS bad THEN strength IS weak;
        RULE 3: IF games_played IS many AND won_to_all_ratio IS good THEN strength IS strong;
    END_RULEBLOCK

END_FUNCTION_BLOCK

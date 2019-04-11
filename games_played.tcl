FUNCTION_BLOCK prediction

VAR_INPUT
    hostStrength: REAL; (* RANGE(-1 .. 1) *)
    guestStrength: REAL; (* RANGE(-1 .. 1) *)
END_VAR

VAR_OUTPUT
    chance_to_win:     REAL; (* RANGE(-1 .. 1) *)
END_VAR

FUZZIFY hostStrength
        TERM very_weak := (-1,0) (-1,1) (-0.8, 1) (-0.6, 0);
        TERM weak := (-0.8, 0) (-0.6, 1) (-0.4, 1) (-0.2, 0);
        TERM average := (-0.4,0) (-0.2,1) (0.2, 1) (0.4, 0);
        TERM strong := (0.2, 0) (0.4,1) (0.6, 1) (0.8, 0);
        TERM very_strong := (0.6, 0) (0.8, 1) (1,1) (1, 0);
END_FUZZIFY

FUZZIFY guestStrength
        TERM very_weak := (-1,0) (-1,1) (-0.8, 1) (-0.6, 0);
        TERM weak := (-0.8, 0) (-0.6, 1) (-0.4, 1) (-0.2, 0);
        TERM average := (-0.4,0) (-0.2,1) (0.2, 1) (0.4, 0);
        TERM strong := (0.2, 0) (0.4,1) (0.6, 1) (0.8, 0);
        TERM very_strong := (0.6, 0) (0.8, 1) (1,1) (1, 0);
END_FUZZIFY

FUZZIFY chance_to_win
    TERM guest := (-1, 0) (-1, 1) (-0.75 ,1) (-0.6, 0);
    TERM probably_guest := (-0.8, 0) (-0.6 , 1) (-0.35 ,1) (-0.15, 0);
    TERM draw := (-0.25, 0) (-0.1, 1) (0.1,1) (0.25, 0);
    TERM probably_host := (0.15, 0) (0.35, 1) (0.6,1) (0.8, 0);
    TERM host := (0.6,0) (0.75, 1) (1,1) (1, 0);
END_FUZZIFY

DEFUZZIFY chance_to_win
    METHOD: COG;
END_DEFUZZIFY

RULEBLOCK winning_team

    AND: MIN;
    ACCU:MAX;
    ACT : MIN;

    RULE 0: IF hostStrength IS very_weak AND guestStrength IS very_weak THEN chance_to_win IS draw ;
    RULE 1: IF hostStrength IS very_weak AND guestStrength IS weak THEN chance_to_win IS  draw ;
    RULE 2: IF hostStrength IS very_weak AND guestStrength IS average THEN chance_to_win IS probably_guest;
    RULE 3: IF hostStrength IS very_weak AND guestStrength IS strong THEN chance_to_win IS guest ;
    RULE 4: IF hostStrength IS very_weak AND guestStrength IS very_strong THEN chance_to_win IS guest ;

    RULE 5: IF hostStrength IS weak AND guestStrength IS very_weak THEN chance_to_win IS draw ;
    RULE 6: IF hostStrength IS weak AND guestStrength IS weak THEN chance_to_win IS draw ;
    RULE 7: IF hostStrength IS weak AND guestStrength IS average THEN chance_to_win IS probably_guest ;
    RULE 8: IF hostStrength IS weak AND guestStrength IS strong THEN chance_to_win IS probably_guest ;
    RULE 9: IF hostStrength IS weak AND guestStrength IS very_strong THEN chance_to_win IS guest ;

    RULE 10: IF hostStrength IS average AND guestStrength IS very_weak THEN chance_to_win IS probably_host ;
    RULE 11: IF hostStrength IS average AND guestStrength IS weak THEN chance_to_win IS probably_host ;
    RULE 12: IF hostStrength IS average AND guestStrength IS average THEN chance_to_win IS draw ;
    RULE 13: IF hostStrength IS average AND guestStrength IS strong THEN chance_to_win IS probably_guest ;
    RULE 14: IF hostStrength IS average AND guestStrength IS very_strong THEN chance_to_win IS guest  ;

    RULE 15: IF hostStrength IS strong AND guestStrength IS very_weak THEN chance_to_win IS host ;
    RULE 16: IF hostStrength IS strong AND guestStrength IS weak THEN chance_to_win IS host ;
    RULE 17: IF hostStrength IS strong AND guestStrength IS average THEN chance_to_win IS probably_host ;
    RULE 18: IF hostStrength IS strong AND guestStrength IS strong THEN chance_to_win IS draw ;
    RULE 19: IF hostStrength IS strong AND guestStrength IS very_strong THEN chance_to_win IS draw ;

    RULE 20: IF hostStrength IS very_strong AND guestStrength IS very_weak THEN chance_to_win IS host ;
    RULE 21: IF hostStrength IS very_strong AND guestStrength IS weak THEN chance_to_win IS host ;
    RULE 22: IF hostStrength IS very_strong AND guestStrength IS average THEN chance_to_win IS host ;
    RULE 23: IF hostStrength IS very_strong AND guestStrength IS strong THEN chance_to_win IS probably_host ;
    RULE 24: IF hostStrength IS very_strong AND guestStrength IS very_strong THEN chance_to_win IS draw ;

END_RULEBLOCK
END_FUNCTION_BLOCK





FUNCTION_BLOCK teamStrength

    VAR_INPUT
        games_played: REAL; (* RANGE(0 .. 2400) *)
        won_to_all_ratio: REAL; (* RANGE(0 .. 1) *)
    END_VAR

    VAR_OUTPUT
        strength: REAL; (* RANGE(-1 .. 1) *)
    END_VAR

    FUZZIFY games_played
       TERM few := (0,0) (0,1) (900,1) (1300,0);
       TERM avg := (600,0) (1000,1) (1400,1) (1800,0);
       TERM many := (1100,0) (1700,1) (2400,1) (2400,0);
    END_FUZZIFY

    FUZZIFY won_to_all_ratio
        TERM very_bad := (0, 0) (0, 1) (0.25, 1) (0.3, 0);
        TERM bad := (0.25,0) (0.3,1) (0.35,0);
        TERM average := (0.3,0) (0.35,1) (0.4, 0);
        TERM good := (0.35,0) (0.4,1) (0.5, 1) (0.60,0);
        TERM very_good := (0.4, 0) (0.60,1) (1, 1) (1, 0);
    END_FUZZIFY

    FUZZIFY strength
        TERM very_weak := (-1,0) (-1,1) (-0.8, 1) (-0.6, 0);
        TERM weak := (-0.8, 0) (-0.6, 1) (-0.4, 1) (-0.2, 0);
        TERM average := (-0.4,0) (-0.2,1) (0.2, 1) (0.4, 0);
        TERM strong := (0.2, 0) (0.4,1) (0.6, 1) (0.8, 0);
        TERM very_strong := (0.6, 0) (0.8, 1) (1,1) (1, 0);
    END_FUZZIFY

    DEFUZZIFY strength
        METHOD: COA;
    END_DEFUZZIFY

    RULEBLOCK team_strength_rules

        AND:MIN;
        ACCU:MAX;

        RULE 0: IF games_played IS few AND won_to_all_ratio IS very_bad THEN strength is very_weak;
        RULE 0: IF games_played IS few AND won_to_all_ratio IS bad THEN strength is very_weak;
        RULE 0: IF games_played IS few AND won_to_all_ratio IS average THEN strength is weak;
        RULE 0: IF games_played IS few AND won_to_all_ratio IS good THEN strength is average;
        RULE 0: IF games_played IS few AND won_to_all_ratio IS very_good THEN strength is strong;

        RULE 0: IF games_played IS avg AND won_to_all_ratio IS very_bad THEN strength is very_weak;
        RULE 0: IF games_played IS avg AND won_to_all_ratio IS bad THEN strength is weak;
        RULE 0: IF games_played IS avg AND won_to_all_ratio IS average THEN strength is average;
        RULE 0: IF games_played IS avg AND won_to_all_ratio IS good THEN strength is average;
        RULE 0: IF games_played IS avg AND won_to_all_ratio IS very_good THEN strength is strong;

        RULE 0: IF games_played IS many AND won_to_all_ratio IS very_bad THEN strength is weak;
        RULE 0: IF games_played IS many AND won_to_all_ratio IS bad THEN strength is average;
        RULE 0: IF games_played IS many AND won_to_all_ratio IS average THEN strength is strong;
        RULE 0: IF games_played IS many AND won_to_all_ratio IS good THEN strength is strong;
        RULE 0: IF games_played IS many AND won_to_all_ratio IS very_good THEN strength is very_strong;
    END_RULEBLOCK

END_FUNCTION_BLOCK

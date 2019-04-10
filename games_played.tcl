FUNCTION_BLOCK

VAR_INPUT
    games_played:       REAL; (* RANGE(0 .. 2500) *)
    enemy_games_played: REAL; (* RANGE(0 .. 2500) *)
END_VAR

VAR_OUTPUT
    chance_to_win:     REAL; (* RANGE(-1 .. 1) *)
END_VAR

FUZZIFY games_played
   TERM few := (0,0) (0,1) (500,1) (1000,0);
   TERM many := (1500,0) (2000,1) (2500,1) (2500,0);
END_FUZZIFY

FUZZIFY enemy_games_played
   TERM few := (0,0) (0,1) (500,1) (1000,0);
   TERM many := (1500,0) (2000,1) (2500,1) (2500,0);
END_FUZZIFY

FUZZIFY chance_to_win
   TERM guest := (-1, 0) (-1, 1) (0, 0);
   TERM draw := (-0.5, 0) (0, 1) (0.5 , 0);
   TERM host := (0, 0) (1, 1) (1, 0);
END_FUZZIFY

DEFUZZIFY chance_to_win
    METHOD: COG;
END_DEFUZZIFY

RULEBLOCK first
AND:MIN;
ACCU:MAX;
    RULE 0: IF games_played IS few AND enemy_games_played IS few THEN chance_to_win IS draw;
    RULE 1: IF games_played IS many AND enemy_games_played IS few THEN chance_to_win IS host;
    RULE 2: IF games_played IS few AND enemy_games_played IS many THEN chance_to_win IS guest;
    RULE 3: IF games_played IS many AND enemy_games_played IS many THEN chance_to_win IS draw;
END_RULEBLOCK
END_FUNCTION_BLOCK

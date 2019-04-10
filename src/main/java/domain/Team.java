package domain;

import java.util.Arrays;

public enum Team {
    LECHIA_GDANSK("Lechia Gdańsk"),
    LEGIA_WARSZAWA("Legia Warszawa"),
    PIAST_GLIWICE("Piast Gliwice"),
    ZAGLEBIE_LUBIN("Zagłębie Lubin"),
    LECH_POZNAN("Lech Poznań"),
    POGON_SZCZECIN("Pogoń Szczecin"),
    JAGIELLONIA_BIALYSTOK("Jagiellonia Białystok"),
    WISLA_KRAKOW("Wisła Kraków"),
    KORONA_KIELCE("Korona Kielce"),
    SLASK_WROCLAW("Śląsk Wrocław"),
    MIEDZ_LEGNICA("Miedź Legnica"),
    GORNIK_ZABRZE("Górnik Zabrze"),
    ARKA_GDYNIA("Arka Gdynia"),
    WISLA_PLOCK("Wisła Płock"),
    ZAGLEBIE_SOSNOWIEC("Zagłębie Sosnowiec");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public static Team from(String name) {
        return Arrays.stream(values())
                .filter(it -> name.equals(it.getName()))
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }
}

//    Lechia Gdańsk
//        2.	Legia Warszawa	28	16	6	6	43:29	54
//
//        3.	Piast Gliwice	28	14	7	7	41:29	49
//
//        4.	Cracovia	29	13	6	10	35:32	45
//
//        5.	Zagłębie Lubin	29	13	5	11	45:37	44
//
//        6.	Lech Poznań	28	13	4	11	41:37	43
//
//        7.	Pogoń Szczecin	28	12	6	10	40:36	42
//
//        8.	Jagiellonia Białystok	28	11	8	9	41:40	41
//
//        9.	Wisła Kraków	28	12	5	11	52:43	41
//
//        10.	Korona Kielce	29	10	10	9	35:40	40
//
//        11.	Śląsk Wrocław	28	8	7	13	35:34	31
//
//        12.	Miedź Legnica	29	8	7	14	29:51	31
//
//        13.	Górnik Zabrze	28	6	10	12	34:47	28
//
//        14.	Arka Gdynia	28	6	9	13	35:40	27
//
//        15.	Wisła Płock	28	5	9	14	35:48	24
//
//        16.	Zagłębie Sosnowiec

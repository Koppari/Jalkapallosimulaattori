package logiikka;

import java.util.Random;

/**
 * Luo eventtejä matseille, esimerkiksi laukauksia ja maaleja.
 */
public class RandomEventGeneraattori {

    private static final Random R = new Random();

    public RandomEventGeneraattori() {
    }

    /**
     * Antaa isomman mahdollisuuden siihen että maaliMahdollisuus kuuluu
     * joukkueelle jolla on parempi voima prosentillisesti.
     *
     * @param j Eka joukkue.
     * @param k Toka joukkue.
     *
     * @return Palauttaa true jos satunnainen mahdollisuusMaaliin on pienempi
     * kuin joukkueen j verrattu voima joukkueeseen k
     * @throws java.lang.Exception
     */
    public static boolean maaliMahdollisuus(Joukkue j, Joukkue k) throws Exception {
        if (j.joukkueenVoima <= 0 || k.joukkueenVoima <= 0) {
            throw new Exception("Yhden tai molempien joukkueiden voima on 0 tai alle!");
        }

        //jos joukkueet samantasoisia, lähtökoht. >0.5 = j maaliMahdollisuus ja <0.5 = k maaliMahdollisuus
        double mahdollisuusMaaliin = R.nextDouble();

        //antaa joukkueen j mahdollisuuden tehdä maalin (eli kuinka paljon parempi joukkue on prosentteina)
        double joukkueenParemmuus = joukkueenParemmuus(j, k);

        //palauttaa true jos maaliMahdollisuus on joukkueen j
        return mahdollisuusMaaliin < joukkueenParemmuus;
    }

    /**
     * Vertailee kahta joukkuetta.
     *
     * @param j Eka joukkue.
     * @param k Toka joukkue.
     *
     * @return Joukkueen paremmuus 0.0-1.0 doublena.
     */
    public static double joukkueenParemmuus(Joukkue j, Joukkue k) {
        double joukkueenParemmuus;

        if (k.getJoukkueenVoima() < j.getJoukkueenVoima() || j.getJoukkueenVoima() < k.getJoukkueenVoima()) {
            joukkueenParemmuus = 0.01 * (50 + (100 - (((double) k.joukkueenVoima / (double) j.joukkueenVoima) * 100)));
        } else {
            joukkueenParemmuus = 0.5;
        }

        return joukkueenParemmuus;
    }

    /**
     * Palauttaa laukojan perustuen maalinTekija()-metodin
     * hyökkääväisyys-vertailuun
     *
     * @param j Laukova joukkue
     * @return Laukauksen tiedot
     */
    public static String laukaus(Joukkue j) {
        //käyttää suoraan maalinTekija-metodia koska hyökkäävillä pelaajilla on isompi mahdollisuus laukoa tolppaan
        return maalinTekija(j) + ", " + "pelaaja joukkueelle " + j.getNimi() + ", " + "laukoi!";
    }

    /**
     * Valitsee maalille tekijän. Mitä hyökkäävämpi pelaaja, sitä isompi
     * mahdollisuus maaliin.
     *
     * @param j Joukkue, johon maalintekijä kuuluu
     * @return Maalin tekevä pelaaja
     */
    public static Pelaaja maalinTekija(Joukkue j) {
        if (j.pelaajat.isEmpty()) {
            return null;
        }

        //valitsee maalille tekijän, mitä hyökkäävämpi pelaaja sitä varmemmin maaliMahdollisuus häneltä
        double maaliMahdollisuus = R.nextDouble();

        Pelaaja maalintekija = j.pelaajat.get(R.nextInt(j.pelaajat.size()));

        if (maaliMahdollisuus >= 0.995) {
            maalintekija = j.pelaajat.get(0);
        }
        if (maaliMahdollisuus < 0.995 && maaliMahdollisuus >= 0.84) {
            maalintekija = j.pelaajat.get(R.ints(1, 1, 4).findFirst().getAsInt());
        }
        if (maaliMahdollisuus < 0.84 && maaliMahdollisuus >= 0.50) {
            maalintekija = j.pelaajat.get(R.ints(1, 5, 8).findFirst().getAsInt());
        }
        if (maaliMahdollisuus < 0.50) {
            maalintekija = j.pelaajat.get(R.ints(1, 9, 10).findFirst().getAsInt());
        }

        //jos käy niin että maalintekijä kentalla = false
        if (!maalintekija.kentalla) {
            //..haetaan joku muu listasta
            maalintekija = j.pelaajat.stream()
                    .filter(p -> p.kentalla != false)
                    .findAny().get();
        }

        return maalintekija;

    }

    /**
     * Valitsee taklaajan. Mitä puolustavampi pelaaja, sitä isompi
     * mahdollisuus taklaukseen.
     *
     * @param j Joukkue, johon taklaaja kuuluu.
     * @return Taklaava pelaaja
     */
    public static Pelaaja taklaus(Joukkue j) {
        if (j.pelaajat.isEmpty()) {
            return null;
        }

        //valitsee taklaajan, mitä puolustavampi pelaaja sitä varmemmin taklaus häneltä
        double taklausMahdollisuus = R.nextDouble();

        Pelaaja taklaaja = j.pelaajat.get(R.nextInt(j.pelaajat.size()));

        if (taklausMahdollisuus >= 0.90) {
            taklaaja = j.pelaajat.get(0);
        }
        if (taklausMahdollisuus < 0.90 && taklausMahdollisuus >= 0.50) {
            taklaaja = j.pelaajat.get(R.ints(1, 1, 4).findFirst().getAsInt());
        }
        if (taklausMahdollisuus < 0.50 && taklausMahdollisuus >= 0.10) {
            taklaaja = j.pelaajat.get(R.ints(1, 5, 8).findFirst().getAsInt());
        }
        if (taklausMahdollisuus < 0.10) {
            taklaaja = j.pelaajat.get(R.ints(1, 9, 10).findFirst().getAsInt());
        }

        if (!taklaaja.kentalla) {
            //..haetaan joku muu listasta
            taklaaja = j.pelaajat.stream()
                    .filter(p -> p.kentalla != false)
                    .findAny().get();
        }

        return taklaaja;
    }

}

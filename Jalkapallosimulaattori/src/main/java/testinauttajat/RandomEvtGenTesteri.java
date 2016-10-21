package testinauttajat;

import java.util.Random;

/**
 * Luokka auttaa randomeiden metodien testaamisessa luokassa
 * RandomEventGeneraattori. Luokka on kopio alkuperäisestä luokasta mutta
 * randomit metodit on korvattu itse määritellyillä.
 *
 */
public class RandomEvtGenTesteri {

    public RandomEvtGenTesteri() {
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
    public static boolean maaliMahdollisuus(JoukkueTesteri j, JoukkueTesteri k) throws Exception {
        if (j.joukkueenVoima <= 0 || k.joukkueenVoima <= 0) {
            throw new Exception("Yhden tai molempien joukkueiden voima on 0 tai alle!");
        }

        //jos joukkueet samantasoisia, lähtökoht. >0.5 = j maaliMahdollisuus ja <0.5 = k maaliMahdollisuus
        double mahdollisuusMaaliin = 0.5;

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
    public static double joukkueenParemmuus(JoukkueTesteri j, JoukkueTesteri k) {
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
    public static String laukaus(JoukkueTesteri j) {
        //käyttää suoraan maalinTekija-metodia koska hyökkäävillä pelaajilla on isompi mahdollisuus laukoa tolppaan
        return maalinTekija(j, 0.5) + ", " + "pelaaja joukkueelle " + j.getNimi() + ", " + "laukoi!";
    }

    /**
     * Valitsee maalille tekijän. Mitä hyökkäävämpi pelaaja, sitä isompi
     * mahdollisuus maaliin.
     *
     * @param j Joukkue, johon maalintekijä kuuluu
     * @return Maalin tekevä pelaaja
     */
    public static PelaajaTesteri maalinTekija(JoukkueTesteri j, double d) {
        if (j.pelaajat.isEmpty()) {
            return null;
        }

        //valitsee maalille tekijän, mitä hyökkäävämpi pelaaja sitä varmemmin maaliMahdollisuus häneltä
        double maaliMahdollisuus = 0.5;

        PelaajaTesteri maalintekija = j.pelaajat.get(9);

        if (d >= 0.995) {
            maalintekija = j.pelaajat.get(0);
        }
        if (d < 0.995 && d >= 0.84) {
            maalintekija = j.pelaajat.get(1);
        }
        if (d < 0.84 && d >= 0.50) {
            maalintekija = j.pelaajat.get(5);
        }
        if (d < 0.50) {
            maalintekija = j.pelaajat.get(9);
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

    public static PelaajaTesteri taklaus(JoukkueTesteri j, double d) {
        if (j.pelaajat.isEmpty()) {
            return null;
        }

        //valitsee taklaajan, mitä puolustavampi pelaaja sitä varmemmin taklaus häneltä
        double taklausMahdollisuus = 0.5;

        PelaajaTesteri taklaaja = j.pelaajat.get(9);

        if (d >= 0.90) {
            taklaaja = j.pelaajat.get(0);
        }
        if (d < 0.90 && taklausMahdollisuus >= 0.50) {
            taklaaja = j.pelaajat.get(1);
        }
        if (d < 0.50 && taklausMahdollisuus >= 0.10) {
            taklaaja = j.pelaajat.get(5);
        }
        if (d < 0.10) {
            taklaaja = j.pelaajat.get(9);
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

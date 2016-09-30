package logiikka;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

/**
 * Luo eventtejä matseille, esimerkiksi laukauksia ja maaleja.
 */
public class RandomEventGeneraattori {

    private static Random r = new Random();

    public RandomEventGeneraattori() {
    }

    /**
     * Antaa isomman mahdollisuuden siihen että maali kuuluu joukkueelle jolla 
     * on parempi voima prosentillisesti.
     * 
     * @param j Eka joukkue.
     * @param k Toka joukkue.
     *
     * @return Palauttaa true jos satunnainen mahdollisuusMaaliin on pienempi 
     * kuin joukkueen j verrattu voima joukkueeseen k
     */
    public static boolean maali(Joukkue j, Joukkue k) throws Exception {
        if (j.joukkueenVoima == 0 || k.joukkueenVoima == 0) {
            throw new Exception("Yhden tai molempien joukkueiden voima on 0!");
        }

        //jos joukkueet samantasoisia, lähtökoht. >0.5 = j maali ja <0.5 = k maali
        double mahdollisuusMaaliin = r.nextDouble();
        
        //antaa joukkueen j mahdollisuuden tehdä maalin (eli kuinka paljon parempi joukkue on prosentteina)
        double joukkueenParemmuus = joukkueenParemmuus(j, k);

        //palauttaa true jos maali on joukkueen j
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
     * Palauttaa laukojan perustuen maalinTekija()-metodin hyökkääväisyys-vertailuun
     * 
     * @param j Laukova joukkue
     * @return Laukauksen tiedot
     */
    public static String laukaus(Joukkue j) {
        //käyttää suoraan maalinTekija-metodia koska hyökkäävillä pelaajilla on isompi mahdollisuus laukoa tolppaan
        return maalinTekija(j) + ", " + "pelaaja joukkueelle " + j.getNimi() + ", " + "laukoi!";
    }

    /**
     * Valitsee maalille tekijän. Mitä hyökkäävämpi pelaaja, sitä isompi mahdollisuus maaliin.
     * 
     * @param j Joukkue, johon maalintekijä kuuluu
     * @return Maalin tekevä pelaaja
     */
    public static Pelaaja maalinTekija(Joukkue j) {
        if (j.pelaajat.isEmpty()) {
            return null;
        }

        //valitsee maalille tekijän, mitä hyökkäävämpi pelaaja sitä varmemmin maali häneltä
        double maaliMahdollisuus = r.nextDouble();

        if (maaliMahdollisuus >= 0.995) {
            return j.pelaajat.get(0);
        }
        if (maaliMahdollisuus < 0.995 && maaliMahdollisuus >= 0.84) {
            return j.pelaajat.get(r.ints(1, 1, 4).findFirst().getAsInt());
        }
        if (maaliMahdollisuus < 0.84 && maaliMahdollisuus >= 0.50) {
            return j.pelaajat.get(r.ints(1, 5, 8).findFirst().getAsInt());
        }
        if (maaliMahdollisuus < 0.50) {
            return j.pelaajat.get(r.ints(1, 9, 10).findFirst().getAsInt());
        }

        //palauttaa satunnaisen varalta pelaajan jos ei löydetä muutoin 
        return j.pelaajat.get(r.nextInt(j.pelaajat.size()));
    }

}

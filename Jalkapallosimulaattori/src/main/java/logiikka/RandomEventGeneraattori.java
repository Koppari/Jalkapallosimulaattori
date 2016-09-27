package logiikka;

import java.util.Random;

public class RandomEventGeneraattori {

    private static Random r = new Random();

    public RandomEventGeneraattori() {
    }

    public static boolean maali(Joukkue j, Joukkue k) throws Exception {
        if (j.joukkueenVoima == 0 || k.joukkueenVoima == 0) {
            throw new Exception("Yhden tai molempien joukkueiden voima on 0!");
        }

        //jos joukkueet samantasoisia, lähtökoht. >0.5 = j maali ja <0.5 = k maali
        double mahdollisuusMaaliin = r.nextDouble();
        double joukkueenParemmuus = 0;

        //antaa joukkueen j mahdollisuuden tehdä maalin
        if (k.kokonaisAttribuutit() < j.kokonaisAttribuutit() || j.kokonaisAttribuutit() < k.kokonaisAttribuutit()) {
            joukkueenParemmuus = 0.01 * (50 + (100 - (((double) k.joukkueenVoima / (double) j.joukkueenVoima) * 100)));
        } else {
            joukkueenParemmuus = 0.5;
        }

        //palauttaa true jos maali on joukkueen j
        if (mahdollisuusMaaliin < joukkueenParemmuus) {
            return true;
        } else {
            return false;
        }

    }

    public static Pelaaja maalinTekija(Joukkue j) {
        //valitsee maalille tekijän, mitä hyökkäävämpi pelaaja sitä varmemmin maali häneltä
        if (j.pelaajat.isEmpty()) {
            return null;
        }
        
        
        return j.pelaajat.get(r.nextInt(j.pelaajat.size()));
    }
    
    
}

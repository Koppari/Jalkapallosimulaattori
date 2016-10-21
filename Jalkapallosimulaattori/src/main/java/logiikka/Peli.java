package logiikka;

import java.io.*;
import java.util.Random;

/**
 * Huolehtii ottelun pyörittämisestä ja sen kirjoittamisesta tiedostoon.
 */
public class Peli {

    private static final Random RANDOM = new Random();
    private static final RandomEventGeneraattori RANDOMEVENTGEN = new RandomEventGeneraattori();
    private TiedostonHoitaja tiedostonHoitaja = new TiedostonHoitaja(this);
    public Joukkue omaJoukkue, vihollisJoukkue;
    protected String s = "";

    //tilastoja
    int omatMaalit = 0, vihollisenMaalit = 0;
    int omatLaukaukset = 0, vihollisenLaukaukset = 0;
    int omatSyotot = 0, vihollisenSyotot = 0;
    int omatTaklaukset = 0, vihollisenTaklaukset = 0;
    int omatKeltaiset = 0, vihollisenKeltaiset = 0;
    int omatPunaiset = 0, vihollisenPunaiset = 0;

    public Peli() {
    }

    public Peli(Joukkue x, Joukkue y) {
        this.omaJoukkue = x;
        this.vihollisJoukkue = y;
    }

    /**
     * Generoi matsin ja palauttaa ulosajetut pelaajat kentälle.
     */
    public void matsinGenerointi() {
        tiedostonHoitaja.tiedostonGenerointi();
        
        if (omatMaalit > vihollisenMaalit) {
            omaJoukkue.haviot++;
        } else if (omatMaalit < vihollisenMaalit) {
            omaJoukkue.voitot++;
        } else {
            omaJoukkue.tasapelit++;
        }
        
        this.omaJoukkue.pelaajatKentalle();
        this.vihollisJoukkue.pelaajatKentalle();
        this.omaJoukkue.joukkueenVoimaLasku();
        this.vihollisJoukkue.joukkueenVoimaLasku();
    }

    /**
     * Lukee matsin tapahtumat.
     *
     * @return Matsin tapahtumat.
     * @throws java.io.FileNotFoundException
     */
    public String matsinLuku() throws FileNotFoundException {
        return tiedostonHoitaja.matsinLuku();
    }

    /**
     * Lisää joukkueen matsin tilastot joukkueen kokonaistilastoihin.
     * @param j Joukkue jonka tilastot halutaan selville.
     */
    public void tilastojenTallennus(Joukkue j) {
        j.tilastojenLisays(omatMaalit, omatLaukaukset, omatSyotot,
                omatTaklaukset, omatKeltaiset, omatPunaiset);
    }

    /**
     * Nollaa tilastot seuraavan matsin generoimista varten.
     */
    public void tilastojenNollaus() {
        omatMaalit = 0;
        vihollisenMaalit = 0;
        omatLaukaukset = 0;
        vihollisenLaukaukset = 0;
        omatTaklaukset = 0;
        vihollisenTaklaukset = 0;
        omatKeltaiset = 0;
        vihollisenKeltaiset = 0;
        omatPunaiset = 0;
        vihollisenPunaiset = 0;
        omatSyotot = 0;
        vihollisenSyotot = 0;
    }

    /**
     * Palauttaa matsin tilastot.
     *
     * @return Tilastot.
     */
    public String tilastot() {
        String s = "";
        s = "Joukkueesi maalit: " + omatMaalit + "\n"
                + "Vastustajan maalit: " + vihollisenMaalit + "\n\n"
                + "Joukkueesi laukaukset: " + omatLaukaukset + "\n"
                + "Vastustajan laukaukset: " + vihollisenLaukaukset + "\n\n"
                + "Joukkueesi taklaukset: " + omatTaklaukset + "\n"
                + "Vastustajan taklaukset: " + vihollisenTaklaukset + "\n\n"
                + "Joukkueesi keltaiset: " + omatKeltaiset + "\n"
                + "Vastustajan keltaiset: " + vihollisenKeltaiset + "\n\n"
                + "Joukkueesi punaiset: " + omatPunaiset + "\n"
                + "Vastustajan punaiset: " + vihollisenPunaiset + "\n\n"
                + "Joukkueesi syötöt: " + omatSyotot + "\n"
                + "Vastustajan syötöt: " + vihollisenSyotot + "\n\n";

        return s;
    }

    /**
     * Luo satunnaiset joukkueet ja tallentaa ne erilliseen ArrayListiin.
     *
     *
     */
    public void tiimienLuonti() {
        Joukkue x = new Joukkue();
        Joukkue y = new Joukkue();

        x.luoJoukkue();
        y.luoJoukkue();
        
        this.omaJoukkue = x;
        this.vihollisJoukkue = y;
    }

    /**
     * Luo eventtejä matsigeneraattorille.
     *
     * @param x Oma joukkue
     * @param y Vihollisjoukkue
     * @throws Exception
     */
    public void eventGenerointi(Joukkue x, Joukkue y) throws Exception {
        double event = 0;
        event = RANDOM.nextDouble();

        syotto();

        if (event > 0.95) { //maali
            s = s + maali();
        }

        if (event < 0.95 && event > 0.93) { //laukaus tolppaan
            s = s + tolppa();
        }

        if (event < 0.15) { //laukaus
            s = s + laukaus();
        }

        if (event < 0.9 && event > 0.1) { //taklaus
            s = s + taklaus();
        }

    }

    /**
     * Katsoo onko maali oma vai vastustajan.
     *
     * @return Maalintekijä
     * @throws Exception
     */
    public String maali() throws Exception {
        boolean maali = RANDOMEVENTGEN.maaliMahdollisuus(omaJoukkue, vihollisJoukkue);
        if (maali) {
            omatLaukaukset++;
            omatMaalit++;
            return "\n" + RANDOMEVENTGEN.maalinTekija(omaJoukkue) + ", teki maalin joukkueelle " + omaJoukkue.getNimi() + "!";
        } else {
            vihollisenLaukaukset++;
            vihollisenMaalit++;
            return "\n" + RANDOMEVENTGEN.maalinTekija(omaJoukkue) + ", teki maalin joukkueelle " + vihollisJoukkue.getNimi() + "!";
        }
    }

    /**
     * Katsoo oliko tolppaan laukaus oma vai vastustajan.
     * @return Tolppalaukaus
     */
    public String tolppa() {
        double tolppaMahdollisuus = RANDOM.nextDouble();
        if (tolppaMahdollisuus < RANDOMEVENTGEN.joukkueenParemmuus(omaJoukkue, vihollisJoukkue)) {
            omatLaukaukset++;
            return "\n" + RANDOMEVENTGEN.laukaus(omaJoukkue) + " Se osui tolppaan!";
        } else { //y laukoo tolppaan
            vihollisenLaukaukset++;
            return "\n" + RANDOMEVENTGEN.laukaus(vihollisJoukkue) + " Se osui tolppaan!";
        }
    }

    /**
     * Antaa joukkueelle syöttöjä per peliminuutti.
     */
    public void syotto() {
        omatSyotot = (int) (omatSyotot + (RANDOM.nextInt(3) + (omaJoukkue.joukkueenVoima * 0.0035)));
        vihollisenSyotot = (int) (vihollisenSyotot + (RANDOM.nextInt(3) + (vihollisJoukkue.joukkueenVoima * 0.0035)));
    }

    /**
     * Katsoo oliko laukaus oma vai vastustajan.
     * @return Laukaus
     */
    public String laukaus() {
        double laukausMahdollisuus = RANDOM.nextDouble();
        if (laukausMahdollisuus < RANDOMEVENTGEN.joukkueenParemmuus(omaJoukkue, vihollisJoukkue)) {
            omatLaukaukset++;
            return "\n" + RANDOMEVENTGEN.laukaus(omaJoukkue) + " Ohi meni!";
        } else {
            vihollisenLaukaukset++;
            return "\n" + RANDOMEVENTGEN.laukaus(vihollisJoukkue) + " Ohi meni!";
        }
    }

    /**
     * Katsoo oliko taklaus oma vai vastustajan.
     * @return Taklaus
     */
    public String taklaus() {
        String s = "";
        //lisärandomiuden vuoksi vielä toinen random-rolli:
        double r = RANDOM.nextDouble();
        Pelaaja taklaaja = null;

        if (r < 0.04) {
            double taklausMahdollisuus = RANDOM.nextDouble();
            if (taklausMahdollisuus < RANDOMEVENTGEN.joukkueenParemmuus(omaJoukkue, vihollisJoukkue)) {
                omatTaklaukset++;
                omatKeltaiset++;
                taklaaja = RANDOMEVENTGEN.taklaus(omaJoukkue);
                s = s + "\n" + taklaaja + ", taklasi pahasti ja sai" + keltainenVaiPunainen(taklaaja) + " kortin!";
                if (taklaaja.kortit >= 2) {
                    omatPunaiset++;
                    s = s + " Hän on ulkona pelistä!";
                    taklaaja.pelaajaUlosKentalta();
                    omaJoukkue.joukkueenVoimaLasku();
                }
            }
            if (taklausMahdollisuus > RANDOMEVENTGEN.joukkueenParemmuus(omaJoukkue, vihollisJoukkue)) {
                vihollisenTaklaukset++;
                vihollisenKeltaiset++;
                taklaaja = RANDOMEVENTGEN.taklaus(vihollisJoukkue);
                s = s + "\n" + taklaaja + ", taklasi pahasti ja sai" + keltainenVaiPunainen(taklaaja) + " kortin!";
                if (taklaaja.kortit >= 2) {
                    vihollisenPunaiset++;
                    s = s + " Hän on ulkona pelistä!";
                    taklaaja.pelaajaUlosKentalta();
                    vihollisJoukkue.joukkueenVoimaLasku();
                }
            }
        }

        return s;
    }

    /**
     * Katsoo oliko taklaus keltainen vai punainen.
     *
     * @param p Taklaava pelaaja
     * @return Joko keltainen tai punainen
     */
    public String keltainenVaiPunainen(Pelaaja p) {
        String s = "";
        double r = RANDOM.nextDouble();

        if (r < 0.92) {
            s = " keltaisen";
            p.kortit++;
        } else {
            s = " punaisen";
            p.setKortit(2);
        }

        return s;
    }

}

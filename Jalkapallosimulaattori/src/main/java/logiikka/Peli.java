package logiikka;

import java.io.*;
import java.util.Random;

/**
 * Huolehtii ottelun pyörittämisestä ja sen kirjoittamisesta tiedostoon.
 */
public class Peli {

    private static final Random R = new Random();
    private static final RandomEventGeneraattori REG = new RandomEventGeneraattori();
    private TiedostonHoitaja tiedostonHoitaja = new TiedostonHoitaja(this);
    public Joukkue x, y;
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
        this.x = x;
        this.y = y;
    }

    /**
     * Generoi matsin ja palauttaa ulosajetut pelaajat kentälle.
     */
    public void matsinGenerointi() {
        tiedostonHoitaja.tiedostonGenerointi();
        this.x.pelaajatKentalle();
        this.y.pelaajatKentalle();
        this.x.joukkueenVoimaLasku();
        this.y.joukkueenVoimaLasku();
    }

    /**
     * Lukee matsin tapahtumat.
     *
     * @return Matsin tapahtumat.
     */
    public String matsinLuku() throws FileNotFoundException {
        return tiedostonHoitaja.matsinLuku();
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
     * @param omaNimi Oman joukkuuen nimi.
     * @param vihollisNimi Vastustajan joukkueen nimi.
     *
     */
    public void tiimienLuonti(String omaNimi, String vihollisNimi) {
        Joukkue x = new Joukkue(omaNimi);
        Joukkue y = new Joukkue(vihollisNimi);

        x.luoJoukkue();
        y.luoJoukkue();

        this.x = x;
        this.y = y;
    }

    /**
     * Luo eventtejä matsigeneraattorille.
     *
     * @param x Oma joukkue
     * @param y Vs. joukkue
     * @throws Exception
     */
    public void eventGenerointi(Joukkue x, Joukkue y) throws Exception {
        double event = 0;
        event = R.nextDouble();

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
     * @throws Exception
     */
    public String maali() throws Exception {
        boolean maali = REG.maaliMahdollisuus(x, y);
        if (maali) {
            omatLaukaukset++;
            omatMaalit++;
            return "\n" + REG.maalinTekija(x) + ", teki maalin joukkueelle " + x.getNimi() + "!";
        } else {
            vihollisenLaukaukset++;
            vihollisenMaalit++;
            return "\n" + REG.maalinTekija(x) + ", teki maalin joukkueelle " + y.getNimi() + "!";
        }
    }

    /**
     * Katsoo oliko tolppaan laukaus oma vai vastustajan.
     */
    public String tolppa() {
        double tolppaMahdollisuus = R.nextDouble();
        if (tolppaMahdollisuus < REG.joukkueenParemmuus(x, y)) {
            omatLaukaukset++;
            return "\n" + REG.laukaus(x) + " Se osui tolppaan!";
        } else { //y laukoo tolppaan
            vihollisenLaukaukset++;
            return "\n" + REG.laukaus(y) + " Se osui tolppaan!";
        }
    }

    /**
     * Antaa joukkueelle syöttöjä per peliminuutti.
     */
    public void syotto() {
        omatSyotot = omatSyotot + (R.nextInt(3) + 5);
        vihollisenSyotot = vihollisenSyotot + (R.nextInt(3) + 5);
    }

    /**
     * Katsoo oliko laukaus oma vai vastustajan.
     */
    public String laukaus() {
        double laukausMahdollisuus = R.nextDouble();
        if (laukausMahdollisuus < REG.joukkueenParemmuus(x, y)) {
            omatLaukaukset++;
            return "\n" + REG.laukaus(x) + " Ohi meni!";
        } else {
            vihollisenLaukaukset++;
            return "\n" + REG.laukaus(y) + " Ohi meni!";
        }
    }

    /**
     * Katsoo oliko taklaus oma vai vastustajan.
     */
    public String taklaus() {
        String s = "";
        //lisärandomiuden vuoksi vielä toinen random-rolli:
        double r = R.nextDouble();
        Pelaaja taklaaja = null;

        if (r < 0.04) {
            double taklausMahdollisuus = R.nextDouble();
            if (taklausMahdollisuus < REG.joukkueenParemmuus(x, y)) {
                omatTaklaukset++;
                omatKeltaiset++;
                taklaaja = REG.taklaus(x);
                s = s + "\n" + taklaaja + ", taklasi pahasti ja sai" + keltainenVaiPunainen(taklaaja) + " kortin!";
                if (taklaaja.kortit >= 2) {
                    omatPunaiset++;
                    s = s + " Hän on ulkona pelistä!";
                    taklaaja.pelaajaUlosKentalta();
                    x.joukkueenVoimaLasku();
                }
            }
            if (taklausMahdollisuus > REG.joukkueenParemmuus(x, y)) {
                vihollisenTaklaukset++;
                vihollisenKeltaiset++;
                taklaaja = REG.taklaus(y);
                s = s + "\n" + taklaaja + ", taklasi pahasti ja sai" + keltainenVaiPunainen(taklaaja) + " kortin!";
                if (taklaaja.kortit >= 2) {
                    vihollisenPunaiset++;
                    s = s + " Hän on ulkona pelistä!";
                    taklaaja.pelaajaUlosKentalta();
                    y.joukkueenVoimaLasku();
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
        double r = R.nextDouble();

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

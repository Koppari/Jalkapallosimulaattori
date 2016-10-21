package testinauttajat;

import java.util.ArrayList;

/**
 * Luokka auttaa randomeiden metodien testaamisessa luokassa Joukkue. Luokka on
 * kopio alkuperäisestä luokasta mutta randomit metodit on korvattu itse
 * määritellyillä.
 *
 */
public class JoukkueTesteri {

    protected ArrayList<PelaajaTesteri> pelaajat = new ArrayList();
    protected ArrayList<PelaajaTesteri> tallennetutPelaajat = new ArrayList();
    protected int joukkueenVoima = 0;
    protected String nimi;

    //joukkueen kokonaistilastoja
    int kokonaisMaalit = 0, kokonaisLaukaukset = 0, kokonaisSyotot = 0,
            kokonaisTaklaukset = 0, kokonaisKeltaiset = 0, kokonaisPunaiset = 0,
            voitot = 0, haviot = 0, tasapelit = 0;

    //satunnaisia nimiä joukkueelle
    private static String[] tiimiNimet = {"Nimble Dinosaurs", "Hard Falcons",
        "Giant Stallions", "Grim Ghosts", "Wise Wreckers", "Iron Sailors",
        "Regal Warthogs", "Enchanted Miracles", "Clever Barracudas", "Magic Birds",
        "Happy Wildlings", "Classic Dodgers", "Magic Striders", "Silver Cobras",
        "White Penguins", "Storm Wings", "Hard Anacondas", "Swift Ducks",
        "Gruesome Raiders", "Fabulous Boomers", "Hard Spikes", "Spirit Pumas"};

    public JoukkueTesteri() {
    }

    public JoukkueTesteri(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Lisää yhden matsin tilastot joukkueen kokonaistilastoihin.
     *
     * @param maalit Kokonaismaalit.
     * @param laukaukset Kokonaislaukaukset.
     * @param syotot Kokonaissyötöt.
     * @param taklaukset Kokonaistaklaukset.
     * @param keltaiset Kokonaiskeltaiset.
     * @param punaiset Kokonaispunaiset.
     */
    public void tilastojenLisays(int maalit, int laukaukset, int syotot,
            int taklaukset, int keltaiset, int punaiset) {
        kokonaisMaalit = kokonaisMaalit + maalit;
        kokonaisLaukaukset = kokonaisLaukaukset + laukaukset;
        kokonaisSyotot = kokonaisSyotot + syotot;
        kokonaisTaklaukset = kokonaisTaklaukset + taklaukset;
        kokonaisKeltaiset = kokonaisKeltaiset + keltaiset;
        kokonaisPunaiset = kokonaisPunaiset + punaiset;
    }

    /**
     * Palauttaa joukkueen matsien kokonaistilastot.
     *
     * @return Kokonaistilastot.
     */
    public String kokonaistilastot() {
        String s = "";
        s = "Joukkueesi nimi: " + this.getNimi() + "\n"
                + "Joukkueesi maalit: " + kokonaisMaalit + "\n"
                + "Joukkueesi laukaukset: " + kokonaisLaukaukset + "\n"
                + "Joukkueesi syötöt: " + kokonaisSyotot + "\n"
                + "Joukkueesi taklaukset: " + kokonaisTaklaukset + "\n"
                + "Joukkueesi keltaiset: " + kokonaisKeltaiset + "\n"
                + "Joukkueesi punaiset: " + kokonaisPunaiset + "\n"
                + "Joukkueesi voitot: " + voitot + "\n"
                + "Joukkueesi häviöt: " + haviot + "\n"
                + "Joukkueesi tasapelit: " + tasapelit + "\n";

        return s;
    }

    /**
     * Luo joukkueelle 11 pelaajaa satunnaisilla attribuuteilla.
     *
     */
    public void luoJoukkue() {
        this.setNimi(tiimiNimet[1]);

        pelaajat.add(new PelaajaTesteri(0));
        for (int i = 1; i <= 4; i++) {
            pelaajat.add(new PelaajaTesteri(1));
        }
        for (int i = 1; i <= 4; i++) {
            pelaajat.add(new PelaajaTesteri(2));
        }
        for (int i = 1; i <= 2; i++) {
            pelaajat.add(new PelaajaTesteri(3));
        }

        joukkueenVoimaLasku();

        if (joukkueenVoima < 0) {
            joukkueenVoima = 0;
        }

    }

    /**
     * Poistaa joukkueen pelaajat.
     */
    public void poistaPelaajat() {
        pelaajat.clear();
    }

    /**
     * Printtaa joukkueen pelaajien attribuutit "korttimaisesti."
     *
     * @return Pelaajien attribuutit.
     */
    public String printPelaajat() {
        String s = "";
        for (PelaajaTesteri p : this.pelaajat) {
            s = s + p.printAttribuutit() + "\n";
        }
        if (this.pelaajat == null) {
            return null;
        }
        return s;
    }

    /**
     * Laskee joukkueen voiman
     */
    public void joukkueenVoimaLasku() {
        joukkueenVoima = 0;
        for (PelaajaTesteri p : this.pelaajat) {
            if (p.getKentalla()) {
                joukkueenVoima = joukkueenVoima + p.getKokonaisAttribuutit();
            }
        }
    }

    /**
     * Tuo pelaajat takaisin kentälle.
     */
    public void pelaajatKentalle() {
        for (PelaajaTesteri p : this.pelaajat) {
            p.setKortit(0);
            if (!p.getKentalla()) {
                p.setKentalla(true);
            }
        }
    }

    public void setJoukkueenVoima(int joukkueenVoima) {
        this.joukkueenVoima = joukkueenVoima;
    }

    public ArrayList<PelaajaTesteri> getPelaajat() {
        return pelaajat;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }

    public int getJoukkueenVoima() {
        return joukkueenVoima;
    }

}

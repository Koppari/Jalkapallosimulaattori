package logiikka;

import java.util.ArrayList;

/**
 * Luokka peleissä pelaaville joukkueille.
 */
public class Joukkue {

    protected ArrayList<Pelaaja> pelaajat = new ArrayList();
    protected ArrayList<Pelaaja> tallennetutPelaajat = new ArrayList();
    protected int joukkueenVoima = 0;
    protected String nimi;

    public Joukkue() {
    }

    public Joukkue(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Luo joukkueelle 11 pelaajaa satunnaisilla attribuuteilla.
     *
     */
    public void luoJoukkue() {
        pelaajat.add(new Pelaaja(0));
        for (int i = 1; i <= 4; i++) {
            pelaajat.add(new Pelaaja(1));
        }
        for (int i = 1; i <= 4; i++) {
            pelaajat.add(new Pelaaja(2));
        }
        for (int i = 1; i <= 2; i++) {
            pelaajat.add(new Pelaaja(3));
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
        for (Pelaaja p : this.pelaajat) {
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
        for (Pelaaja p : this.pelaajat) {
            if (p.getKentalla()) {
                joukkueenVoima = joukkueenVoima + p.getKokonaisAttribuutit();
            }
        }
    }
    
    /**
     * Tuo pelaajat takaisin kentälle.
     */
    public void pelaajatKentalle() {
        for (Pelaaja p : this.pelaajat) {
            p.setKortit(0);
            if (!p.getKentalla()) {
                p.setKentalla(true);
            }
        }
    }

    public void setJoukkueenVoima(int joukkueenVoima) {
        this.joukkueenVoima = joukkueenVoima;
    }

    public ArrayList<Pelaaja> getPelaajat() {
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

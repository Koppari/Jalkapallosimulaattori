package logiikka;

import java.util.ArrayList;

/**
 * Luokka peleissä pelaaville joukkueille.
 */
public class Joukkue {

    // joukkue koostuu pelaajista
    protected ArrayList<Pelaaja> pelaajat = new ArrayList();
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

        for (Pelaaja p : this.pelaajat) {
            joukkueenVoima = joukkueenVoima + p.getKokonaisAttribuutit();
        }

        if (joukkueenVoima < 0) {
            joukkueenVoima = 0;
        }

    }

    public void poistaPelaajat() {
        pelaajat.clear();
    }

    public String printPelaajat() {
        String s = "";
        for (Pelaaja p : this.pelaajat) {
            s = s + p.toString() + "\n";
        }
        if (this.pelaajat == null) {
            return null;
        }
        return s;
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

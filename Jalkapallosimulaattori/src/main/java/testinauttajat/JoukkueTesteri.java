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

    public JoukkueTesteri() {
    }

    public JoukkueTesteri(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Luo joukkueelle 11 pelaajaa satunnaisilla attribuuteilla.
     *
     */
    public void luoJoukkue() {
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

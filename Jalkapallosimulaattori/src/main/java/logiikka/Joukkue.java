package logiikka;

import java.util.ArrayList;

public class Joukkue {

    // joukkue koostuu pelaajista
    protected ArrayList<Pelaaja> pelaajat = new ArrayList();
    protected int joukkueenVoima = 0;

    public Joukkue() {
    }

    //lisää maalivahdin, 4 puolustajaa, 4 keskikenttää ja 2 hyökkääjää
    public void lisaaPelaajat() {
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
    }

    public void poistaPelaajat() {
        pelaajat.clear();
    }

    public void printPelaajat() {
        for (Pelaaja p : this.pelaajat) {
            System.out.println(p.toString());
            System.out.println("");
        }
        if (this.pelaajat == null) {
            System.out.println("Joukkueessa ei ole pelaajia!");
        }
    }

    public int kokonaisAttribuutit() {
        int joukkueenVoima = 0;

        for (Pelaaja p : this.pelaajat) {
            joukkueenVoima = joukkueenVoima + p.getKokonaisAttribuutit();
        }

        if (joukkueenVoima < 0) {
            joukkueenVoima = 0;
        }

        this.joukkueenVoima = joukkueenVoima;
        return joukkueenVoima;
    }

    public void setJoukkueenVoima(int joukkueenVoima) {
        this.joukkueenVoima = joukkueenVoima;
    }

    public ArrayList<Pelaaja> getPelaajat() {
        return pelaajat;
    }

}

package logiikka;

import java.io.File;
import static logiikka.RandomEventGeneraattori.maalinTekija;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {

    static Peli p;
    static Joukkue x;
    static Joukkue y;

    @Before
    public void setUp() {
        p = new Peli();
        x = new Joukkue();
        y = new Joukkue();
        x.luoJoukkue();
        y.luoJoukkue();
    }

    @Test
    public void laukausTolppaanOikein() {
        Pelaaja p = new Pelaaja();
        p = maalinTekija(x);
        String s = p.getNimi() + ", " + "pelaaja joukkueelle " + x.getNimi() + ", " + "laukoi tolppaan!";
        assertEquals(s, p.getNimi() + ", " + "pelaaja joukkueelle " + x.getNimi() + ", " + "laukoi tolppaan!");
    }

    @Test
    public void tiimienLuontiToimii() {
        p.tiimienLuonti("Eka", "Toka");
        assertEquals(p.x.getNimi(), "Eka");
        assertEquals(p.y.getNimi(), "Toka");
    }

    @Test
    public void tiimienLuontiKonstruktorillaToimii() {
        Joukkue j = new Joukkue();
        Joukkue k = new Joukkue();
        j.setNimi("A");
        k.setNimi("B");
        Peli p = new Peli(j, k);
        assertEquals(p.x.getNimi(), "A");
        assertEquals(p.y.getNimi(), "B");
    }

}

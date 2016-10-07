package logiikka;

import java.io.File;
import java.io.FileNotFoundException;
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
    public void konstruktoriJoukkueillaToimii() {
        x.setNimi("A");
        y.setNimi("B");
        Peli p = new Peli(x, y);
        assertEquals(p.x.nimi, "A");
        assertEquals(p.y.nimi, "B");
    }

    @Test
    public void tilastojenNollausNollaa() {
        p.matsinGenerointi(x, y);
        p.tilastojenNollaus();
        assertEquals(p.omatLaukaukset, 0);
        assertEquals(p.vihollisenLaukaukset, 0);
    }

    @Test
    public void tilastotPalauttaaOikein() {
        p.matsinGenerointi(x, y);
        assertEquals(p.tilastot(), "Joukkueesi maalit: " + p.omatMaalit + "\n"
                + "Vastustajan maalit: " + p.vihollisenMaalit + "\n"
                + "Joukkueesi laukaukset: " + p.omatLaukaukset + "\n"
                + "Vastustajan laukaukset: " + p.vihollisenLaukaukset + "\n"
                + "Joukkueesi taklaukset: " + p.omatTaklaukset + "\n"
                + "Vastustajan taklaukset: " + p.vihollisenTaklaukset + "\n"
                + "Joukkueesi keltaiset: " + p.omatKeltaiset + "\n"
                + "Vastustajan keltaiset: " + p.vihollisenKeltaiset + "\n"
                + "Joukkueesi punaiset: " + p.omatPunaiset + "\n"
                + "Vastustajan punaiset: " + p.vihollisenPunaiset + "\n");
    }

    @Test
    public void tolppaToimii() {
        Pelaaja p = new Pelaaja();
        p = maalinTekija(x);
        String s = p.getNimi() + ", " + "pelaaja joukkueelle " + x.getNimi() + ", " + "laukoi!";
        assertEquals(s, p.getNimi() + ", " + "pelaaja joukkueelle " + x.getNimi() + ", " + "laukoi!");
    }

    @Test
    public void maaliToimii() {
        Pelaaja p = new Pelaaja();
        p = maalinTekija(x);
        String s = "\n" + p + ", teki maalin joukkueelle " + x.getNimi() + "!";
        assertEquals(s, "\n" + p + ", teki maalin joukkueelle " + x.getNimi() + "!");
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

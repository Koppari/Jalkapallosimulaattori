package testinauttajat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkueTest {

    static JoukkueTesteri j;

    @Before
    public void setUp() {
        j = new JoukkueTesteri();
    }

    @Test
    public void luoYksitoistaPelaajaa() {
        j.luoJoukkue();
        assertEquals(j.getPelaajat().size(), 11);
    }
    
    @Test
    public void tilastojaLisataan() {
        assertEquals(j.kokonaisMaalit, 0);
        assertEquals(j.kokonaisLaukaukset, 0);
        assertEquals(j.kokonaisSyotot, 0);
        assertEquals(j.kokonaisTaklaukset, 0);
        assertEquals(j.kokonaisKeltaiset, 0);
        assertEquals(j.kokonaisPunaiset, 0);
        j.tilastojenLisays(1, 1, 1, 1, 1, 1);
        assertEquals(j.kokonaisMaalit, 1);
        assertEquals(j.kokonaisLaukaukset, 1);
        assertEquals(j.kokonaisSyotot, 1);
        assertEquals(j.kokonaisTaklaukset, 1);
        assertEquals(j.kokonaisKeltaiset, 1);
        assertEquals(j.kokonaisPunaiset, 1);
    }

    @Test
    public void kokonaisTilastotToimii() {
        assertEquals(j.kokonaistilastot(), "Joukkueesi nimi: " + j.getNimi() + "\n"
                + "Joukkueesi maalit: " + j.kokonaisMaalit + "\n"
                + "Joukkueesi laukaukset: " + j.kokonaisLaukaukset + "\n"
                + "Joukkueesi syötöt: " + j.kokonaisSyotot + "\n"
                + "Joukkueesi taklaukset: " + j.kokonaisTaklaukset + "\n"
                + "Joukkueesi keltaiset: " + j.kokonaisKeltaiset + "\n"
                + "Joukkueesi punaiset: " + j.kokonaisPunaiset + "\n"
                + "Joukkueesi voitot: " + j.voitot + "\n"
                + "Joukkueesi häviöt: " + j.haviot + "\n"
                + "Joukkueesi tasapelit: " + j.tasapelit + "\n");
    }

    @Test
    public void joukkueenVoimaEiNegatiivinen() {
        assertTrue(0 <= j.getJoukkueenVoima());
    }

    @Test
    public void setVoimaToimii() {
        j.setJoukkueenVoima(1200);
        assertEquals(j.getJoukkueenVoima(), 1200);
    }
    
    @Test
    public void voimaLasketaan() {
        j.luoJoukkue();
        j.joukkueenVoimaLasku();
        assertEquals(j.getJoukkueenVoima(), j.joukkueenVoima);
    }

    @Test
    public void pelaajatKentalleToimii() {
        PelaajaTesteri p;
        j.luoJoukkue();
        p = j.pelaajat.get(1);
        p.setKentalla(false);
        p.setKortit(1);
        j.pelaajatKentalle();
        assertTrue(p.getKentalla());
        assertEquals(p.getKortit(), 0);
    }

    @Test
    public void printPelaajatPalauttaaOikeinJosNull() {
        j.poistaPelaajat();
        assertEquals(j.printPelaajat(), "");
    }

    @Test
    public void poistaPelaajatToimii() {
        j.poistaPelaajat();
        assertEquals(j.getJoukkueenVoima(), 0);
    }

}

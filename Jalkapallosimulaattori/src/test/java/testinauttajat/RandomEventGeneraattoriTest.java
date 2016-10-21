package testinauttajat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RandomEventGeneraattoriTest {

    static RandomEvtGenTesteri r;
    static JoukkueTesteri j;

    @Before
    public void setUp() {
        r = new RandomEvtGenTesteri();
        j = new JoukkueTesteri();
    }

    @Test(expected = Exception.class)
    public void virheJosJoukkueenVoimaNolla() throws Exception {
        JoukkueTesteri j = new JoukkueTesteri();
        JoukkueTesteri k = new JoukkueTesteri();
        j.setJoukkueenVoima(0);
        r.maaliMahdollisuus(j, k);
    }

    @Test
    public void joukkueetYhtaHyvia() {
        JoukkueTesteri j = new JoukkueTesteri();
        JoukkueTesteri k = new JoukkueTesteri();
        j.setJoukkueenVoima(1600);
        k.setJoukkueenVoima(1600);
        assertEquals(0.5, r.joukkueenParemmuus(j, k), 0.1);
    }

    @Test
    public void maaliMahdollisuusPalauttaaTrue() throws Exception {
        JoukkueTesteri j = new JoukkueTesteri();
        JoukkueTesteri k = new JoukkueTesteri();
        j.setJoukkueenVoima(1700);
        k.setJoukkueenVoima(1600);
        assertTrue(r.maaliMahdollisuus(j, k));
    }

    @Test
    public void maaliMahdollisuusPalauttaaFalse() throws Exception {
        JoukkueTesteri j = new JoukkueTesteri();
        JoukkueTesteri k = new JoukkueTesteri();
        j.setJoukkueenVoima(1600);
        k.setJoukkueenVoima(1700);
        assertTrue(!r.maaliMahdollisuus(j, k));
    }

    @Test
    public void laukausOikein() {
        PelaajaTesteri p;
        p = r.maalinTekija(j, 0.5);
        assertEquals(r.laukaus(j), p + ", " + "pelaaja joukkueelle " + j.getNimi() + ", " + "laukoi!");
    }

    @Test(expected = Exception.class)
    public void virheJosJoukkueidenVoimatNolla() throws Exception {
        JoukkueTesteri j = new JoukkueTesteri();
        JoukkueTesteri k = new JoukkueTesteri();
        j.setJoukkueenVoima(0);
        k.setJoukkueenVoima(0);
        r.maaliMahdollisuus(j, k);
    }

    @Test
    public void maalinTekijaToimii() {
        j.luoJoukkue();
        PelaajaTesteri p;
        p = j.pelaajat.get(0);
        assertEquals(r.maalinTekija(j, 0.995), p);
        p = j.pelaajat.get(1);
        assertEquals(r.maalinTekija(j, 0.84), p);
        p = j.pelaajat.get(5);
        assertEquals(r.maalinTekija(j, 0.50), p);
        p = j.pelaajat.get(9);
        assertEquals(r.maalinTekija(j, 0.49), p);
    }

    @Test
    public void maalinTekijaToimiiVaikkaEiKentalla() {
        j.luoJoukkue();
        PelaajaTesteri p;
        p = j.pelaajat.get(0);
        p.setKentalla(false);
        r.maalinTekija(j, 0.995);
    }

    @Test
    public void maalinTekijaPalauttaaNullJosEmpty() {
        JoukkueTesteri j = new JoukkueTesteri();
        assertEquals(r.maalinTekija(j, 0.1), null);
    }

    @Test(expected = Exception.class)
    public void maaliHeittaaVirheenJosEiPelaajia() throws Exception {
        JoukkueTesteri j = new JoukkueTesteri();
        JoukkueTesteri k = new JoukkueTesteri();
        r.maaliMahdollisuus(j, k);
    }

    @Test
    public void taklausTekijaToimii() {
        j.luoJoukkue();
        PelaajaTesteri p;
        p = j.pelaajat.get(0);
        assertEquals(r.taklaus(j, 0.90), p);
        p = j.pelaajat.get(1);
        assertEquals(r.taklaus(j, 0.50), p);
        p = j.pelaajat.get(5);
        assertEquals(r.taklaus(j, 0.10), p);
        p = j.pelaajat.get(9);
        assertEquals(r.taklaus(j, 0.09), p);
    }

    @Test
    public void taklausToimiiVaikkaEiKentalla() {
        j.luoJoukkue();
        PelaajaTesteri p;
        p = j.pelaajat.get(0);
        p.setKentalla(false);
        r.taklaus(j, 0.995);
    }

    @Test
    public void taklaajaPalauttaaNullJosEmpty() {
        JoukkueTesteri j = new JoukkueTesteri();
        assertEquals(r.taklaus(j, 0.5), null);
    }

}

package logiikka;

import logiikka.Joukkue;
import logiikka.RandomEventGeneraattori;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RandomEventGeneraattoriTest {

    static RandomEventGeneraattori r;

    @Before
    public void setUp() {
        r = new RandomEventGeneraattori();
        
        
    }

    @Test(expected = Exception.class)
    public void virheJosJoukkueenVoimaNolla() throws Exception {
        Joukkue j = new Joukkue();
        Joukkue k = new Joukkue();
        j.setJoukkueenVoima(0);
        r.maaliMahdollisuus(j, k);
    }

    @Test(expected = Exception.class)
    public void virheJosJoukkueidenVoimatNolla() throws Exception {
        Joukkue j = new Joukkue();
        Joukkue k = new Joukkue();
        j.setJoukkueenVoima(0);
        k.setJoukkueenVoima(0);
        r.maaliMahdollisuus(j, k);
    }

    @Test
    public void maalinTekijaPalauttaaNullJosEmpty() {
        Joukkue j = new Joukkue();
        assertEquals(r.maalinTekija(j), null);
    }
    

    @Test(expected = Exception.class)
    public void maaliHeittaaVirheenJosEiPelaajia() throws Exception {
        Joukkue j = new Joukkue();
        Joukkue k = new Joukkue();
        r.maaliMahdollisuus(j, k);
    }

    @Test
    public void taklaajaPalauttaaNullJosEmpty() {
        Joukkue j = new Joukkue();
        assertEquals(r.taklaus(j), null);
    }      

}

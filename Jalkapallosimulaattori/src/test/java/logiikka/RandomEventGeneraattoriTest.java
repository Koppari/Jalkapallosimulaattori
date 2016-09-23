package logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RandomEventGeneraattoriTest {

    static RandomEventGeneraattori r;

    @Before
    public void setUp() {
        r = new RandomEventGeneraattori();
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
        r.maali(j, k);
    }

}

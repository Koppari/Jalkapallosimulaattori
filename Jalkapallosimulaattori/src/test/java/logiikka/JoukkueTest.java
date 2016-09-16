
package logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class JoukkueTest {
    
    static Joukkue j;
    
    @Before
    public void setUp() {
        j = new Joukkue();
    }
    
    @Test
    public void luoYksitoistaPelaajaa() {
        j.lisaaPelaajat();
        assertEquals(j.getPelaajat().size(), 11);
    }
    
    @Test
    public void joukkueenVoimaEiNegatiivinen() {
        assertTrue(0 <= j.kokonaisAttribuutit());
    }
    
    @Test
    public void poistaPelaajatToimii() {
        j.poistaPelaajat();
        assertEquals(j.kokonaisAttribuutit(), 0);
    }

}

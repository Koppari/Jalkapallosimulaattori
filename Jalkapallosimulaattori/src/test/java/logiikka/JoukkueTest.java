
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
        j.luoJoukkue();
        assertEquals(j.getPelaajat().size(), 11);
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

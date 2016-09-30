package logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {

    static Pelaaja p;

    @Before
    public void setUp() {
        p = new Pelaaja();
    }

    @Test
    public void konstruktoriIlmanParametria() {
        int i = 0;
        int j = 3;
        assertTrue(i <= p.getTyyppi() && p.getTyyppi() <= j);
    }

    @Test
    public void konstruktoriTyyppiParametrilla() {
        p = new Pelaaja(1);
        assertEquals(p.getTyyppi(), 1);
    }

    @Test
    public void konstruktoriNimiJaTyyppiParametrilla() {
        Pelaaja p = new Pelaaja("Make", 1);
        assertEquals(p.getNimi(), "Make");
        assertEquals(p.getTyyppi(), 1);
    }

    @Test
    public void pelaajaSaaUniikinIdn() {
        Pelaaja yksi = new Pelaaja("Viljami", 0);
        Pelaaja kaksi = new Pelaaja("Viljami", 0);
        assertTrue(yksi.getId() != kaksi.getId());
    }

    @Test
    public void pelaajaSaaTyypinVaarallaSyotteella() {
        Pelaaja p = new Pelaaja(6);
        assertTrue(p.getTyyppi() != 6);
    }

    @Test
    public void toStringToimii() {
        assertEquals(p.toString(), p.getNimi() + ", " + p.getRooli());
    }

}

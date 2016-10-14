package testinauttajat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {

    static PelaajaTesteri p;

    @Before
    public void setUp() {
        p = new PelaajaTesteri();
    }

    @Test
    public void konstruktoriIlmanParametria() {
        int i = 0;
        int j = 3;
        assertTrue(i <= p.getTyyppi() && p.getTyyppi() <= j);
    }

    @Test
    public void konstruktoriTyyppiParametrilla() {
        p = new PelaajaTesteri(1);
        assertEquals(p.getTyyppi(), 1);
    }

    @Test
    public void konstruktoriNimiJaTyyppiParametrilla() {
        PelaajaTesteri p = new PelaajaTesteri("Make", 1);
        assertEquals(p.getNimi(), "Make");
        assertEquals(p.getTyyppi(), 1);
    }

    @Test
    public void pelaajaSaaUniikinIdn() {
        PelaajaTesteri yksi = new PelaajaTesteri("Viljami", 0);
        PelaajaTesteri kaksi = new PelaajaTesteri("Viljami", 0);
        assertTrue(yksi.getUid() != kaksi.getUid());
    }

    @Test
    public void pelaajaSaaTyypinVaarallaSyotteella() {
        PelaajaTesteri p = new PelaajaTesteri(6);
        assertEquals(p.getTyyppi(), 3);
    }
    
    @Test 
    public void attribuutitAntavatOikeat() {
        PelaajaTesteri p = new PelaajaTesteri(0);
        PelaajaTesteri p2 = new PelaajaTesteri(1);
        PelaajaTesteri p3 = new PelaajaTesteri(2);
        PelaajaTesteri p4 = new PelaajaTesteri(3);
        
        assertEquals(p.getNopeus(),5);
        assertEquals(p.getTekniikka(),5);
        assertEquals(p.getPuolustaminen(),90);
        assertEquals(p.getVoima(),15);
        assertEquals(p.getKokonaisAttribuutit(),5+5+90+15);
        assertEquals(p.getRooli(), "maalivahti");
        
        assertEquals(p2.getNopeus(),10);
        assertEquals(p2.getTekniikka(),10);
        assertEquals(p2.getPuolustaminen(),80);
        assertEquals(p2.getVoima(),75);
        assertEquals(p2.getKokonaisAttribuutit(),10+10+80+75);
        assertEquals(p2.getRooli(), "puolustaja");
        
        assertEquals(p3.getNopeus(),28);
        assertEquals(p3.getTekniikka(),28);
        assertEquals(p3.getPuolustaminen(),28);
        assertEquals(p3.getVoima(),28);
        assertEquals(p3.getKokonaisAttribuutit(),28+28+28+28);
        assertEquals(p3.getRooli(), "keskikenttä");
        
        assertEquals(p4.getNopeus(),90);
        assertEquals(p4.getTekniikka(),90);
        assertEquals(p4.getPuolustaminen(),3);
        assertEquals(p4.getVoima(),25);
        assertEquals(p4.getKokonaisAttribuutit(),90+90+3+25);
        assertEquals(p4.getRooli(), "hyökkääjä");
        
    }
    
    @Test 
    public void pelaajaLentaaUlos() {
        PelaajaTesteri p = new PelaajaTesteri(0);
        p.pelaajaUlosKentalta();
        assertEquals(p.getKentalla(), false);
    }
    
    @Test
    public void pelaajaSaaKortin() {
        p.kortti();
        assertEquals(p.getKortit(), 1);
    }

    @Test
    public void toStringToimii() {
        assertEquals(p.toString(), p.getNimi() + ", " + p.getRooli());
    }

}

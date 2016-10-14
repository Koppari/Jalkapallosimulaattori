package testinauttajat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {

    static PeliTesteri p;
    static JoukkueTesteri x, y;
    static PelaajaTesteri pt;

    @Before
    public void setUp() {
        p = new PeliTesteri();
        p.tiimienLuonti("A", "B");
    }

    @Test
    public void konstruktoriJoukkueillaToimii() {
        JoukkueTesteri j = new JoukkueTesteri("A");
        JoukkueTesteri k = new JoukkueTesteri("B");
        PeliTesteri p = new PeliTesteri(j, k);
        assertEquals(p.x.getNimi(), "A");
        assertEquals(p.y.getNimi(), "B");
    }

    @Test
    public void tilastojenNollausNollaa() {
        p.omatSyotot = 100;
        p.vihollisenSyotot = 100;
        p.omatMaalit = 1;
        p.vihollisenMaalit = 0;

        p.tilastojenNollaus();
        assertEquals(p.omatLaukaukset, 0);
        assertEquals(p.vihollisenLaukaukset, 0);
    }

    @Test
    public void tilastotPalauttaaOikein() {
        p.omatSyotot = 100;
        p.vihollisenSyotot = 100;
        p.omatMaalit = 1;
        p.vihollisenMaalit = 0;
        assertEquals(p.tilastot(), "Joukkueesi maalit: " + p.omatMaalit + "\n"
                + "Vastustajan maalit: " + p.vihollisenMaalit + "\n\n"
                + "Joukkueesi laukaukset: " + p.omatLaukaukset + "\n"
                + "Vastustajan laukaukset: " + p.vihollisenLaukaukset + "\n\n"
                + "Joukkueesi taklaukset: " + p.omatTaklaukset + "\n"
                + "Vastustajan taklaukset: " + p.vihollisenTaklaukset + "\n\n"
                + "Joukkueesi keltaiset: " + p.omatKeltaiset + "\n"
                + "Vastustajan keltaiset: " + p.vihollisenKeltaiset + "\n\n"
                + "Joukkueesi punaiset: " + p.omatPunaiset + "\n"
                + "Vastustajan punaiset: " + p.vihollisenPunaiset + "\n\n"
                + "Joukkueesi syötöt: " + p.omatSyotot + "\n"
                + "Vastustajan syötöt: " + p.vihollisenSyotot + "\n\n");
    }

    @Test
    public void tiimienLuontiToimii() {
        PeliTesteri p = new PeliTesteri();
        p.tiimienLuonti("Eka", "Toka");
        assertEquals(p.x.getNimi(), "Eka");
        assertEquals(p.y.getNimi(), "Toka");
    }

    @Test
    public void syottoToimii() {
        p.syotto();
        assertEquals(p.omatSyotot, 6);
        assertEquals(p.vihollisenSyotot, 6);
    }

    @Test
    public void eventMaaliToimii() throws Exception {
        p.eventGenerointi(p.x, p.y, 0.96);
        assertEquals(p.omatSyotot, 6);
        assertEquals(p.vihollisenSyotot, 6);
        assertEquals(p.s, p.maali(true));
    }

    @Test
    public void eventTolppaToimii() throws Exception {
        p.eventGenerointi(p.x, p.y, 0.94);
        assertEquals(p.omatSyotot, 6);
        assertEquals(p.vihollisenSyotot, 6);
        assertEquals(p.s, p.tolppa(0.49));
    }

    @Test
    public void eventLaukausToimii() throws Exception {
        p.eventGenerointi(p.x, p.y, 0.14);
        assertEquals(p.omatSyotot, 6);
        assertEquals(p.vihollisenSyotot, 6);
        assertEquals(p.s, p.laukaus(0.49));
    }

    @Test
    public void eventTaklausToimii() throws Exception {
        p.eventGenerointi(p.x, p.y, 0.20);
        assertEquals(p.omatSyotot, 6);
        assertEquals(p.vihollisenSyotot, 6);
        assertEquals(p.s, p.taklaus(0.03, 0.49, 0.91));
    }

    @Test
    public void maaliAntaaOikeanTruella() throws Exception {
        pt = new PelaajaTesteri("Esimerkkipelaaja", 3);
        String s = "\n" + pt + ", teki maalin joukkueelle " + p.x.getNimi() + "!";
        assertEquals(p.maali(true), s);
    }

    @Test
    public void maaliAntaaOikeanFalsella() throws Exception {
        pt = new PelaajaTesteri("Esimerkkipelaaja", 3);
        String s = "\n" + pt + ", teki maalin joukkueelle " + p.y.getNimi() + "!";
        assertEquals(p.maali(false), s);
    }

    @Test
    public void maaliLisaaLaukauksia() throws Exception {
        p.maali(true);
        assertEquals(p.omatLaukaukset, 1);
        p.maali(false);
        assertEquals(p.vihollisenLaukaukset, 1);
    }

    @Test
    public void maaliLisaaMaaleja() throws Exception {
        p.maali(true);
        assertEquals(p.omatMaalit, 1);
        p.maali(false);
        assertEquals(p.vihollisenMaalit, 1);
    }

    @Test
    public void tolppaAntaaOikean() throws Exception {
        pt = new PelaajaTesteri("Esimerkkipelaaja", 3);
        String s = "\n" + pt + ", " + "pelaaja joukkueelle " + p.x.getNimi() + ", " + "laukoi!" + " Se osui tolppaan!";
        String s2 = "\n" + pt + ", " + "pelaaja joukkueelle " + p.y.getNimi() + ", " + "laukoi!" + " Se osui tolppaan!";
        assertEquals(p.tolppa(0.49), s);
        assertEquals(p.tolppa(0.51), s2);
    }

    @Test
    public void tolppaLisaaLaukauksia() throws Exception {
        p.tolppa(0.49);
        assertEquals(p.omatLaukaukset, 1);
        p.tolppa(0.51);
        assertEquals(p.vihollisenLaukaukset, 1);
    }

    @Test
    public void laukausLisaaLaukauksia() throws Exception {
        p.laukaus(0.49);
        assertEquals(p.omatLaukaukset, 1);
        p.laukaus(0.51);
        assertEquals(p.vihollisenLaukaukset, 1);
    }
    
    @Test
    public void laukausAntaaOikean() {
        pt = new PelaajaTesteri("Esimerkkipelaaja", 3);
        String s = "\n" + pt + ", " + "pelaaja joukkueelle " + p.x.getNimi() + ", " + "laukoi!" + " Ohi meni!";
        String s2 = "\n" + pt + ", " + "pelaaja joukkueelle " + p.y.getNimi() + ", " + "laukoi!" + " Ohi meni!";
        assertEquals(p.laukaus(0.49), s);
        assertEquals(p.laukaus(0.51), s2);
    }

    @Test
    public void taklausAntaaOikeanKeltainen() {
        pt = new PelaajaTesteri("Esimerkkipelaaja", 3);
        String s = "\n" + pt + ", taklasi pahasti ja sai" + p.keltainenVaiPunainen(pt, 0.91) + " kortin!";
        assertEquals(p.taklaus(0.03, 0.49, 0.91), s);
        assertEquals(p.taklaus(0.03, 0.51, 0.91), s);
    }

    @Test
    public void taklausAntaaOikeanTokaKeltainen() {
        pt = new PelaajaTesteri("Esimerkkipelaaja", 3);
        pt.setKortit(1);
        p.taklaus(0.03, 0.49, 0.91);
        String s = "\n" + pt + ", taklasi pahasti ja sai" + p.keltainenVaiPunainen(pt, 0.91) + " kortin!" + " Hän on ulkona pelistä!";
        assertEquals(p.taklaus(0.03, 0.49, 0.91) + " Hän on ulkona pelistä!", s);
        assertEquals(p.taklaus(0.03, 0.51, 0.91) + " Hän on ulkona pelistä!", s);
    }

    @Test
    public void taklausAntaaOikeanPunainen() {
        pt = new PelaajaTesteri("Esimerkkipelaaja", 3);
        pt.setKortit(2);
        String s = "\n" + pt + ", taklasi pahasti ja sai" + p.keltainenVaiPunainen(pt, 0.93) + " kortin!" + " Hän on ulkona pelistä!";
        assertEquals(p.taklaus(0.03, 0.49, 0.93), s);
        assertEquals(p.taklaus(0.03, 0.51, 0.93), s);
    }

    @Test
    public void punainenHeittaaUlos() {
        p.taklaus(0.03, 0.49, 0.93);
        assertEquals(p.pt.getKentalla(), false);
        p.taklaus(0.03, 0.51, 0.93);
        assertEquals(p.pt.getKentalla(), false);
    }

    @Test
    public void korttienJakoToimiiKeltainen() {
        pt = new PelaajaTesteri("Esimerkkipelaaja", 3);
        String s = " keltaisen";
        assertEquals(s, p.keltainenVaiPunainen(pt, 0.91));
        assertEquals(pt.getKortit(), 1);
    }

    @Test
    public void korttienJakoToimiiPunainen() {
        pt = new PelaajaTesteri("Esimerkkipelaaja", 3);
        String s = " punaisen";
        assertEquals(s, p.keltainenVaiPunainen(pt, 0.93));
        assertEquals(pt.getKortit(), 2);
    }
      
}

package testinauttajat;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Luokka auttaa randomeiden metodien testaamisessa luokassa Pelaaja. Luokka on
 * kopio alkuperäisestä luokasta mutta randomit metodit on korvattu itse
 * määritellyillä.
 */
public class PelaajaTesteri {

    //pelaajan tiedot
    private static AtomicInteger uid = new AtomicInteger();
    private int atomicUid;

    //testauksen helpottamiseksi
    private int id;
    private int idlaskuri = 0;

    private String nimi;
    private int tyyppi; //maalivahti 0, puolustaja 1, keskikenttä 2, hyök. 3

    //pelaajien attribuutit
    private int nopeus, tekniikka, puolustaminen, voima;
    private int kokonaisAttribuutit;
    private String rooli;

    //tällä lasketaan pelaajan kortit, jos 2 = ulos pelistä
    protected int kortit = 0;
    protected boolean kentalla = true;

    //nimiä pelaajille, väliaikainen
    private static String[] names = {"Mikey Stenberg", "Alex Daniel", "Ambrozije Parodi",
        "Kliment Rhee", "Grozdan Megalos", "Symeonu Svendsen", "Enitan Tos",
        "Yaakov Van Amersvoort", "Tristan Derricks", "Terrell Salcedo",
        "Eadwulf Romero", "Vlassis Skala", "Giedrius Suero", "Lalit Hambledon",
        "Haldor Innocenti", "Qasim Lindsay", "Vasilios Horn", "Eirenaios Huang",
        "Adebayo De Vries", "Jaron Bajusz", "Christianus McWilliam",
        "Slava Einarsson", "Baldassare Marek", "Titus Rojo", "Timon Yukimura",
        "Alexander Finn"};

    public PelaajaTesteri() {
        this(3);
    }

    public PelaajaTesteri(int tyyppi) {
        this(names[1], tyyppi);
    }

    public PelaajaTesteri(String nimi, int tyyppi) {
        this.atomicUid = uid.getAndIncrement();
        this.id = 123;
        this.nimi = nimi;
        if (tyyppi <= 3 && tyyppi >= 0) {
            setTyyppi(tyyppi);
        } else {
            setTyyppi(3);
        }
        attribuutit();
    }

    /**
     * Metodi arpoo joukkueen pelaajille attribuutit.
     *
     *
     */
    public void attribuutit() {

        if (tyyppi == 0) {
            setNopeus(5);
            setTekniikka(5);
            setPuolustaminen(90);
            setVoima(15);
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            rooli = "maalivahti";
        }

        if (tyyppi == 1) {
            setNopeus(10);
            setTekniikka(10);
            setPuolustaminen(80);
            setVoima(75);
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            rooli = "puolustaja";
        }

        if (tyyppi == 2) {
            setNopeus(28);
            setTekniikka(28);
            setPuolustaminen(28);
            setVoima(28);
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            rooli = "keskikenttä";
        }

        if (tyyppi == 3) {
            setNopeus(90);
            setTekniikka(90);
            setPuolustaminen(3);
            setVoima(25);
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            rooli = "hyökkääjä";
        }
    }

    public void pelaajaUlosKentalta() {
        setKentalla(false);
    }

    public String toString() {
        return nimi + ", " + rooli;
    }

    /**
     * Listaa pelaajan attribuutit ja tiedot.
     *
     *
     * @return Pelaajan attribuutit listana.
     */
    public String printAttribuutit() {
        return nimi + "\nRooli: " + rooli + "\nNopeus: " + nopeus + "\nPuolustaminen: "
                + puolustaminen + "\nTekniikka: " + tekniikka + "\nVoima: " + voima
                + "\nKokonais: " + kokonaisAttribuutit + "\n\n";
    }

    public void kortti() {
        kortit++;
    }

    //getterit ja setterit
    public int getId() {
        return id;
    }

    public int getUid() {
        return atomicUid;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getNopeus() {
        return nopeus;
    }

    public void setNopeus(int nopeus) {
        this.nopeus = nopeus;
    }

    public int getTekniikka() {
        return tekniikka;
    }

    public void setTekniikka(int tekniikka) {
        this.tekniikka = tekniikka;
    }

    public int getPuolustaminen() {
        return puolustaminen;
    }

    public boolean getKentalla() {
        return kentalla;
    }

    public void setPuolustaminen(int puolustaminen) {
        this.puolustaminen = puolustaminen;
    }

    public int getVoima() {
        return voima;
    }

    public void setVoima(int voima) {
        this.voima = voima;
    }

    public int getTyyppi() {
        return tyyppi;
    }

    public int getKokonaisAttribuutit() {
        return kokonaisAttribuutit;
    }

    public String getRooli() {
        return rooli;
    }

    public int getKortit() {
        return kortit;
    }

    public void setKortit(int kortit) {
        this.kortit = kortit;
    }

    public void setRooli(String rooli) {
        this.rooli = rooli;
    }

    public void setTyyppi(int tyyppi) {
        this.tyyppi = tyyppi;
    }

    public void setKokonaisAttribuutit(int kokonaisAttribuutit) {
        this.kokonaisAttribuutit = kokonaisAttribuutit;
    }

    public void setKentalla(boolean kentalla) {
        this.kentalla = kentalla;
    }

}

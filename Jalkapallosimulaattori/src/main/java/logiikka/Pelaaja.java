package logiikka;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Luokka joukkueiden pelaajille.
 */
public class Pelaaja {

    //luodaan uniikki id- ja random-oliot
    private static AtomicInteger uid = new AtomicInteger();
    private static Random random = new Random();

    //pelaajan tiedot
    private int id;
    private String nimi;
    private int tyyppi; //maalivahti 0, puolustaja 1, keskikenttä 2, hyök. 3

    //pelaajien attribuutit
    private int nopeus, tekniikka, puolustaminen, voima;
    private int kokonaisAttribuutit;
    private String rooli;

    //tällä lasketaan pelaajan kortit, jos 2 = ulos pelistä
    protected int kortit = 0;
    protected boolean kentalla = true;

    //satunnaisia nimiä pelaajille
    private static String[] nimet = {"Mikey Stenberg", "Alex Daniel", "Ambrozije Parodi",
        "Kliment Rhee", "Grozdan Megalos", "Symeonu Svendsen", "Enitan Tos",
        "Yaakov Van Amersvoort", "Tristan Derricks", "Terrell Salcedo",
        "Eadwulf Romero", "Vlassis Skala", "Giedrius Suero", "Lalit Hambledon",
        "Haldor Innocenti", "Qasim Lindsay", "Vasilios Horn", "Eirenaios Huang",
        "Adebayo De Vries", "Jaron Bajusz", "Christianus McWilliam",
        "Slava Einarsson", "Baldassare Marek", "Titus Rojo", "Timon Yukimura",
        "Alexander Finn", "Jian Malone", "Calum Myles", "Plutarch Groves",
        "Alvar Leonardson", "Anupam Couture", "Khalid Yap", "Daniel Midgley",
        "Lars Nichols", "Babak Spini", "Tiriaq Beaufort", "Hartwig Mihov",
        "Efe Tifft", "Lochan Kelley", "Yosyp Sergeant", "Ricki Snijders",
        "Irakli Petit", "Uzziel Mendelsohn", "Seymour Ewart", "Stuart Rocha",
        "Athaulf Mikkelsen", "Alfarr Santillian", "Siddharta Schäfer",
        "Azad Daly", "Hugubert Victor", "Anwer Zhang", "Micheal Raske",
        "Morgan Mathiasen", "Amariah Kunkle", "Richard Darzi", "Briscoe Knef",
        "Kaipo Lawson", "Derek Derichs", "Jaydon Nagi", "Gulbahar Leccese",
        "Fedor Peláez", "Tyriq Battle", "Shafaqat Coenen", "Cadmus De Groot",
        "Zaki Markó", "Phanouel Zdunowski", "Soheil Corna", "Linus Meyer",
        "Ade Matějka", "Mamadou Sternberg", "Åsmund Hepburn", "Sweeney Klimy",
        "Mahomet Lombardi", "Jodoc Lazzari", "Guillaume Speight", "Parris Fattore",
        "Irvine Van Laar", "Branimir Kárpáti", "Yahui Forst", "Abd al-Rashid Illés",
        "Pyotr Van Veenen", "Burak Dempsey", "Pali King", "Mattin Jó", "Raimund Lengyel",
        "Norbert Stern", "Fergus Naldi", "Aleks Reynders", "Ariston Tarr",
        "Konrad Merlo", "Silvio Giffard", "Efemena Salvage", "Shakil Lang"
    };

    public Pelaaja() {
        this(random.nextInt(4));
    }

    public Pelaaja(int tyyppi) {
        this(nimet[random.nextInt(93)], tyyppi);
    }

    public Pelaaja(String nimi, int tyyppi) {
        this.id = uid.getAndIncrement();
        this.nimi = nimi;
        if (tyyppi <= 3 && tyyppi >= 0) {
            this.tyyppi = tyyppi;
        } else {
            this.tyyppi = random.nextInt(4);
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
            setNopeus(random.nextInt(10));
            setTekniikka(random.nextInt(10));
            setPuolustaminen(random.nextInt(20) + 80);
            setVoima(random.nextInt(30));
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            setRooli("maalivahti");
        }

        if (tyyppi == 1) {
            setNopeus(random.nextInt(20));
            setTekniikka(random.nextInt(20));
            setPuolustaminen(random.nextInt(40) + 60);
            setVoima(random.nextInt(50) + 50);
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            setRooli("puolustaja");
        }

        if (tyyppi == 2) {
            setNopeus(random.nextInt(35) + 10);
            setTekniikka(random.nextInt(35) + 10);
            setPuolustaminen(random.nextInt(35) + 10);
            setVoima(random.nextInt(35) + 10);
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            setRooli("keskikenttä");
        }

        if (tyyppi == 3) {
            setNopeus(random.nextInt(20) + 80);
            setTekniikka(random.nextInt(20) + 80);
            setPuolustaminen(random.nextInt(5));
            setVoima(random.nextInt(50));
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            setRooli("hyökkääjä");
        }
    }

    /**
     * Asettaa pelaajan kentällä oloksi falsen.
     */
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

    /**
     * Pelaaja saa keltaisen kortin.
     */
    public void kortti() {
        kortit++;
    }

    //getterit ja setterit
    public int getId() {
        return id;
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

package logiikka;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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

    //nimiä pelaajille, väliaikainen
    String[] names = {"Joni", "Petteri", "Jorma", "Mauri", "Jaakko", "Aatu", "Akseli", "Olavi", "Kalevi", "Paavo", "Roope"};

    public Pelaaja() {
        this.id = uid.getAndIncrement();
        this.nimi = names[random.nextInt(10)];
        this.tyyppi = random.nextInt(4);
        attribuutit();
    }

    public Pelaaja(int tyyppi) {
        this.id = uid.getAndIncrement();
        this.nimi = names[random.nextInt(10)];
        if (tyyppi < 4 && tyyppi >= 0) {
            this.tyyppi = tyyppi;
        } else {
            this.tyyppi = random.nextInt(4);
        }
        attribuutit();
    }

    public Pelaaja(String nimi, int tyyppi) {
        this.id = uid.getAndIncrement();
        this.nimi = nimi;
        if (tyyppi < 4 && tyyppi >= 0) {
            this.tyyppi = tyyppi;
        } else {
            this.tyyppi = random.nextInt(4);
        }
        attribuutit();
    }

    //metodi arpoo pelaajalle attribuutit riippuen pelaajan tyypistä
    public void attribuutit() {

        if (tyyppi == 0) {
            this.setNopeus(random.nextInt(10));
            this.setTekniikka(random.nextInt(10));
            this.setPuolustaminen(random.nextInt(20) + 80);
            this.setVoima(random.nextInt(30));
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            rooli = "maalivahti";
        }

        if (tyyppi == 1) {
            this.setNopeus(random.nextInt(20));
            this.setTekniikka(random.nextInt(20));
            this.setPuolustaminen(random.nextInt(40) + 60);
            this.setVoima(random.nextInt(50) + 50);
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            rooli = "puolustaja";
        }

        if (tyyppi == 2) {
            this.setNopeus(random.nextInt(35) + 10);
            this.setTekniikka(random.nextInt(35) + 10);
            this.setPuolustaminen(random.nextInt(35) + 10);
            this.setVoima(random.nextInt(35) + 10);
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            rooli = "keskikenttä";
        }

        if (tyyppi == 3) {
            this.setNopeus(random.nextInt(20) + 80);
            this.setTekniikka(random.nextInt(20) + 80);
            this.setPuolustaminen(random.nextInt(5));
            this.setVoima(random.nextInt(50));
            kokonaisAttribuutit = nopeus + puolustaminen + tekniikka + voima;
            rooli = "hyökkääjä";
        }
    }

    public String toString() {
        return nimi + ", " + rooli;
    }

    public String printAttribuutit() {
        return nimi + ", tyyppi: " + tyyppi + "\nNopeus: " + nopeus + "\nPuolustaminen: " + puolustaminen + "\nTekniikka: " + tekniikka + "\nVoima: " + voima + "\nKokonais: " + kokonaisAttribuutit;
    }

    //getterit ja setterit
    //pitää laittaa settereille 0 tai 100 välille check?
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

    public void setRooli(String rooli) {
        this.rooli = rooli;
    }

    //lisäys: muuttaa myös attribuutteja
    public void setTyyppi(int tyyppi) {
        this.tyyppi = tyyppi;
    }

}

package logiikka;

import java.util.Random;

public class Peli {

    private static final Random R  = new Random();
    private static final RandomEventGeneraattori REG = new RandomEventGeneraattori();

    public static void main(String[] args) {
        Joukkue x = new Joukkue();
        Joukkue y = new Joukkue();

        x.lisaaPelaajat();
        y.lisaaPelaajat();

        //peli kestää 90 looppausta ja jokaisella loop-kerralla on mahdollisuus tapahtua jokin sattumanvarainen event
        double event = 0;
        int omatMaalit = 0, vihollisenMaalit = 0;

        try {
            System.out.println("Peli on alkamassa! Joukkueesi voima: " + x.kokonaisAttribuutit() + "\nVihollisjoukkueen voima: " + y.kokonaisAttribuutit() + "\n");
            Thread.sleep(3000);
            for (int i = 0; i <= 90; i++) {
                System.out.print(omatMaalit + "-" + vihollisenMaalit + ", " + i + " min: ");
                event = R.nextDouble();

                if (event > 0.95) {
                    boolean maali = REG.maali(x, y);
                    if (maali) {
                        omatMaalit++;
                        System.out.print("Joukkueesi teki maalin! Maalin tekijä: " + REG.maalinTekija(x));
                    } else {
                        vihollisenMaalit++;
                        System.out.print("Vihollisjoukkue teki maalin! Maalin tekijä: " + REG.maalinTekija(y));
                    }
                }

                System.out.println("");
                Thread.sleep(350);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Peli päättyi " + omatMaalit + "-" + vihollisenMaalit + ".");

    }

}

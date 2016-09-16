package logiikka;


public class Peli {
    // 2 joukkuetta
    // randomeventgen joka luo pelin eventit
    // luo match reportin
    
    public static void main(String[] args) {
        Joukkue x = new Joukkue();
        Joukkue y = new Joukkue();
        
        x.lisaaPelaajat();
        y.lisaaPelaajat();
        
        System.out.println("Oman joukkueen pelaajat:\n");
        x.printPelaajat();
        System.out.println("Vihollisjoukkueen pelaajat:\n");
        y.printPelaajat();
        
        System.out.println("Oman joukkueesi kokonaisvoima: " + x.kokonaisAttribuutit());
        System.out.println("Vihollisjoukkueen kokonaisvoima: " + y.kokonaisAttribuutit());
        
        if (x.kokonaisAttribuutit() < y.kokonaisAttribuutit()) {
            System.out.println("Joukkueesi hävisi!");
        } else if (x.kokonaisAttribuutit() > y.kokonaisAttribuutit()) {
            System.out.println("Voitit!");
        } else {
            System.out.println("Tasapeli.");
        }
        
    }
    
}

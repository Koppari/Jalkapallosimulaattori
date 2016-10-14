**Aihe:** Jalkapallosimulaattori
Ohjelmassa valitaan ohjelman luomia satunnaisilla attribuuteilla varustettuja pelaajia jalkapallojoukkueeseen. Tämä joukkue sitten laitetaan toista pelaajien attribuuttien tasoa vastaavaa tietokonejoukkuetta vastaan. Ohjelma käy sitten pelin läpi tärkeästä tapahtumasta toiseen satunnaisen event-generatorin (jonka tapahtumat riippuvat pelaajien/tiimin attrbuuteista) avulla.

**Käyttäjät:** Simulaation seuraaja/pelaaja

**Käyttäjän toiminnot:** 
- uuden pelin aloittaminen
- joukkueen tietojen luonti (to-do)
- pelaajien valinta
- joukkueen kokonaistilastojen tarkistus
- uusien joukkueiden luonti

![Luokkakaavio](https://github.com/Koppari/Jalkapallosimulaattori/raw/master/dokumentaatio/lkaavio2.png?raw=true)
- luokan RandomEventGeneraattori metodeissa viitataan suoraan Joukkue- ja Pelaaja-luokan ilmentymiin

![Sekvenssikaavio pelin aloitukselle](https://github.com/Koppari/Jalkapallosimulaattori/raw/master/dokumentaatio/sekvenssi_pelinaloitus.jpg?raw=true)
![Sekvenssikaavio pelaajien valinnalle](https://github.com/Koppari/Jalkapallosimulaattori/raw/master/dokumentaatio/sekvenssi_pelaajienvalinta.jpg?raw=true)



**Aihe:** Jalkapallosimulaattori
Ohjelmassa valitaan ohjelman luomia satunnaisilla attribuuteilla varustettuja pelaajia jalkapallojoukkueeseen. Tämä joukkue sitten laitetaan toista pelaajien attribuuttien tasoa vastaavaa tietokonejoukkuetta vastaan. Ohjelma käy sitten pelin läpi tärkeästä tapahtumasta toiseen satunnaisen event-generatorin (jonka tapahtumat riippuvat pelaajien/tiimin attrbuuteista) avulla.

**Käyttäjät:** Simulaation seuraaja/pelaaja

**Käyttäjän toiminnot:** 
- uuden pelin aloittaminen
- joukkueen tietojen luonti
- pelaajien valinta
- pelin sammuttaminen

![Luokkakaavio](https://github.com/Koppari/Jalkapallosimulaattori/raw/master/dokumentaatio/lkaavio.png?raw=true)

- luokan RandomEventGeneraattori metodeissa viitataan myös suoraan Joukkue- ja Pelaaja-luokkiin
**Aihe:** Jalkapallosimulaattori
Ohjelmassa valitaan ohjelman luomia satunnaisilla attribuuteilla varustettuja (tai sitten valmiiksi luotuja?) pelaajia jalkapallojoukkueeseen. Tämä joukkue sitten laitetaan toista pelaajien attribuuttien tasoa vastaavaa tietokonejoukkuetta vastaan. Ohjelma käy sitten pelin läpi tärkeästä tapahtumasta toiseen satunnaisen event-generatorin (jonka tapahtumat riippuvat pelaajien/tiimin attrbuuteista) avulla.

**Käyttäjät:** Simulaation seuraaja/pelaaja

**Käyttäjän toiminnot:** 
- uuden pelin aloittaminen
- joukkueen tietojen luonti
- pelaajien valinta
- pelin sammuttaminen

![Luokkakaavio](http://i.imgur.com/si8vjQH.png)

- luokan RandomEventGeneraattori metodeissa viitataan myös suoraan Joukkue- ja Pelaaja-luokkiin
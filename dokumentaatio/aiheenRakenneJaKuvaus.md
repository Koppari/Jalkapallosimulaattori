**Aihe:** Jalkapallosimulaattori
Ohjelmassa valitaan ohjelman luomia satunnaisilla attribuuteilla varustettuja (tai sitten valmiiksi luotuja?) pelaajia jalkapallojoukkueeseen. T�m� joukkue sitten laitetaan toista pelaajien attribuuttien tasoa vastaavaa tietokonejoukkuetta vastaan. Ohjelma k�y sitten pelin l�pi t�rke�st� tapahtumasta toiseen satunnaisen event-generatorin (jonka tapahtumat riippuvat pelaajien/tiimin attrbuuteista) avulla.

**K�ytt�j�t:** Simulaation seuraaja/pelaaja

**K�ytt�j�n toiminnot:** 
- uuden pelin aloittaminen
- joukkueen tietojen luonti
- pelaajien valinta
- pelin sammuttaminen

![Luokkakaavio](http://i.imgur.com/si8vjQH.png)

- luokan RandomEventGeneraattori metodeissa viitataan my�s suoraan Joukkue- ja Pelaaja-luokkiin
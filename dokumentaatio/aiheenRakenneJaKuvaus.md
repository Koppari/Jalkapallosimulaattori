**Aihe:** Jalkapallosimulaattori
Ohjelmassa valitaan ohjelman luomia satunnaisilla attribuuteilla varustettuja pelaajia jalkapallojoukkueeseen. T�m� joukkue sitten laitetaan toista pelaajien attribuuttien tasoa vastaavaa tietokonejoukkuetta vastaan. Ohjelma k�y sitten pelin l�pi t�rke�st� tapahtumasta toiseen satunnaisen event-generatorin (jonka tapahtumat riippuvat pelaajien/tiimin attrbuuteista) avulla.

**K�ytt�j�t:** Simulaation seuraaja/pelaaja

**K�ytt�j�n toiminnot:** 
- uuden pelin aloittaminen
- satunnaisesti luodun joukkueen valinta
- matsien generointi
- joukkueen kokonaistilastojen tarkistus lopussa
- uusien joukkueiden luonti

**Rakennekuvaus:**
Main-luokka luo ensin Peli-olion joka laitetaan uuden luodun GUI-olion sis�lle. GUI-oliolla luodaan sitten pelille k�ytt�liittym�. Peli on ohjelman keskeisin luokka, sill� se on vastuussa pelin tapahtumien generoimisesta, sen tilastoista ja siihen liittyvien joukkueiden ilmoittamisesta. Peliin kuuluu Joukkueita joihin taas kuuluu Pelaajia. TiedostonHoitaja hoitaa matsireporttien tekemisen omassa luokassaan. RandomEventGeneraattori laskee esimerkiksi joukkueiden paremmuuksia ja kertoo Pelille ketk� pelaajat ja kumpi joukkue tekev�t mit�kin.

![Luokkakaavio](https://github.com/Koppari/Jalkapallosimulaattori/raw/master/dokumentaatio/lkaavio2.png?raw=true)
- luokan RandomEventGeneraattori metodeissa viitataan suoraan Joukkue- ja Pelaaja-luokan ilmentymiin

![Sekvenssikaavio pelin aloitukselle](https://github.com/Koppari/Jalkapallosimulaattori/raw/master/dokumentaatio/sekvenssi_pelinaloitus.jpg?raw=true)
![Sekvenssikaavio pelaajien valinnalle](https://github.com/Koppari/Jalkapallosimulaattori/raw/master/dokumentaatio/sekvenssi_pelaajienvalinta.jpg?raw=true)



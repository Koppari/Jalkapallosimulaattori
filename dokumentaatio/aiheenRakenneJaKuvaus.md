**Aihe:** Jalkapallosimulaattori
Ohjelmassa valitaan ohjelman luomia satunnaisilla attribuuteilla varustettuja pelaajia jalkapallojoukkueeseen. Tämä joukkue sitten laitetaan toista pelaajien attribuuttien tasoa vastaavaa tietokonejoukkuetta vastaan. Ohjelma käy sitten pelin läpi tärkeästä tapahtumasta toiseen satunnaisen event-generatorin (jonka tapahtumat riippuvat pelaajien/tiimin attrbuuteista) avulla.

**Käyttäjät:** Simulaation seuraaja/pelaaja

**Käyttäjän toiminnot:** 
- uuden pelin aloittaminen
- satunnaisesti luodun joukkueen valinta
- matsien generointi
- joukkueen kokonaistilastojen tarkistus lopussa
- uusien joukkueiden luonti

**Rakennekuvaus:**
Main-luokka luo ensin Peli-olion joka laitetaan uuden luodun GUI-olion sisälle. GUI-oliolla luodaan sitten pelille käyttöliittymä. Peli on ohjelman keskeisin luokka, sillä se on vastuussa pelin tapahtumien generoimisesta, sen tilastoista ja siihen liittyvien joukkueiden ilmoittamisesta. Peliin kuuluu Joukkueita joihin taas kuuluu Pelaajia. TiedostonHoitaja hoitaa matsireporttien tekemisen omassa luokassaan. RandomEventGeneraattori laskee esimerkiksi joukkueiden paremmuuksia ja kertoo Pelille ketkä pelaajat ja kumpi joukkue tekevät mitäkin.

![Luokkakaavio](https://github.com/Koppari/Jalkapallosimulaattori/raw/master/dokumentaatio/lkaavio2.png?raw=true)
- luokan RandomEventGeneraattori metodeissa viitataan suoraan Joukkue- ja Pelaaja-luokan ilmentymiin

![Sekvenssikaavio pelin aloitukselle](https://github.com/Koppari/Jalkapallosimulaattori/raw/master/dokumentaatio/sekvenssi_pelinaloitus.jpg?raw=true)
![Sekvenssikaavio pelaajien valinnalle](https://github.com/Koppari/Jalkapallosimulaattori/raw/master/dokumentaatio/sekvenssi_pelaajienvalinta.jpg?raw=true)



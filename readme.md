> [!NOTE]  
> The English translation for this exercise can be found in [this file](./readme.en.md).

# Yksikkötestaus JUnit-työkalulla

Tämä repositorio sisältää Java-harjoituksia, joiden avulla harjoittelet yksikkötestausta [JUnit-työkalun](https://junit.org) avustuksella.

Aikaisemmista tehtävistä poiketen tässä tehtävässä ei ole valmiita testejä, vaan kirjoitat testit itse. Testattavat Java-luokat ovat sen sijaan valmiiksi projektipohjassa.

Voit suorittaa testisi joko koodieditorisi testaustyökalulla ([VS Code](https://code.visualstudio.com/docs/java/java-testing), [Eclipse](https://www.vogella.com/tutorials/JUnitEclipse/article.html)) tai [Gradle-automaatiotyökalulla](https://docs.gradle.org/current/userguide/java_testing.html).


## Tehtävän aloittaminen

Aloita kloonaamalla Git-repositorio omalle koneellesi. Harjoitus täytyy tehdä omassa kopiossasi, eli varmista, että repositorion osoitteessa on mukana oma GitHub-käyttäjätunnuksesi! Oman kopiosi saat tekemällä tästä repositoriosta forkin tai kopion omalle GitHub-tilillesi.

```sh
git clone https://github.com/varmista-etta-github-tunnuksesi-on-repositorion-osoitteessa.git
```

Kloonattuasi projektin omalle koneellesi, avaa se koodieditorissasi.


## Vastausten lähettäminen

Kun olet saanut osan tai kaikki tehtävistä ratkaistua ja commitoinut vastauksesi, lähetä ratkaisut arvioitavaksi `git push`-komennolla. Git push käynnistää automaattisesti GitHub actions -workflow:n, joka kääntää sekä testaa ratkaisusi ja antaa jokaisesta testistä joko hyväksytyn tai hylätyn tuloksen.

Kun GitHub Actions on saanut koodisi suoritettua, näet tuloksen GitHub-repositoriosi [Actions-välilehdellä](../../actions). Arvioinnin valmistumiseen menee tyypillisesti pari minuuttia.

💡 *Voit lähettää ratkaisusi arvioitavaksi niin monta kertaa kuin on tarpeen tehtävän määräaikaan asti. Varmista kuitenkin, että viimeisin suoritus tuottaa parhaat pisteet, koska vain viimeisimmät pisteet jäävät voimaan.*


## Tehtävä 1: PriceFormatter

Tässä tehtävässä sinun tulee kirjoittaa yksikkötestejä valmiille `PriceFormatter`-luokalle ja siellä olevalle `formatPrice`-metodille. Metodi saa parametrinaan liukuluvun ja se palauttaa sen merkkijonoksi muotoiltuna tiettyjen sääntöjen mukaisesti. Metodin kutsuminen onnistuu esim. seuraavasti:

```java
PriceFormatter formatter = new PriceFormatter();
String price = formatter.formatPrice(3.141592653589793); // Palauttaa "3,14 €"
```

Perustapauksessa metodin pitäisi muotoilla annettu liukuluku muotoon `"123 456,79 €"`. Muotoilun suhteen on seuraavat tarkentavat säännöt:

1. Tuhaterottimena on välilyönti.
1. Desimaalierottimena on pilkku.
1. Desimaaliosa on kahden numeron pituinen, seuraavin tarkennuksin:
    1. Desimaaliosa pyöristetään lähimpään sadasosaan (eli senttiin). [Lisävinkki](./rounding.md).
    1. Jos desimaaliosa on pyöristyksen jälkeen nolla, jätetään desimaaliosa kokonaan pois.
1. Hinnan lopussa on Euro-symboli `€`.

**Huomaa, että sinun ei tarvitse itse toteuttaa yllä esitettyä metodia tai siihen kuuluvia ehtoja.** Metodin toteuttamisen sijasta harjoittelemme valmiin metodin *testaamista*.

### PriceFormatterTest *(perusteet)*

Toteuta tehtäväpohjan [PriceFormatterTest](./src/test/java/price/formatter/PriceFormatterTest.java)-luokkaan omat yksikkötestit, jotka varmistavat eri syötteiden avulla, että valmis `formatPrice`-metodi toimii edellä kuvatulla tavalla. Suosittelemme, että kirjoitat jokaista sääntöä kohden oman erillisen testimetodin.

Kutsu testimetodeissasi `formatPrice`-metodia erilaisilla parametreilla ja varmista, että metodin palauttamat arvot ovat esitettyjen sääntöjen mukaisia:

```java
PriceFormatter formatter = new PriceFormatter();
String result = formatter.formatPrice(Math.PI); // pitäisi olla 3,14 €
```

Voit suorittaa testisi joko koodieditorisi testaustyökalulla ([VS Code](https://code.visualstudio.com/docs/java/java-testing), [Eclipse](https://www.vogella.com/tutorials/JUnitEclipse/article.html)) tai [Gradle-automaatiotyökalulla](https://docs.gradle.org/current/userguide/java_testing.html):

```
./gradlew test --tests PriceFormatterTest       # unix
.\gradlew.bat test --tests PriceFormatterTest   # windows
```

✅ Huomaa, että omien testiesi tulisi tässä vaiheessa mennä läpi, koska testattavassa metodissa ei *pitäisi* olla loogisia virheitä.

💡 *Metodin testaamiseksi sinun ei tarvitse perehtyä sen lähdekoodiin. Tärkeämpää on, että tiedossasi on yllä esitetyt metodin toimintaa koskevat **vaatimukset**.*

💡 *Jos käytät testeissäsi lukuja, joka asettuvat tasan sadasosien "puoliväliin", saatat törmätä liukulukujen rajallista tarkkuutta koskeviin ongelmiin. Ongelma ei johdu Javasta eikä omasta tai testattavasta koodista, vaan siitä, miten tietokoneet käsittelevät desimaaliosia. Tarkempi selitys aiheesta löytyy tämän repositorion tiedostosta [rounding.md](./rounding.md) tai artikkelista ["What Every Computer Scientist Should Know About Floating-Point Arithmetic" (oracle.com)](https://docs.oracle.com/cd/E19957-01/806-3568/ncg_goldberg.html).*

⛔ *Et saa tehdän lainkaan muutoksia `PriceFormatter`-luokkaan.*


### Tehtävän pisteytys *(5 * 10 %)*

Se, että testit "menevät läpi", ei välttämättä tarkoita, että testit olisivat kattavat tai että testattavassa koodissa ei olisi bugeja. Jotta varmistamme, että testisi ovat kattavat, ajetaan testisi myös **viittä eri tavoin bugista versioita vasten** 😈. Kirjoittamiesi testien tulee löytää näissä versioissa piilevät bugit.

Jos kirjoitit kattavat testitapaukset kaikille aiemmin esitetyille muotoilusäännöille, tämä osa ei edellytä muutoksia testeihisi 😎. Mikäli testisi eivät löydä kaikkia bugeja automaattisessa tarkastuksessa, jatka [`PriceFormatterTest`-luokan](./src/test/java/price/formatter/PriceFormatterTest.java) kehittämistä ja varmista, että testisi tarkastavat kaikki ylempänä esitetyt muotoilusäännöt erilaisten syötteiden avulla.


💡 *Tässä osassa testisi ajetaan viiteen kertaan viittä eri tavoin bugista `formatPrice`-metodia vasten. Saat kustakin suorituksesta pisteet, mikäli buginen versio `formatPrice`-metodista **aiheuttaa virheen** vähintään yhdessä testimetodissasi. Jos testit menevät läpi, tarkoittaa se, että bugi jäi löytymättä. Tällöin myös pisteet jäävät saamatta.*

💡 *Näiden soveltavien testien suorittaminen edellyttää, että alkuperäinen toimiva versio `formatPrice`-metodista läpäisee ensin omat testisi.*

🚀 *Tehtävän tarkastuksessa sovelletaan menetelmää nimeltä mutaatiotestaus: "Mutation testing is used to design new software tests and evaluate the quality of existing software tests. Mutation testing involves modifying a program in small ways." [(Wikipedia)](https://en.wikipedia.org/wiki/Mutation_testing)*



## Tehtävä 2: DayOfYear *(soveltava, 2 * 25 %)*

Tämän tehtävärepositorion toisessa tehtävässä sinun tulee toteuttaa JUnit-yksikkötestit [valmiiksi annetulle `DayOfYear`-luokalle](./src/main/java/refactoring/DayOfYear.java). Luokassa on yksi staattinen metodi nimeltään `dayOfYear`. Tämä metodi saa parametreinaan päivämäärän kolmena kokonaislukuna ja sen pitäisi palauttaa annetun päivämäärän järjestysnumeron kyseisenä vuonna (1-366). Vuoden ensimmäinen päivä on numeroltaan 1 ja viimeinen vuodesta riippuen joko 365 tai 366.

Metodin sisällön lukeminen ja ymmärtäminen eivät ole testien kannalta välttämätöntä. Suunnittele testitapaukset sen mukaan, miten metodin **pitäisi** toimia eikä sen mukaan miten se oikeasti toimii.

> *"Some data sets specify dates using the year and day of year rather than the year, month, and day of month. The day of year (DOY) is the sequential day number starting with day 1 on January 1st."*
>
> [Day of the Year (DOY) calendar. National Snow and Ice Data Center](https://nsidc.org/data/user-resources/help-center/day-year-doy-calendar)

Voit tutkia eri päivämääriä vastaavia "day of year"-lukuja esimerkiksi [Day of the Year (DOY) calendar -kalenterista](https://nsidc.org/data/user-resources/help-center/day-year-doy-calendar) (National Snow and Ice Data Center). Huomaa, että maaliskuusta alkaen eri päivien numerot vaihtelevat tavallisina- ja karkausvuosina.

**Annetussa metodissa on bugeja**, jotka tässä tehtävässä tulee löytää omilla JUnit-testeillä.


### Osa 2.1: kirjoita `dayOfYear`-metodille JUnit-yksikkötestit

Lisää projektiin uusi testiluokka `DayOfYearTest`, jossa hyödynnät  JUnit-testikirjastoa testataksesi `dayOfYear`-metodin toimivuutta erilaisilla testitapauksilla. Testiluokat tulee lisätä [src/test/java/](./src/test/java/)-hakemistoon, jotta JUnit löytää ne ja jotta niissä voidaan hyödyntää JUnit-kirjastoa. Testiluokat sijaitsevat tyypillisesti samannimisessä paketissa kuin testattavat luokat, eli tässä tapauksessa luo `src/test/java`-hakemistoon tätä uutta testiä varten paketti nimeltä `refactoring`. Lisää testiluokkasi alkuun myös rivi: `package refactoring;`.

Kirjoita testiluokkaasi riittävät testitapaukset, jotta voit todentaa metodin toiminnan ja siinä mahdollisesti esiintyvät virheet vuoden eri ajankohtina huomioiden karkausvuosilogiikan. Kirjoita eri testitapaukset kukin omaan `@Test`-metodiin.

Voit suorittaa testiluokkasi tuttuun tapaan joko koodieditorillasi tai Gradlen avulla. Automaattisessa arvioinnissa testisi suoritetaan komennolla:

```
./gradlew test --tests DayOfYearTest       # unix
.\gradlew.bat test --tests DayOfYearTest   # windows
```

💡 *Tässä vaiheessa testisi toivottavasti löytävät metodissa valmiiksi olevia bugeja, joten kaikki testit eivät vielä mene läpi.*

💡 *Muista lisätä uusi `src/test/refactoring`-hakemisto ja `DayOfYearTest.java`-tiedosto versionhallintaan `git add`-komennolla. Katso tarvittaessa `git status`, joka kertoo miten tämä tehdään.*


### Osa 2.2: `dayOfYear`-metodin korjaus ja refaktorointi

Tähän asti olet ohjelmointiopinnoissasi kenties keskittynyt lähinnä saamaan ohjelmasi toimimaan tehtävänannon mukaisesti kiinnittämättä suurempaa huomiota sen ymmärrettävyyteen tai jatkokehitettävyyteen. Voimme olettaa myös annetun `dayOfYear`-metodin syntyneen näin. Ammatillisessa ohjelmistokehityksessä on harvinaista, että samaa koodia työstettäisiin vain kerran tai vain yhden kehittäjän toimesta. Päinvastoin, koodia kirjoitetaan tiimeissä, joissa kehittäjät vaihtuvat ja olemassa oleviin ominaisuuksiin tehdään jatkuvasti muutoksia ja korjauksia.

Tulet siis itse jatkokehittämään jonkun toisen vuosia sitten kirjoittamaa koodia, aivan kuten joku muu tulee jatkokehittämään sinun koodiasi. Tällöin on erittäin tärkeää, että koodi on muokattavissa ilman odottamattomia rikkoutumisia ja että muut kehittäjät ymmärtävät toistensa koodia ja pystyvät hyödyntämään ja muokkaamaan sitä.

Kun luet `dayOfYear`-metodin sisältämää koodia tarkemmin, huomaat, että siinä on käytetty ohjelmoinnin perusrakenteita melko suppeasti. Koodi koostuukin erittäin pitkästä `if-else`-rakenteesta sekä samanlaisista kokonaislukujen yhteenlaskuista. Samat numerot myös esiintyvät koodissa toistuvasti ja ne saattavat olla virheellisiä.

Bugien korjauksessa sinulle voi olla hyötyä Javan valmiista [`Year`-luokasta ja sen `isLeap`-metodista](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/Year.html#isLeap(long)). Voit myös tarkistaa eri kuukausien oikeat pituudet esimerkiksi [Wikipediasta](https://fi.wikipedia.org/wiki/Kuukausi). Kuukausien pituuksien "kovakoodaus" ei toisaalta ole välttämättä kannattavaa, koska Javasta löytyy valmiiksi sekä [Month](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/Month.html)- että [YearMonth](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/YearMonth.html)-luokat, jotka [sisältävät logiikkaa kuukausien pituuksien laskemiseksi](https://github.com/AdoptOpenJDK/openjdk-jdk9u/blob/9347c48cc4ce5d966c7f0c0a751c313eb0cba99a/jdk/src/java.base/share/classes/java/time/Month.java#L425-L437).

📣 Korjaamasi koodin tulee olla ymmärrettävämpää ja ylläpidettävämpää kuin alkuperäinen koodi, mutta toimia ulkoisesti samalla tavalla. **Et siis saa muuttaa metodin otsikkoa etkä esimerkiksi parametrien järjestystä.**

> *"Refaktorointi tarkoittaa prosessia, jossa tietokoneohjelman lähdekoodia muutetaan siten, että sen toiminnallisuus säilyy, mutta sen sisäinen rakenne muuttuu. Muutokset voivat koskea esimerkiksi luettavuutta tai ohjelmakomponenttien työnjaon selkeyttämistä."*
>
> Wikipedia. [Refaktorointi](https://fi.wikipedia.org/wiki/Refaktorointi). Viitattu 28.11.2023. [CC BY–SA 3.0](https://fi.wikipedia.org/wiki/Wikipedia:Creative_Commons_Attribution-Share_Alike_3.0_Unported_-lisenssiehdot)


⛔ *Javan standardikirjastossa on olemassa useita valmiita toimivia toteutuksia päivän järjestysnumeron laskemiselle. Oikeassa ohjelmistoprojektissa sinun tulisi luonnollisesti käyttää valmista ratkaisua, eikä yrittää keksiä pyörää uudestaan. Tämän harjoituksen tavoitteena on kuitenkin opetella testaamaan ja refaktoroimaan koodia, joten suosittelemme muodostamaan oman ratkaisun annettua koodia muokkaamalla.*


**"Code smells"**

Tutustu seuraaviin "koodin hajuihin" esimerkkikoodissa ja parantele koodia parhaasi mukaan:

- [Don't Repeat Yourself](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself)
- [Comments Where Needed](https://stackoverflow.blog/2021/12/23/best-practices-for-writing-code-comments/)
- [Fail Fast](https://en.wikipedia.org/wiki/Fail-fast)
- [Avoid Magic Numbers](https://en.wikipedia.org/wiki/Magic_number_(programming))
- [One Purpose For Each Variable](https://en.wikipedia.org/wiki/Single-responsibility_principle)

Edellä mainittu lista koodin "hajuista" on käyty läpi tämän saman esimerkkikoodin avulla osoitteessa [https://web.mit.edu/6.005/www/fa16/classes/04-code-review/](https://web.mit.edu/6.005/www/fa16/classes/04-code-review/). Voit käyttää myös muita lähteitä.


**Linkit**

* [Year-luokka](https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/time/Year.html)
* [Month-luokka](https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/time/Month.html)
* [YearMonth-luokka](https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/time/YearMonth.html)
* [Tehtävän alkuperäinen lähde](https://web.mit.edu/6.005/www/fa16/classes/04-code-review/)


### Virheelliset parametrit &mdash; bugi vai ominaisuus? *(pohdittavaa)*

`dayOfYear`-esimerkkikoodin [alkuperäisessä lähteessä](https://web.mit.edu/6.005/www/fa16/classes/04-code-review/#fail_fast) tuodaan esiin fail fast -ajattelutapa, jonka mukaisesti ongelmat on sitä helpompi korjata mitä aikaisemmin ne havaitaan. Tämän metodin tapauksessa metodi palauttaa kokonaislukuja riippumatta siitä, ovatko kuukausien ja päivien numerot annettu oikeassa järjestyksessä tai ovatko ne sallituilta väleiltä.

Voit halutessasi lisätä metodiin tarkastuksia ja heittää esimerkiksi `IllegalArgumentException`-poikkeuksen, mikäli metodille annetut numerot eivät vastaa todellista päivämäärää. Tämä ei kuitenkaan ole tehtävän kannalta välttämätöntä, emmekä laske virheellisiä syötteitä tämän metodin bugeiksi.


### Tehtävän pisteytys *(25 % + 25 %)*

`DayOfYear`-tehtävä arvioidaan automaattisesti kahdessa osassa. Ensimmäisessä osassa suoritamme oman `DayOfYearTest`-testiluokkasi, jonka tulee läpäistä kaikki kirjoittamasi testit.

```
./gradlew test --tests DayOfYearTest       # unix
.\gradlew.bat test --tests DayOfYearTest   # windows
```

Toisessa osassa suoritamme erillisen testiluokan, jossa varmistetaan, että `dayOfYear`-metodi toimii oikein tekemiesi korjausten jälkeen. Mikäli erillinen testiluokka havaitsee virheitä automaattisessa arvioinnissa, lue tarkasti [Actions-välilehdeltä](../../actions) löytyvä raportti testien suorituksesta.



## Lisenssi ja tekijät

### DayOfYear-esimerkkiluokka (Smelly Example #1)

Tehtävässä hyödynnetty [`DayOfYear`-esimerkkiluokka](./src/main/java/refactoring/DayOfYear.java) on lainattu [MIT:n Software Construction -kurssin oppimateriaaleista](https://web.mit.edu/6.005/www/fa16/classes/04-code-review/). Sen tekijät ja lisenssi ovat:

> *Collaboratively authored with contributions from: Saman Amarasinghe, Adam Chlipala, Srini Devadas, Michael Ernst, Max Goldman, John Guttag, Daniel Jackson, Rob Miller, Martin Rinard, and Armando Solar-Lezama.*
>
> *Licensed under [CC BY-SA 4.0](https://creativecommons.org/licenses/by-sa/4.0/deed.fi).*


### Tämä tehtävä

Tämän tehtävän on kehittänyt Teemu Havulinna ja se on lisensoitu [Creative Commons BY-NC-SA -lisenssillä](https://creativecommons.org/licenses/by-nc-sa/4.0/).

Tehtävänannon, lähdekoodien ja testien toteutuksessa on hyödynnetty ChatGPT 3.5 -kielimallia sekä GitHub copilot -tekoälyavustinta.

## Ratkaisun tallentaminen

Tee tästä tehtäväpohjasta oma kopio (fork tai kopio) ja tallenna ratkaisusi myös **yksityiseen (private) GitHub-repositorioon omalla GitHub-tililläsi**. Älä julkaise ratkaisuasi julkisessa repositoriossa.

Palauta ratkaisusi opettajan ilmoittamaan palautusjärjestelmään.

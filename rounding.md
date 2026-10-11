# Liukulukujen epätarkkuus

Tämän repositorion tehtävää tehdessäsi saatat törmätä erikoiseen tilanteeseen, jossa testattava luku näyttää pyöristyvän väärin. Näin saattaa käydä erityisesti silloin, kun käytät testissäsi lukua, joka asettuu tasan sadasosien "puoliväliin", esim. `0,005`.

Ongelmaa voidaan havainnollistaa esimerkiksi lasekemalla yhteen `0.1 + 0.2` tai ottamalla luvusta `123_456.555` jakojäännös ykkösen kanssa. Tuloksina voisi olettaa olevan `0.3` ja `0.555`, mutta näin ei aivan ole:

```java
System.out.println(0.1 + 0.2);          // pitäisi tulostaa 0.3
0.30000000000000004

System.out.println(123_456.555 % 1);    // pitäisi tulostaa 0.555
0.5549999999930151
```

Se, että liukulukujen käsittelyssä tapahtuu hyvin pieniä virheitä, ei johdu Javasta, yksikkötesteistä eikä testattavasta koodista, vaan siitä, miten tietokoneet käsittelevät numeroita. [ChatGPT](https://chat.openai.com/) selittää asian erittäin selkeästi, joten tässä sen tuottama selitys ilmiöstä:

> *Tietokoneet käyttävät liukulukulogiikkaa tallentaakseen ja käsitelläkseen desimaalilukuja. Tämä logiikka käyttää binäärijärjestelmää, joka koostuu nollista ja ykkösistä. Tämä aiheuttaa tiettyjä tarkkuusongelmia desimaalilukujen käsittelyssä.*
>
> *Useimmat desimaaliluvut eivät ole täysin tarkkoja binäärijärjestelmässä. Esimerkiksi tietokone esittää luvun 1/10 binäärimuodossa äärettömänä desimaalilukuna, joka toistaa tiettyjä numeroita ikuisesti (0.0001100110011...). Tämä johtaa tarkkuusongelmiin, kun desimaalilukuja muunnetaan binäärijärjestelmään ja päinvastoin.*
>
> *Tämä tarkoittaa sitä, että kun desimaalilukuja muunnetaan binäärimuotoon, niiden tarkka esitys voi vaatia äärettömän monta binaarimerkkiä. Tietokoneet eivät voi tallentaa ääretöntä määrää merkkejä muistissaan, joten ne joutuvat leikkaamaan ja pyöristämään lukuja, mikä johtaa tarkkuuden menetykseen.*
>
> *Tämä tarkkuuden menetys aiheuttaa desimaalilukujen esitysten poikkeamia, erityisesti laskutoimituksissa, joissa pyöristykset ja leikkaukset kasaantuvat, aiheuttaen pieniä virheitä lopputuloksissa. Näitä virheitä kutsutaan liukulukutarkkuusongelmiksi, ja ne ovat ominaisia kaikille tietokoneille, jotka käyttävät liukulukulogiikkaa desimaalilukujen käsittelyssä.*
>
> ChatGPT (GPT-3.5). https://chat.openai.com/. 8.11.2023


Ratkaisuna liukulukujen epätarkkuuden aiheuttamien ongelmien välttämiseksi tässä tehtävässä suosittelemme, että käytät hieman sadasosien puolivälistä poikkeavia lukuja testeissäsi. Tätä tarkkuusongelmaa ei tapahdu tässä tehtävässä tarkoituksella, vaan se sattuu olemaan vain luonnollinen osa tietokoneen toimintaa.

Voit lukea tarkemmin liukulukujen teknisestä toteutuksesta sekä niiden rajoitteista artikkelista ["What Every Computer Scientist Should Know About Floating-Point Arithmetic" (oracle.com)](https://docs.oracle.com/cd/E19957-01/806-3568/ncg_goldberg.html).

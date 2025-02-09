# Projektni zadatak iz kolegija Metode i tehnike testiranja programske podrške

## Uvod

<div align=justify> 
Testiranje softvera je ključna faza u razvoju programske podrške koja osigurava kvalitetu, stabilnost i ispravnost aplikacije. Cilj testiranja je identificirati greške, osigurati ispunjavanje zahtjeva te poboljšati korisničko iskustvo. Testiranje može biti ručno (manual testing) ili automatizirano (automated testing), a u ovom projektu koristi se Selenium za automatizirano testiranje web aplikacija. U ovome se projektu testiraju funkcionalnosti web aplikacije Njuškalo - najpopularnije hrvatske web stranice za oglašavanje.
</div>

## Automatizirano testiranje sa Seleniumom
<div align=justify> 
Selenium je popularan okvir (framework) za testiranje web aplikacija koji omogućava automatizirano izvršavanje testova u stvarnim preglednicima. Pomoću Seleniuma možemo simulirati radnje korisnika poput klikanja, unosa podataka, navigacije i provjere elemenata na stranici koje će nam uvelike pomoći u testiranju aplikacije.
</div>

## Tehnologije i tehnike korištene u projektu

- **Maven** – Alat za upravljanje projektima i ovisnostima (dependencies), omogućava jednostavnu integraciju potrebnih knjižnica (libraries) kao što su *Selenium, TestNG, WebDriverManager* i dr.
- **Surefire Plugin** – Maven plugin koji omogućava izvršavanje testova i generiranje detaljnih izvještaja u XML i HTML formatu.
- **TestNG** – Okvir za upravljanje testovima koji omogućava organizaciju test slučajeva, definiranje testnih skupova (*test suites*) i paralelno izvršavanje testova.
- **Selenium WebDriver** – Omogućava interakciju s web stranicama i pokretanje testova u različitim preglednicima.
- **WebDriverManager** – Alat koji automatski upravlja verzijama pregledničkih drivera, eliminirajući potrebu za ručnim preuzimanjem i konfiguriranjem.
- **Cross-browser testiranje** – Testovi se pokreću u isto vrijeme (*threading*) na više različitih preglednika (*Chrome, Firefox, Edge*) kako bi se osigurala kompatibilnost aplikacije.
- **POM (Page Object Model)** – Arhitekturni stil koji organizira testni kod tako da svaki web element i funkcionalnost imaju svoje klase, čime se poboljšava održavanje i ponovna upotreba koda.
- **Selenium Grid** – Omogućava distribuirano i paralelno testiranje na više uređaja i preglednika, čime se povećava brzina i efikasnost testnog procesa.

## Popis alata potrebnih za pokretanje projekta

- [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/?section=windows#section=windows)
- [Open-JDK 23](https://www.oracle.com/java/technologies/downloads/#jdk23-windows)
- [Google Chrome](https://www.google.com/chrome/)
- [Mozilla Firefox](https://www.mozilla.org/en-US/firefox/new/)
- [Microsoft Edge](https://www.microsoft.com/en-us/edge/download)


## Upute

- Preuzeti i instalirati gore navedene alate
- Klonirati ovaj repozitorij s [GitHuba](https://github.com/Matej293/MTTPP-project) u `C:\Users\<user>\IdeaProjects\` i otvoriti ga u [IntelliJ]((https://www.jetbrains.com/idea/download/?section=windows#section=windows))
- Desni klik na projekt u gornjem lijevom kutu -> Maven -> Sync project
- Preuzeti [Apache Maven](https://maven.apache.org/download.cgi) u željenu mapu i postaviti bin direktorij u system `PATH` putanju (npr. <br>`C:\Program Files\apache-maven-3.9.9\bin`)
- Preuzeti [Selenium server](https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.28.0/selenium-server-4.28.1.jar) i postaviti ga u željenu mapu
- Pokrenuti komandnu liniju u mapi gdje se nalazi `selenium-server-4.28.1.jar` i pokrenuti komandu:
<br>`java -jar selenium-server-4.28.1.jar standalone`
- Pokrenuti komandnu liniju i pokrenuti naredbe:
<br>`cd C:\Users\<user>\IdeaProjects\MTTPP-project`
<br>`mvn clean install`
- Pričekati dok se testovi izvrše
- Otvoriti datoteku izvještaja testova koja se nalazi u <br>`C:\Users\<user>\IdeaProjects\MTTPP-project\target\surefire-reports\index.html`

## Reference

- [MVN repozitorij za dependencies](https://mvnrepository.com/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- [Selenium dokumentacija](https://www.selenium.dev/documentation/overview/)
- [Selenium Grid](https://www.selenium.dev/documentation/grid/getting_started/)
- [Selenium server releases](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.28.0)
- [Stranica na kojoj je provedeno testiranje - Njuškalo.hr](https://www.njuskalo.hr/)
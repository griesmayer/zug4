# Projekt anlegen
## vaadin Start Projekt
Vaadin ist ein Java-Framework zur Entwicklung von modernen Webanwendungen.

Öffnen der WebSeite https://start.vaadin.com/

<img alt="" src="z_readme/001.png" width="200"/>

Definieren der Views

<img alt="" src="z_readme/002.png" width="200"/>

* Empty View für die Infos
* Hello World View für copy von Buttons

Download des Projekts

<img alt="" src="z_readme/003.png" width="200"/>
<br>
<img alt="" src="z_readme/004.png" width="200"/>

* Domain angeben
* Projektname

Publish to GIT

## IntelliJ Start Projekt

Clone des GitHub Repository

<img alt="" src="z_readme/005.png" width="200"/>
<br>
<img alt="" src="z_readme/006.png" width="200"/>

Beide Dateien löschen

<img alt="" src="z_readme/007.png" width="200"/>

```
public class Application implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

Projekt ausführen

<img alt="" src="z_readme/008.png" width="200"/>
<br>
<img alt="" src="z_readme/009.png" width="200"/>

# Basisklasse

## Lombok

Lombok ist eine Java-Bibliothek, die dir hilft:
* Getter
* Setter
* Konstruktoren (NICHT da Zuweisung)
* toString()
* equals() (NICHT, da wir einen ID Vergleich machen)
automatisch zu generieren.
<br>
<img alt="" src="z_readme/101.png" width="200"/>

Für die Basisklasse benötigen wir ein Package domain.

<img alt="" src="z_readme/102.png" width="200"/>

Die Klammern sollen in der selben Zeile dargestellt werden.

<img alt="" src="z_readme/103.png" width="200"/>

Die Exception wir in einem eigenen Package erstellt. I,mplementierung wie bisher.

<img alt="" src="z_readme/104.png" width="200"/>

Die Abhängigkeiten werden in der Datei pom.xml definiert. Dabei werden die notwendigen Frameworks geladen.

<img alt="" src="z_readme/105.png" width="200"/>

Das Projekt verwendet lombok.

```
// Es wird eine Datenbank Tabelle
@Entity
// Generiet die get Methoden - wie gewohnt
@Getter
// Generiet die set Methoden - wie gewohnt
@Setter
// Erzeugt mir eine toString Methode - lt. lombok
@ToString
public class Zug {
```

```
    public void setDauerMin(int dauerMin) throws ZugException {
        if (dauerMin < 1)
            throw new ZugException("Fehler: ungültige Dauer - muss >= 1 sein!");
        this.dauerMin = dauerMin;
    }
```

Eigene Implementierung der setDauerMin.

## Primary key

```
    // Dient als Zähler für den primary key
    private static final AtomicLong sequence = new AtomicLong(1000);
    // Die Eigenschaft dient als primarykey
    @Id
    private Long    zugId;
```

Zusätzlich zur setZugId(zugId) soll noch folgendes implementiert werden.

```
    public void setZugId() {
        setZugId(sequence.getAndIncrement());
    }
```

Wir können die ID automatisch über den Konstruktor vergeben lassen.

```
    public Zug(String abBahnhof, String anBahnhof, int dauerMin, boolean hatErsteKlasse, String zugArt) throws ZugException {
        setZugId();
```

## hashcode & equals

Beide Methoden können über IntelliJ generiert werden.

```
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Zug zug = (Zug) o;
        return Objects.equals(zugId, zug.zugId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(zugId);
    }
```

Bei der equals Methode gehört NUR die zugId auf Gleichheit geprüft.

Bei der hashcode Methode verwenden wir die hashcode der Klasse Long.

## clone

Wir benötigen einen Konstruktor inkl. der zugId. Die Zug ID soll beim clone gleich bleiben.

```
    public Zug(Long zugId, String abBahnhof, String anBahnhof, int dauerMin, boolean hatErsteKlasse, String zugArt) throws ZugException {
        setZugId(zugId);
        setAbBahnhof (abBahnhof);
        setAnBahnhof (anBahnhof);
        setDauerMin (dauerMin);
        setHatErsteKlasse (hatErsteKlasse);
        setZugArt (zugArt);
    }

    @Override
    public Zug clone() {
        return new Zug(zugId, abBahnhof, anBahnhof, dauerMin, hatErsteKlasse, zugArt);
    }
```

Der Konsturktor auch eine Exception werfen kann. Der Methodenkopf muss gleich bleiben.

```
    // kann beim Methodenkopf die throws clause weglassen
    @SneakyThrows
    @Override
    public Zug clone() {
        return new Zug(zugId, abBahnhof, anBahnhof, dauerMin, hatErsteKlasse, zugArt);
    }
```


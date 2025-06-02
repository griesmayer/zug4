package at.spengergasse.vaadin.domain;

import at.spengergasse.vaadin.exception.ZugException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

// Es wird eine Datenbank Tabelle
@Entity
// Generiet die get Methoden - wie gewohnt
@Getter
// Generiet die set Methoden - wie gewohnt
@Setter
// Erzeugt mir eine toString Methode - lt. lombok
@ToString
public class Zug {
    // Dient als Zähler für den primary key
    private static final AtomicLong sequence = new AtomicLong(1000);
    // Die Eigenschaft dient als primarykey
    @Id
    private Long    zugId;

    private String  abBahnhof;
    private String  anBahnhof;
    private int     dauerMin;
    private boolean hatErsteKlasse;
    private String  zugArt;

    public Zug(Long zugId, String abBahnhof, String anBahnhof, int dauerMin, boolean hatErsteKlasse, String zugArt) throws ZugException {
        setZugId(zugId);
        setAbBahnhof (abBahnhof);
        setAnBahnhof (anBahnhof);
        setDauerMin (dauerMin);
        setHatErsteKlasse (hatErsteKlasse);
        setZugArt (zugArt);
    }

    public Zug(String abBahnhof, String anBahnhof, int dauerMin, boolean hatErsteKlasse, String zugArt) throws ZugException {
        setZugId();
        setAbBahnhof (abBahnhof);
        setAnBahnhof (anBahnhof);
        setDauerMin (dauerMin);
        setHatErsteKlasse (hatErsteKlasse);
        setZugArt (zugArt);
    }

    public void setZugId() {
        setZugId(sequence.getAndIncrement());
    }

    public void setDauerMin(int dauerMin) throws ZugException {
        if (dauerMin < 1)
            throw new ZugException("Fehler: ungültige Dauer - muss >= 1 sein!");
        this.dauerMin = dauerMin;
    }

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

    // kann beim Methodenkopf die throws clause weglassen
    @SneakyThrows
    @Override
    public Zug clone() {
        return new Zug(zugId, abBahnhof, anBahnhof, dauerMin, hatErsteKlasse, zugArt);
    }
}

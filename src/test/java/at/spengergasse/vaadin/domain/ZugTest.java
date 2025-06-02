package at.spengergasse.vaadin.domain;

import at.spengergasse.vaadin.exception.ZugException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ZugTest {
    @Test
    void testSetDauerMinNegativ() {
        Zug z;

        try {
            z = new Zug("Wien", "Baden", -4, true, "REX");
            assertEquals(1,0);
        }
        catch (ZugException e) {
            System.out.println(e.getMessage());
            assertEquals(1,1);
        }
    }

    @Test
    void testSetDauerMinOK() {
        Zug z;

        try {
            z = new Zug("Wien", "Baden", 23, true, "REX");
            System.out.println(z);
            assertEquals(1,1);
        }
        catch (ZugException e) {
            System.out.println(e.getMessage());
            assertEquals(1,0);
        }
    }

}
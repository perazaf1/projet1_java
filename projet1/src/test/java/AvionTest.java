import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AvionTest {

    @BeforeEach
    public void reinitialiser() {
        Avion.vider();
    }

    @Test
    public void testCreation() {
        Avion a = new Avion("F-GKXA", "Airbus A320", 150);
        assertEquals("F-GKXA", a.getImmatriculation());
        assertEquals("Airbus A320", a.getModele());
        assertEquals(150, a.getCapacite());
        assertNull(a.getVolAssigne());
    }

    @Test
    public void testDisponibleSansVol() {
        Avion a = new Avion("F-GKXA", "Airbus A320", 150);
        assertTrue(a.verifierDisponibilite());
    }

    @Test
    public void testIndisponibleApresAffectation() {
        Avion a = new Avion("F-GKXA", "Airbus A320", 150);
        Vol v = new Vol("AF101", "Paris", "New York", "2025-04-20 10:00", "2025-04-20 13:00");
        a.affecterVol(v);
        assertFalse(a.verifierDisponibilite());
        assertEquals(v, a.getVolAssigne());
    }

    @Test
    public void testAjouter() {
        Avion a = new Avion("F-GKXA", "Airbus A320", 150);
        Avion.ajouter(a);
        assertEquals(1, Avion.getListe().size());
    }

    @Test
    public void testTrouver() {
        Avion a = new Avion("F-GKXA", "Airbus A320", 150);
        Avion.ajouter(a);
        Avion trouve = Avion.trouver("F-GKXA");
        assertNotNull(trouve);
        assertEquals("Airbus A320", trouve.getModele());
    }

    @Test
    public void testTrouverInexistant() {
        assertNull(Avion.trouver("INCONNU"));
    }

    @Test
    public void testSupprimer() {
        Avion a = new Avion("F-GKXA", "Airbus A320", 150);
        Avion.ajouter(a);
        assertTrue(Avion.supprimer("F-GKXA"));
        assertEquals(0, Avion.getListe().size());
    }

    @Test
    public void testSupprimerInexistant() {
        assertFalse(Avion.supprimer("INCONNU"));
    }
}

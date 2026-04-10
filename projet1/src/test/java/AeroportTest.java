import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AeroportTest {

    @BeforeEach
    public void reinitialiser() {
        Aeroport.vider();
        Vol.vider();
    }

    @Test
    public void testCreation() {
        Aeroport a = new Aeroport("CDG", "Paris", "Charles de Gaulle");
        assertEquals("CDG", a.getNom());
        assertEquals("Paris", a.getVille());
        assertEquals(0, a.getVolsDepart().size());
        assertEquals(0, a.getVolsArrivee().size());
    }

    @Test
    public void testAffecterVolDepart() {
        Aeroport a = new Aeroport("CDG", "Paris", "Charles de Gaulle");
        Vol v = new Vol("AF101", "Paris", "New York", "2025-04-20 10:00", "2025-04-20 13:00");
        a.affecterVol(v, true);
        assertEquals(1, a.getVolsDepart().size());
        assertEquals(0, a.getVolsArrivee().size());
        assertEquals(v, a.getVolsDepart().get(0));
    }

    @Test
    public void testAffecterVolArrivee() {
        Aeroport a = new Aeroport("JFK", "New York", "John F. Kennedy");
        Vol v = new Vol("AF101", "Paris", "New York", "2025-04-20 10:00", "2025-04-20 13:00");
        a.affecterVol(v, false);
        assertEquals(0, a.getVolsDepart().size());
        assertEquals(1, a.getVolsArrivee().size());
    }

    @Test
    public void testAjouter() {
        Aeroport a = new Aeroport("CDG", "Paris", "Charles de Gaulle");
        Aeroport.ajouter(a);
        assertEquals(1, Aeroport.getListe().size());
    }

    @Test
    public void testTrouver() {
        Aeroport a = new Aeroport("CDG", "Paris", "Charles de Gaulle");
        Aeroport.ajouter(a);
        assertNotNull(Aeroport.trouver("CDG"));
        assertNull(Aeroport.trouver("INCONNU"));
    }

    @Test
    public void testSupprimer() {
        Aeroport a = new Aeroport("CDG", "Paris", "Charles de Gaulle");
        Aeroport.ajouter(a);
        assertTrue(Aeroport.supprimer("CDG"));
        assertEquals(0, Aeroport.getListe().size());
        assertFalse(Aeroport.supprimer("CDG"));
    }
}

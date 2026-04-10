import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PiloteTest {

    @BeforeEach
    public void reinitialiser() {
        Pilote.vider();
        Vol.vider();
    }

    private Pilote creerPilote() {
        return new Pilote("P001", "Dupont Jean", "12 rue de la Paix", "0612345678",
                "EMP001", "2015-03-10", "LIC-FR-001", 5000);
    }

    @Test
    public void testCreation() {
        Pilote p = creerPilote();
        assertEquals("P001", p.getIdentifiant());
        assertEquals("Dupont Jean", p.getNom());
        assertEquals("EMP001", p.getNumeroEmploye());
        assertEquals("LIC-FR-001", p.getLicence());
        assertEquals(5000, p.getHeuresDeVol());
        assertNull(p.getVolAssigne());
    }

    @Test
    public void testObtenirRole() {
        Pilote p = creerPilote();
        assertEquals("Pilote", p.obtenirRole());
    }

    @Test
    public void testAffecterVol() {
        Pilote p = creerPilote();
        Vol v = new Vol("AF101", "Paris", "New York", "2025-04-20 10:00", "2025-04-20 13:00");
        p.affecterVol(v);
        assertEquals(v, p.getVolAssigne());
        assertEquals(v, p.obtenirVol());
    }

    @Test
    public void testAjouter() {
        Pilote p = creerPilote();
        Pilote.ajouter(p);
        assertEquals(1, Pilote.getListePilotes().size());
    }

    @Test
    public void testTrouver() {
        Pilote p = creerPilote();
        Pilote.ajouter(p);
        assertNotNull(Pilote.trouver("EMP001"));
        assertNull(Pilote.trouver("INCONNU"));
    }

    @Test
    public void testSupprimer() {
        Pilote p = creerPilote();
        Pilote.ajouter(p);
        assertTrue(Pilote.supprimer("EMP001"));
        assertEquals(0, Pilote.getListePilotes().size());
        assertFalse(Pilote.supprimer("EMP001"));
    }

    @Test
    public void testHeritage() {
        Pilote p = creerPilote();
        assertTrue(p instanceof Employe);
        assertTrue(p instanceof Personne);
    }
}

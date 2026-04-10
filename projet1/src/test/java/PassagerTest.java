import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PassagerTest {

    @BeforeEach
    public void reinitialiser() {
        Passager.vider();
        Reservation.vider();
        Vol.vider();
        Avion.vider();
    }

    private Passager creerPassager() {
        return new Passager("PA001", "Durand Alice", "3 rue Molière", "0655443322", "PP123456");
    }

    private Vol creerVol() {
        Vol v = new Vol("AF101", "Paris", "New York", "2025-04-20 10:00", "2025-04-20 13:00");
        Avion a = new Avion("F-GKXA", "Airbus A320", 150);
        v.setAvion(a);
        return v;
    }

    @Test
    public void testCreation() {
        Passager p = creerPassager();
        assertEquals("PA001", p.getIdentifiant());
        assertEquals("Durand Alice", p.getNom());
        assertEquals("PP123456", p.getPasseport());
        assertEquals(0, p.getReservations().size());
    }

    @Test
    public void testReserverVol() {
        Passager p = creerPassager();
        Vol v = creerVol();
        Reservation r = p.reserverVol(v, "2025-04-15");
        assertNotNull(r);
        assertEquals(1, p.getReservations().size());
        assertEquals("Confirmé", r.getStatut());
        assertEquals(1, v.getPassagers().size());
    }

    @Test
    public void testAnnulerReservation() {
        Passager p = creerPassager();
        Vol v = creerVol();
        Reservation r = p.reserverVol(v, "2025-04-15");
        String numero = r.getNumeroReservation();
        p.annulerReservation(numero);
        assertEquals(0, p.getReservations().size());
        assertEquals("Annulé", r.getStatut());
    }

    @Test
    public void testAnnulerReservationInexistante() {
        Passager p = creerPassager();
        // Ne doit pas lever d'exception
        p.annulerReservation("RES999");
        assertEquals(0, p.getReservations().size());
    }

    @Test
    public void testAjouter() {
        Passager p = creerPassager();
        Passager.ajouter(p);
        assertEquals(1, Passager.getListe().size());
    }

    @Test
    public void testTrouver() {
        Passager p = creerPassager();
        Passager.ajouter(p);
        assertNotNull(Passager.trouver("PA001"));
        assertNull(Passager.trouver("INCONNU"));
    }

    @Test
    public void testSupprimer() {
        Passager p = creerPassager();
        Passager.ajouter(p);
        assertTrue(Passager.supprimer("PA001"));
        assertEquals(0, Passager.getListe().size());
        assertFalse(Passager.supprimer("PA001"));
    }

    @Test
    public void testHeritage() {
        Passager p = creerPassager();
        assertTrue(p instanceof Personne);
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    private Passager passager;
    private Vol vol;

    @BeforeEach
    public void reinitialiser() {
        Reservation.vider();
        Passager.vider();
        Vol.vider();
        passager = new Passager("PA001", "Durand Alice", "Paris", "0655443322", "PP123456");
        vol = new Vol("AF101", "Paris", "New York", "2025-04-20 10:00", "2025-04-20 13:00");
    }

    @Test
    public void testCreation() {
        Reservation r = new Reservation("2025-04-15", passager, vol);
        assertEquals("RES001", r.getNumeroReservation());
        assertEquals("2025-04-15", r.getDateReservation());
        assertEquals("En attente", r.getStatut());
        assertEquals(passager, r.getPassager());
        assertEquals(vol, r.getVol());
    }

    @Test
    public void testAutoIncrementNumero() {
        Reservation r1 = new Reservation("2025-04-15", passager, vol);
        Reservation r2 = new Reservation("2025-04-15", passager, vol);
        Reservation r3 = new Reservation("2025-04-15", passager, vol);
        assertEquals("RES001", r1.getNumeroReservation());
        assertEquals("RES002", r2.getNumeroReservation());
        assertEquals("RES003", r3.getNumeroReservation());
    }

    @Test
    public void testConfirmerReservation() {
        Reservation r = new Reservation("2025-04-15", passager, vol);
        r.confirmerReservation();
        assertEquals("Confirmé", r.getStatut());
    }

    @Test
    public void testAnnulerReservation() {
        Reservation r = new Reservation("2025-04-15", passager, vol);
        r.annulerReservation();
        assertEquals("Annulé", r.getStatut());
    }

    @Test
    public void testModifierReservation() {
        Reservation r = new Reservation("2025-04-15", passager, vol);
        Vol autreVol = new Vol("AF202", "Lyon", "Madrid", "2025-05-01 08:00", "2025-05-01 10:00");
        r.modifierReservation(autreVol);
        assertEquals(autreVol, r.getVol());
    }

    @Test
    public void testAjouter() {
        Reservation r = new Reservation("2025-04-15", passager, vol);
        Reservation.ajouter(r);
        assertEquals(1, Reservation.getListe().size());
    }

    @Test
    public void testTrouver() {
        Reservation r = new Reservation("2025-04-15", passager, vol);
        Reservation.ajouter(r);
        assertNotNull(Reservation.trouver("RES001"));
        assertNull(Reservation.trouver("RES999"));
    }

    @Test
    public void testSupprimer() {
        Reservation r = new Reservation("2025-04-15", passager, vol);
        Reservation.ajouter(r);
        assertTrue(Reservation.supprimer("RES001"));
        assertEquals(0, Reservation.getListe().size());
        assertFalse(Reservation.supprimer("RES001"));
    }

    @Test
    public void testViderReinitialiseCompteur() {
        new Reservation("2025-04-15", passager, vol);
        new Reservation("2025-04-15", passager, vol);
        Reservation.vider();
        Reservation r = new Reservation("2025-04-15", passager, vol);
        assertEquals("RES001", r.getNumeroReservation());
    }
}

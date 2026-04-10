import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class VolTest {

    @BeforeEach
    public void reinitialiser() {
        Vol.vider();
        Avion.vider();
        Pilote.vider();
        PersonnelCabine.vider();
        Passager.vider();
        Reservation.vider();
    }

    private Vol creerVol() {
        return new Vol("AF101", "Paris CDG", "New York JFK", "2025-04-20 10:00", "2025-04-20 13:00");
    }

    private Avion creerAvion() {
        return new Avion("F-GKXA", "Airbus A320", 2);
    }

    private Pilote creerPilote() {
        return new Pilote("P001", "Dupont Jean", "Paris", "0612345678",
                "EMP001", "2015-03-10", "LIC-FR-001", 5000);
    }

    private PersonnelCabine creerPersonnel(String id, String empNum) {
        return new PersonnelCabine(id, "Agent " + id, "Lyon", "0600000000",
                empNum, "2020-01-01", "Steward");
    }

    @Test
    public void testCreation() {
        Vol v = creerVol();
        assertEquals("AF101", v.getNumeroVol());
        assertEquals("Paris CDG", v.getOrigine());
        assertEquals("New York JFK", v.getDestination());
        assertEquals("Planifié", v.getEtat());
        assertNull(v.getAvion());
        assertNull(v.getPilote());
        assertEquals(0, v.getPassagers().size());
        assertEquals(0, v.getPersonnelCabine().size());
    }

    @Test
    public void testPlanifierVol() {
        Vol v = creerVol();
        v.annulerVol();
        assertEquals("Annulé", v.getEtat());
        v.planifierVol();
        assertEquals("Planifié", v.getEtat());
    }

    @Test
    public void testAnnulerVol() {
        Vol v = creerVol();
        v.annulerVol();
        assertEquals("Annulé", v.getEtat());
    }

    @Test
    public void testModifierVol() {
        Vol v = creerVol();
        v.modifierVol("2025-05-01 09:00", "2025-05-01 12:00");
        assertEquals("2025-05-01 09:00", v.getDateHeureDepart());
        assertEquals("2025-05-01 12:00", v.getDateHeureArrivee());
    }

    @Test
    public void testAffecterEquipage() {
        Vol v = creerVol();
        Pilote p = creerPilote();
        PersonnelCabine pc1 = creerPersonnel("PC001", "EMP002");
        PersonnelCabine pc2 = creerPersonnel("PC002", "EMP003");
        ArrayList<PersonnelCabine> equipe = new ArrayList<PersonnelCabine>();
        equipe.add(pc1);
        equipe.add(pc2);
        v.affecterVol(p, equipe);
        assertEquals(p, v.getPilote());
        assertEquals(2, v.getPersonnelCabine().size());
        assertEquals(v, p.getVolAssigne());
        assertEquals(v, pc1.getVolAssigne());
    }

    @Test
    public void testAjouterPassager() {
        Vol v = creerVol();
        v.setAvion(creerAvion());
        Passager p1 = new Passager("PA001", "Alice", "Paris", "0600000001", "PP001");
        Passager p2 = new Passager("PA002", "Bob", "Lyon", "0600000002", "PP002");
        v.ajouterPassager(p1);
        v.ajouterPassager(p2);
        assertEquals(2, v.getPassagers().size());
    }

    @Test
    public void testCapaciteMaximale() {
        Vol v = creerVol();
        v.setAvion(creerAvion()); // capacité = 2
        Passager p1 = new Passager("PA001", "Alice", "Paris", "0600000001", "PP001");
        Passager p2 = new Passager("PA002", "Bob", "Lyon", "0600000002", "PP002");
        Passager p3 = new Passager("PA003", "Charlie", "Nantes", "0600000003", "PP003");
        v.ajouterPassager(p1);
        v.ajouterPassager(p2);
        v.ajouterPassager(p3); // doit être refusé
        assertEquals(2, v.getPassagers().size());
    }

    @Test
    public void testAjouter() {
        Vol v = creerVol();
        Vol.ajouter(v);
        assertEquals(1, Vol.getListe().size());
    }

    @Test
    public void testTrouver() {
        Vol v = creerVol();
        Vol.ajouter(v);
        assertNotNull(Vol.trouver("AF101"));
        assertNull(Vol.trouver("INCONNU"));
    }

    @Test
    public void testSupprimer() {
        Vol v = creerVol();
        Vol.ajouter(v);
        assertTrue(Vol.supprimer("AF101"));
        assertEquals(0, Vol.getListe().size());
        assertFalse(Vol.supprimer("AF101"));
    }
}

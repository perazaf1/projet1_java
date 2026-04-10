import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonnelCabineTest {

    @BeforeEach
    public void reinitialiser() {
        PersonnelCabine.vider();
        Vol.vider();
    }

    private PersonnelCabine creerPersonnel() {
        return new PersonnelCabine("PC001", "Martin Claire", "5 avenue Victor Hugo",
                "0698765432", "EMP002", "2018-06-01", "Hôtesse confirmée");
    }

    @Test
    public void testCreation() {
        PersonnelCabine pc = creerPersonnel();
        assertEquals("PC001", pc.getIdentifiant());
        assertEquals("Martin Claire", pc.getNom());
        assertEquals("EMP002", pc.getNumeroEmploye());
        assertEquals("Hôtesse confirmée", pc.getQualification());
        assertNull(pc.getVolAssigne());
    }

    @Test
    public void testObtenirRole() {
        PersonnelCabine pc = creerPersonnel();
        assertEquals("Personnel Cabine", pc.obtenirRole());
    }

    @Test
    public void testAffecterVol() {
        PersonnelCabine pc = creerPersonnel();
        Vol v = new Vol("AF101", "Paris", "New York", "2025-04-20 10:00", "2025-04-20 13:00");
        pc.affecterVol(v);
        assertEquals(v, pc.getVolAssigne());
        assertEquals(v, pc.obtenirVol());
    }

    @Test
    public void testAjouter() {
        PersonnelCabine pc = creerPersonnel();
        PersonnelCabine.ajouter(pc);
        assertEquals(1, PersonnelCabine.getListePersonnelCabine().size());
    }

    @Test
    public void testTrouver() {
        PersonnelCabine pc = creerPersonnel();
        PersonnelCabine.ajouter(pc);
        assertNotNull(PersonnelCabine.trouver("EMP002"));
        assertNull(PersonnelCabine.trouver("INCONNU"));
    }

    @Test
    public void testSupprimer() {
        PersonnelCabine pc = creerPersonnel();
        PersonnelCabine.ajouter(pc);
        assertTrue(PersonnelCabine.supprimer("EMP002"));
        assertEquals(0, PersonnelCabine.getListePersonnelCabine().size());
        assertFalse(PersonnelCabine.supprimer("EMP002"));
    }

    @Test
    public void testHeritage() {
        PersonnelCabine pc = creerPersonnel();
        assertTrue(pc instanceof Employe);
        assertTrue(pc instanceof Personne);
    }
}

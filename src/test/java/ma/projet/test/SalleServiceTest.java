package ma.projet.test;

import ma.projet.entities.Salle;
import org.junit.*;
import ma.projet.services.SalleService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SalleServiceTest {

    private SalleService salleService;
    private Salle salle;

    @Before
    public void setUp() {
        salleService = new SalleService();
        salle = new Salle();
        salle.setCode("A101");
        salleService.create(salle);
    }

    @After
public void tearDown(){
    Salle foundSalle = salleService.findById(salle.getId());
      if (foundSalle !=null) {
          salleService.delete(foundSalle);
      }
      }
    @Test
    public void testCreate() {
        assertNotNull("La salle devrait avoir été créée avec un ID",
     ma.projet.entities.Salle.getId());
    }
    @org.junit.Test
    public void testFindById() {
        Salle foundSalle = salleService.findById(salle.getId());
        assertNotNull("La salle n'a pas été trouvée", foundSalle);
        assertEquals("Les codes des salles ne correspondent pas", salle.getCode(), foundSalle.getCode());
    }

    @org.junit.Test
    public void testUpdate() {
        salle.setCode("B202"); // Modifiez le code pour tester la mise à jour
        boolean result = salleService.update(salle);
        assertTrue("Salle should be updated successfully", result);

        Salle updatedSalle = salleService.findById(salle.getId());
        assertEquals("Updated salle code should match", "B202", updatedSalle.getCode());
    }

    @org.junit.Test
    public void testDelete() {
        boolean result = salleService.delete(salle);
        assertTrue("Salle should be deleted successfully", result);

        Salle foundSalle = salleService.findById(salle.getId());
        assertNull("Salle should not be found after deletion", foundSalle);
    }

    @org.junit.Test
    public void testFindAll() {
        List<Salle> salle = salleService.findAll();
        assertNotNull("Salles list should not be null", salle);
        assertTrue("Salles list should contain at least one salle", salle.size() > 0);
    }
}


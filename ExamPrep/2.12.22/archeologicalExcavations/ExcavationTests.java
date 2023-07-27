package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExcavationTests {
    private Archaeologist ivan;
    private Archaeologist gosho;
    private Excavation excavation;


    @Before
    public void setup() {
        ivan = new Archaeologist("Ivan", 15);
        gosho = new Archaeologist("Gosho", 15);
        excavation = new Excavation("Pirin", 20);
    }

    @Test
    public void testExcavationCreate() {
        Assert.assertEquals("Pirin", excavation.getName());
        Assert.assertEquals(20, excavation.getCapacity());
        Assert.assertEquals(0, excavation.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistThrowsExceptionWhenCapacityEqualsArchaeologistSize() {
        Excavation excavation2 = new Excavation("Vihren", 1);
        excavation2.addArchaeologist(ivan);
        excavation2.addArchaeologist(gosho);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExcavationThrowsExceptionWhenNegativeCapacity() {
        excavation = new Excavation("Pirin", -1);
    }

    @Test(expected = NullPointerException.class)
    public void testExcavationThrowsExceptionWhenNameNull() {
        excavation = new Excavation(null, 0);
    }

    @Test(expected = NullPointerException.class)
    public void testExcavationThrowsExceptionWhenNameEmpty() {
        excavation = new Excavation(" ", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistThrowsExceptionWhenArchaeologistNameExist() {
        excavation.addArchaeologist(ivan);
        excavation.addArchaeologist(ivan);
    }

    @Test
    public void testAddArchaeologistWhenArchaeologistDoesNotExist() {
        excavation.addArchaeologist(ivan);
        //
        Assert.assertEquals(1, excavation.getCount());
    }

    @Test
    public void testRemoveArchaeologistWhenExist() {
        excavation.addArchaeologist(ivan);
        Assert.assertTrue(excavation.removeArchaeologist(ivan.getName()));
        Assert.assertEquals(0, excavation.getCount());
    }

    @Test
    public void testRemoveArchaeologistWhenDoesNotExist() {
        Assert.assertFalse(excavation.removeArchaeologist("Missing Name"));
    }

}

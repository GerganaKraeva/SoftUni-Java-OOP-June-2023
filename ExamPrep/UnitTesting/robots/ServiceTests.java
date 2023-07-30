package robots;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;
import java.util.regex.Pattern;

public class ServiceTests {
    private Service service;
    private Robot maia;
    private Robot robi;

    @Before
    public void setup() {
        service = new Service("Zora", 20);
        maia = new Robot("Maia");
        robi = new Robot("Robi");
    }

    @Test
    public void testConstructorServiceCreateNewService() {
        Assert.assertEquals("Zora", service.getName());
        Assert.assertEquals(20, service.getCapacity());
    }

    @Test(expected = NullPointerException.class)
    public void testWhenNameNullThrowsException() {
        Service service1 = new Service(null, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenCapacityNegativeThrowsException() {
        Service service1 = new Service("Zora", -1);
    }

    @Test
    public void testAddReturnsCorrectCount() {
        service.add(maia);
        Assert.assertEquals(1, service.getCount());
        service.add(robi);
        Assert.assertEquals(2, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWhenNoMoreCapacity() {
        Service service1 = new Service("Zora", 1);
        service1.add(maia);
        service1.add(robi);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWhenServiceIsEmptyThrowsException() {
        service.remove("Maia");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWhenServiceNoSuchRobotThrowsException() {
        Robot noSuchRobot = new Robot("NoSuchRobot");
        service.add(maia);
        service.add(robi);
        service.remove(noSuchRobot.getName());
    }

    @Test
    public void testRemoveExistingRobot() {
        service.add(maia);
        service.add(robi);
        service.remove("Robi");
    }

    @Test
    public void testRobotForSale() {
        service.add(maia);
        Assert.assertTrue(maia.isReadyForSale());
        service.remove(maia.getName());
        robi.setReadyForSale(false);
        service.add(robi);
        Assert.assertFalse(service.forSale(robi.getName()).isReadyForSale());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRobotForSaleThrowsExceptionWhenNoExist() {
        Assert.assertFalse(service.forSale(maia.getName()).isReadyForSale());
    }

    @Test
    public void testReport() {
        service.add(maia);
        String expectedResult = String.format("The robot %s is in the service %s!", maia.getName(), service.getName());
        String result = service.report();

        Assert.assertEquals(expectedResult,result);
    }

}

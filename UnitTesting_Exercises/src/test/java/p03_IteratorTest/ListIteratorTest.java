package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTest {
    private ListIterator listIterator;
    private static final String[] NAMES = {"Pesho", "Gosho", "Tosho"};

    @Before
    public void prepare() throws OperationNotSupportedException {
        listIterator = new ListIterator(NAMES);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsExceptionIfNull() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void testConstructorShouldCreate() throws OperationNotSupportedException {
        new ListIterator(NAMES);
        Assert.assertEquals(NAMES[0],listIterator.print());
        listIterator.move();
        Assert.assertEquals(NAMES[1],listIterator.print());
        listIterator.move();
        Assert.assertEquals(NAMES[2],listIterator.print());
    }

    @Test
    public void testHasNext() {
        Assert.assertTrue(listIterator.hasNext());
        listIterator.move();
        Assert.assertTrue(listIterator.hasNext());
        listIterator.move();
        Assert.assertFalse(listIterator.hasNext());
    }

    @Test
    public void testMove() {
        Assert.assertTrue(listIterator.move());
        Assert.assertTrue(listIterator.move());
        Assert.assertFalse(listIterator.move());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testPrintShouldThrowWithEmptyIterator() throws OperationNotSupportedException {
        ListIterator emptyIterator = new ListIterator(null);
        emptyIterator.print();
    }

    @Test
    public void testPrintShouldReturnFirstPerson() {
        Assert.assertEquals(NAMES[0], listIterator.print());
        listIterator.move();
        Assert.assertEquals(NAMES[1], listIterator.print());
    }
}
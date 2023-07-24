package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private static final Integer[] NUMBERS = {1, 5, 8, 9};

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }
//Дали създава правилен обект
    @Test
    public void testConstructorCreatesCorrectObjects() {
        Assert.assertArrayEquals("Arrays are not the same!", NUMBERS, database.getElements());
        Assert.assertEquals("Count of element is incorrect!", NUMBERS.length, database.getElements().length);
        Assert.assertEquals("Index is incorrect!", NUMBERS.length - 1, database.getIndex());
    }

    //дали създаваме обект с повече от 16 ел.
    @Test(expected = OperationNotSupportedException.class)
    public void testAddElementsAreMoreThan16() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[17];
        new Database(numbers);
    }

    //Ako създаваме Array с по-малко от 1 елемент
    @Test(expected = OperationNotSupportedException.class)
    public void testEmptyArrayThrowsException() throws OperationNotSupportedException {
        Integer[] emptyArr = new Integer[0];
        new Database(emptyArr);
    }

    //успешно добавяне на елемент
    @Test
    public void testAddShouldAddElement() throws OperationNotSupportedException {
        Integer[] elementsABefore = database.getElements();
        database.add(15);
        Integer[] elementsAfter = database.getElements();
        Assert.assertEquals(Integer.valueOf(15), elementsAfter[elementsAfter.length - 1]);
        Assert.assertEquals(elementsABefore.length + 1, elementsAfter.length);
    }

    //Ако добавяме NULL
    @Test(expected = OperationNotSupportedException.class)
    public void testAddNullThrowsException() throws OperationNotSupportedException {
        database.add(null);
    }

    //дали успешно премахваме елемент
    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        Integer [] elementsBefore= database.getElements();
        database.remove();
        Integer [] elementsAfter= database.getElements();
        Assert.assertEquals(elementsBefore.length-1,elementsAfter.length);
        Assert.assertEquals(elementsBefore[elementsBefore.length-2],elementsAfter[elementsAfter.length-1]);

}

    //дава ли грешка при премахване на несъществъващ
    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowsExceptionInvalidIndex() throws OperationNotSupportedException {
    for (int i = 0; i < NUMBERS.length; i++) {
        database.remove();
    }
    database.remove();
    }
}
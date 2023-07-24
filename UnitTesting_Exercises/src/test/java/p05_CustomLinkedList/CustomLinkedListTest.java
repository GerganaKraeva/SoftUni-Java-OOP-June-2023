package p05_CustomLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {
    private CustomLinkedList<String> list;

    @Before
    public void prepare() {
        list = new CustomLinkedList<>();
        list.add("Pesho");
        list.add("Gosho");
        list.add("Tosho");
        list.add("Tosho");
    }

    @Test
    public void testAddShouldAdd() {
        int previousSize = list.getCount();
        list.add("Andrei");
        int currentSize = list.getCount();
        Assert.assertEquals(previousSize + 1, currentSize);
        Assert.assertEquals(list.getCount() - 1, list.indexOf("Andrei"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowsWithNegativeIndex() {
        list.get(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowsWithBiggerIndex() {
        list.get(list.getCount() + 1);
    }

    @Test
    public void testGetShouldGet() {
        Assert.assertEquals("Gosho", list.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowExceptionWhenBiggerIndex() {
        list.set(list.getCount() + 1, "Ani");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowExceptionWhenNegativeIndex() {
        list.set(-2, "Ani");
    }

    @Test
    public void testSetShouldSet() {
        list.set(1, "Mimi");
        Assert.assertEquals("Mimi", list.get(1));
    }

    @Test
    public void testIndexOfShouldFindIndex() {
        assertEquals(1, list.indexOf("Gosho"));
    }

    @Test
    public void testIndexOfShouldNotFindIndex() {
        assertEquals(-1, list.indexOf("Geri"));
    }

    @Test
    public void testContainsShouldReturnTrue() {
        Assert.assertTrue(list.contains("Gosho"));
    }

    @Test
    public void testContainsShouldReturnFalse() {
        Assert.assertFalse(list.contains("Emi"));
    }

    @Test
    public void testRemoveWithMissingElement() {
        Assert.assertEquals(-1, list.remove("Petko"));
    }

    @Test
    public void testRemoveShouldRemoveElement() {
        int countBeforeRemove = list.getCount();
        Assert.assertEquals(1, list.remove("Gosho"));
        int countAfterRemove = list.getCount();
        Assert.assertEquals(countBeforeRemove - 1, countAfterRemove);
        Assert.assertEquals(-1, list.indexOf("Gosho"));
    }

    @Test
    public void testRemoveAtIndexShouldRemove() {
        int countBeforeRemove = list.getCount();
        Assert.assertEquals("Gosho", list.removeAt(1));
        int countAfterRemove = list.getCount();
        Assert.assertEquals(countBeforeRemove - 1, countAfterRemove);
    }

}
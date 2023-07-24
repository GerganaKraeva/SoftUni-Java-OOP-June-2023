package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

public class BubbleTest {

    @Test
    public void testIsCorrectSortedBubbleSort(){
        int [] array=new int[]{1,7,19,8,4,3,22,11};
        Bubble.sort(array);
        int [] expectedArray=new int[]{1,3,4,7,8,11,19,22};
        Assert.assertArrayEquals(array,expectedArray);
    }

}
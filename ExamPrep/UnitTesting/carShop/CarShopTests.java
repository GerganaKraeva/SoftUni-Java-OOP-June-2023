package carShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarShopTests {
     private CarShop carShop;
     private Car audi;
     private Car golf;
     private static final String AUDI = "Audi";
     private static final String GOLF = "Golf";
     private static final int HORSEPOWER = 250;
     private static final int HORSEPOWER_GOLF = 200;
     private static final double AUDI_PRICE = 30000;
     private static final double GOLF_PRICE = 20000;

     @Before
     public void setup() {
          this.carShop = new CarShop();
          this.audi = new Car(AUDI, HORSEPOWER, AUDI_PRICE);
          this.golf = new Car(GOLF, HORSEPOWER_GOLF, GOLF_PRICE);
     }


     @Test(expected = UnsupportedOperationException.class)
     public void testGetCarsReturnsUnmodifiableListWhenNotEmpty() {
         carShop.add(audi);
          List<Car>cars=carShop.getCars();
          cars.remove(0);
     }
     @Test
     public void testFindAllCarsWithMaxHorsePowerWhenIsEmpty(){
          List<Car>expected=new ArrayList<>();
          List<Car> cars=carShop.findAllCarsWithMaxHorsePower(100);
          Assert.assertEquals(expected,cars);
     }
     @Test
     public void testFindAllCarsWithMaxHorsePower(){
          carShop.add(audi);
          carShop.add(golf);
          List<Car> cars=carShop.findAllCarsWithMaxHorsePower(210);
          Assert.assertEquals(1,cars.size());
          Assert.assertEquals("Audi", cars.get(0).getModel());
     }
     @Test
     public void testGetCountShopCar() {
          Assert.assertEquals(0,carShop.getCount());
     }
     @Test(expected = NullPointerException.class)
     public void testAddCarToCarShopThrowsExceptionWhenCarIsNull() {
          carShop.add(null);
          Car newCar=new Car(null,HORSEPOWER,20000);
          carShop.add(newCar);
     }
     @Test
     public void testAddCarInCarShop() {
          carShop.add(audi);
     }
     @Test
     public void testRemoveWhenCarShopEmptyReturnsFalseWhenNoSuchCar() {
          Assert.assertFalse( carShop.remove(audi));
          carShop.add(audi);
          carShop.remove(golf);
     }
     @Test
     public void testRemoveExistingCar() {
          carShop.add(audi);
          Assert.assertEquals(1,carShop.getCount());
          carShop.remove(audi);
          Assert.assertEquals(0,carShop.getCount());
     }
     @Test
     public void testTheMostLuxuryCarReturnsNullWhenEmpty() {
         Assert.assertEquals(null, carShop.getTheMostLuxuryCar());
     }
     @Test
     public void testTheMostLuxuryCarReturns() {
          carShop.add(audi);
          carShop.add(golf);
          Assert.assertEquals(audi, carShop.getTheMostLuxuryCar());
     }
     @Test
     public void testFindAllCarsByModel() {
          carShop.add(audi);
          carShop.add(audi);
          carShop.add(golf);
          Assert.assertEquals(2,carShop.findAllCarByModel(audi.getModel()).size());

     }
}


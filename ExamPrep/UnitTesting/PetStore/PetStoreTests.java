package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.instrument.UnmodifiableClassException;
import java.util.List;

public class PetStoreTests {

    private PetStore petStore;
    private Animal dog;
    private Animal cat;
    private Animal rabbit;

    @Before
    public void setUp() {
        this.petStore = new PetStore();
        this.dog = new Animal("dog", 7, 125);
        this.cat = new Animal("cat", 3, 125);
        this.rabbit = new Animal("rabbit", 2, 50);
    }

    @Test
    public void testConstructorPetStoreCreateCorrect() {
        Assert.assertEquals(0, petStore.getCount());
        petStore.addAnimal(dog);
        Assert.assertEquals(1, petStore.getCount());
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testFindGetAnimalsReturnUnmodifiableList() {
        List<Animal> animals = petStore.getAnimals();
        animals.remove(1);
    }

    @Test
    public void testFindAnimalsWithMaxKgThrowsExceptionWhenEmpty() {
        petStore.addAnimal(dog);
        List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(10);
        Assert.assertTrue(animals.isEmpty());
    }

    @Test
    public void testFindAnimalsWithMaxKg() {
        petStore.addAnimal(dog);
        petStore.addAnimal(cat);
        petStore.addAnimal(rabbit);
        List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(4);
        Assert.assertEquals(1, animals.size());
        Assert.assertEquals(dog.getSpecie(), animals.get(0).getSpecie());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalThrowsExceptionWhenAnimalNull() {
        petStore.addAnimal(null);
    }

    @Test
    public void testAddAnimalShouldIncreaseCount() {
        petStore.addAnimal(cat);
        Assert.assertEquals(1, petStore.getCount());

    }
    @Test
    public void testGetMostExpensiveAnimalsThrowsExceptionWhenEmpty() {
       Animal animal = petStore.getTheMostExpensiveAnimal();
        Assert.assertEquals(null,animal);
    }
    @Test
    public void testGetMostExpensiveAnimals() {
        petStore.addAnimal(cat);
        petStore.addAnimal(dog);
        petStore.addAnimal(rabbit);
        Animal animals = petStore.getTheMostExpensiveAnimal();
        Assert.assertEquals(cat,animals);
    }
    @Test
    public void testFindAnimalsBySpecieNullWhenNoSuchSpecie() {
        List<Animal>animals=petStore.findAllAnimalBySpecie("dog");
        Assert.assertTrue(animals.isEmpty());
    }
    @Test
    public void testFindAnimalsBySpecie() {
        petStore.addAnimal(rabbit);
        petStore.addAnimal(cat);
        petStore.addAnimal(cat);
        List<Animal>animals=petStore.findAllAnimalBySpecie("cat");
        Assert.assertEquals(2,animals.size());
    }
}


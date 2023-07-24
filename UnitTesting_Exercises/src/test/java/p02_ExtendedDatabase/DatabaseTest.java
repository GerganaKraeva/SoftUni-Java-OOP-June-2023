package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;
    private static final Person gosho=new Person(1,"Gosho");
    private static final  Person mimi=new Person(2, "Mimi");
    private static final    Person iva=new Person(3,"Iva");
    private static final Person[] PEOPLE=new Person[]{gosho,mimi,iva};

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database =new Database(gosho,mimi,iva);
    }
    //Дали създава правилен обект
    @Test
    public void testConstructorCreatesCorrectObjects() {
        Assert.assertArrayEquals("Arrays are not the same!", PEOPLE, database.getElements());
        Assert.assertEquals("Count of element is incorrect!",PEOPLE.length, database.getElementsCount());
        Assert.assertEquals("Index is incorrect!", PEOPLE.length - 1, database.getIndex());
    }

    //дали създаваме обект с повече от 16 ел.
    @Test(expected = OperationNotSupportedException.class)
    public void testAddElementsAreMoreThan16() throws OperationNotSupportedException {
        Person [] people = new Person[17];
        new Database(people);
    }

    //Ako създаваме Array с по-малко от 1 елемент
    @Test(expected = OperationNotSupportedException.class)
    public void testEmptyArrayThrowsException() throws OperationNotSupportedException {
        Person[] people = new Person[0];
        new Database(people);
    }

    //успешно добавяне на елемент
    @Test
    public void testAddShouldAddElement() throws OperationNotSupportedException {
       Person [] peopleBefore = database.getElements();
        database.add(new Person(4,"Ivan"));
        Person [] peopleAfter = database.getElements();
        Person lastPerson=peopleAfter[peopleAfter.length-1];
        Assert.assertEquals(peopleBefore.length+1, peopleAfter.length);
        Assert.assertEquals(lastPerson.getId(), 4);
        Assert.assertEquals(lastPerson.getUsername(), "Ivan");
    }

    //Ако добавяме NULL
    @Test(expected = OperationNotSupportedException.class)
    public void testAddNullThrowsException() throws OperationNotSupportedException {
        database.add(null);
    }

    //дали успешно премахваме елемент
    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        Person [] peopleBefore= database.getElements();
        database.remove();
        Person [] peopleAfter= database.getElements();
        Assert.assertEquals(peopleBefore.length-1,peopleAfter.length);
        Person currentLast=peopleAfter[peopleAfter.length-1];
        Assert.assertEquals("Mimi",currentLast.getUsername(),"Mimi");
        Assert.assertEquals(2,currentLast.getId());

    }

    //дава ли грешка при премахване на несъществъващ
    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowsExceptionInvalidIndex() throws OperationNotSupportedException {
        for (int i = 0; i < PEOPLE.length; i++) {
            database.remove();
        }
        database.remove();
    }
    //тестове за username
    // ако username е null
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByNameThrowsExceptionWhenNameNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }
    //ако username не съществува
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameTrowsExceptionIfMissing() throws OperationNotSupportedException {
        database.findByUsername("Pepi");
    }
    @Test
    //ако съществува такъв username
    public void  testFindByUsernameReturnUser() throws OperationNotSupportedException {
        Person person=database.findByUsername(iva.getUsername());
    Assert.assertEquals("Invalid id of the taken person!",person.getId(),iva.getId());
    Assert.assertEquals("Invalid username of the taken person!",person.getUsername(),iva.getUsername());
    }
    //тестове за id
    //няма човек с такова ид
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdThrowsExceptionWhenNotFoundId() throws OperationNotSupportedException {
        database.findById(20);
    }
    //подаваме валидно ид
    @Test
    public void  testFindByIdReturnUser() throws OperationNotSupportedException {
        Person person=database.findById(iva.getId());
        Assert.assertEquals("Invalid id of the taken person!",person.getId(),iva.getId());
        Assert.assertEquals("Invalid username of the taken person!",person.getUsername(),iva.getUsername());
    }
    //има повече от 1 човек с такова ид
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameMoreThanPerson() throws OperationNotSupportedException {
        database.add(new Person(4, "Iva"));
        database.findByUsername("Iva");
    }



}
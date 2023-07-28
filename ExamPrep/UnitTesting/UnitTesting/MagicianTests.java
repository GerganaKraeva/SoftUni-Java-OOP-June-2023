package magicGame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MagicianTests {
    private Magic magic;
    private Magician magician;

    @Before
    public void setup() {
        magic = new Magic("Black Magic", 100);
        magician = new Magician("Wizard", 200);
    }

    @Test
    public void testConstructorCreateMagician() {
        Assert.assertEquals("Wizard", magician.getUsername());
        Assert.assertEquals(200, magician.getHealth());
        Assert.assertEquals(0, magician.getMagics().size());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWhenMagicianNameNullThrowsException() {
        Magician nullName = new Magician(null, 100);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWhenMagicianNameEmptyThrowsException() {
        Magician emptyName = new Magician(" ", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMagicianThrowsExceptionWhenHealthNegative() {
        Magician negativeHealth = new Magician("Wizard", -20);
    }

    @Test(expected = IllegalStateException.class)
    public void testIfMagicianIsDeadBeforeTakeDamage() {
        Magician zeroHealth=new Magician("Wizard",0);
        zeroHealth.takeDamage(200);
    }

    @Test
    public void testTakeDamageWhenMagicianIsDead() {
        magician.takeDamage(200);
        Assert.assertEquals(0, magician.getHealth());
    }

    @Test
    public void testTakeDamage() {
        magician.takeDamage(120);
        Assert.assertEquals(80, magician.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testAddMagicThrowsExceptionWhenNull() {
        magician.addMagic(null);
    }
    @Test
    public void testAddMagicToMagician() {
        magician.addMagic(magic);
    }
    @Test
    public void testRemoveMagic() {
        Assert.assertFalse(magician.removeMagic(magic));
        magician.addMagic(magic);
        Assert.assertTrue(magician.removeMagic(magic));
    }
    @Test
    public void testGetMagicWhenExist() {
        Assert.assertEquals(null,magician.getMagic("noSuchMagic"));
        Assert.assertEquals("Black Magic", magic.getName());
    }
}

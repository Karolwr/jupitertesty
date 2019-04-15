package pl.sda.testowe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;

//rozrzesza bilioteki @runwith
@RunWith(PowerMockRunner.class)
public class PowerMockPersonTest {
    private String name = "Adam Nowak";
    private Person person;

    @Before
    public void setUp() {
        person = new Person(name);
    }

    @Test
    public void constructorShouldSetName() {
        Assert.assertEquals("Konstruktor powinien ustawic Jan Kowalski" + name, name, person.getName());
    }

    @Test
    public void testHowMuchEarn() throws Exception {
        int result = Whitebox.invokeMethod(person, "howMuchEarn");

        assertEquals(500, result);
    }
}
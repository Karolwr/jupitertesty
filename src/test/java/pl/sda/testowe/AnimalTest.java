package pl.sda.testowe;

import org.junit.jupiter.api.Test;
import pl.sda.testowe.exceptions.TooManyTimesHungryException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalTest {

    Animal animal = mock(Animal.class);

    //zasymulowalimy tylko w tym tescie ze jezeli
    @Test
    void testName() {

//symuluje
        when(animal.getName()).thenReturn("Pimpuś");
        assertEquals("Pimpuś", animal.getName());
    }

    @Test
    void testWeight() {
        when(animal.getWeight()).thenReturn(10);
        assertEquals(10, animal.getWeight());
    }

    @Test
    void testHunger() {
        when(animal.isHungry()).thenReturn(true);

        assertTrue(animal.isHungry());
    }

    @Test
    void testHungerException() {
        when(animal.isHungry()).thenThrow(TooManyTimesHungryException.class);

        assertThrows(TooManyTimesHungryException.class, () -> {
            animal.isHungry();
        });
    }

    @Test
    void testGoForWalk() {
        //anyString dla wszytskich Stringów
        animal.goForWalk(anyString());

        verify(animal).goForWalk(anyString());
    }
}
package pl.sda.testowe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimeNumbersTest {
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13})
    void numberShouldBePrime(int number) {
        assertTrue(PrimeNumbers.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 9, 12})
    void numberShouldNotBePrime(int number) {
        assertFalse(PrimeNumbers.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 0, 1})
    void cornerCasesShouldNotBePrime(int number) {
        assertFalse(PrimeNumbers.isPrime(number));
    }
}

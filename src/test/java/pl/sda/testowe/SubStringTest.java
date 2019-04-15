package pl.sda.testowe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SubStringTest {

    String string = "Dzban";


    @Test
    void shouldReturnFirstCharacter() {
        String result = string.substring(0, 1);

        assertEquals("D", result);
        assertThat(result)
                .isEqualTo("D")
                .isEqualToIgnoringCase("d")
                .hasSize(1);
    }

    @Test
    void shouldReturnBanWord() {
        String result = string.substring(2, 5);

        assertEquals("ban", result);
    }

    @Test
    void shouldReturnLastWord() {
        String result = string.substring(4, 5);

        assertEquals("n", result);
    }

    @Test
    void shouldNotReturnAnyCharacters() {
        String result = string.substring(0, 0);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldBeTheSame() {
        String result = string.substring(0, 5);

        assertEquals(string, result);
    }

    @Test
    void shouldBeTheSame1() {
        String result = string.substring(0, string.length());

        assertEquals(string, result);
    }

    @Test
    void shouldBeNull() {
        String stringNull = null;
//lambda w przypadku junit5
        assertThrows(NullPointerException.class, () -> {
            stringNull.substring(0, 2);
        });
        //kiedys alternatywa
        try {
            stringNull.substring(0, 2);
            fail();
        } catch (NullPointerException e) {
            System.out.println("Wpadło nam tutaj");
        }
    }

    @ParameterizedTest
    @MethodSource(value = "provideSubstrings")
    void shouldTestManyCases(String slowo, String zawiera, int indexBegin, int indexEnd) {
        String wynik = slowo.substring(indexBegin, indexEnd);

        assertEquals(zawiera, wynik);
    }

    static Stream provideSubstrings() {
        return Stream.of(
                Arguments.of("Dzban", "ban", 2, 5),
                Arguments.of("Czymadlugoscpiec", "dlugosc", 5, 12),
                Arguments.of("AustriaDzisiajPrzegra","Przegra",14,21)
        );
    }
}
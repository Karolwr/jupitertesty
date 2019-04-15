package pl.sda.testowe;

import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.Assertions;
import pl.sda.testowe.exceptions.MinusMoneyExeption;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.*;

class PersonTest {
    Person person = new Person("Jan Kowalski");
    Person spouse = new Person("Alicja Nowak");

    // Person child;
//Person lover;
//    @BeforeEach
//    void setUp() {
//        spouse = new Person("Alicja Nowak");
//    }

    @Test
    void constructorShouldSetName() {
        Assertions.assertEquals("Jan Kowalski", person.getName(),
                "Konstruktor powinien ustawić Jan Kowalski");
    }

    @Test
    void personShouldHaveChild() {
        Person child = new Person("Adam Kowalski");

        person.addChild(child);

        assertNotNull(person.getChildren());

        Assertions.assertEquals(1, person.getChildren().size());
    }

    @Test
    void personShouldHaveSpouse() {
        person.marriage(spouse);

        assertNotNull(person.getSpouse());
    }

    @Test
    void personShouldNotHaveAnotherSpouse() {
        Person lover = new Person("Beata K");

        person.marriage(spouse);
        person.marriage(lover);

        Assertions.assertEquals(spouse, person.getSpouse(),
                "Małżonką Jana Kowalskiego powinna być Alicja Nowak," +
                        "a obecną jest Beata K" + person.getSpouse().getName());
    }

    @Test
    void spouseShouldHaveSpouse() {
        person.marriage(spouse);

        Assertions.assertEquals(person, spouse.getSpouse());
    }

    @Test
    void loverShouldNotMarriageMarriedPerson() {
        Person lover = new Person("Beata K");

        person.marriage(spouse);
        lover.marriage(person);

        //powoduje ze jesli mamy wiecej testow niz jeden
        // każdy zostanie wykorzystany osobno
        assertAll(
                () -> Assertions.assertNull(lover.getSpouse(), lover.getName() +
                        "jest w zwiazku z"),
                () -> Assertions.assertEquals(spouse, person.getSpouse())
        );
    }

    //czy osoby sa po slubie
    @Test
    void personShouldNorBeMarrriedWithAnotherOne() {
        person.marriage(spouse);

        Assertions.assertFalse(person.IsMarriedWithAnotherPerson(spouse));
    }

    //czy trzecia osba nie ma nikogo i czy za kogos wyszla:)
    @Test
    void personShouldBeMarriedWithAnotherOne() {
        Person lover = new Person("Beata K");

        person.marriage(spouse);

        Assertions.assertTrue(person.IsMarriedWithAnotherPerson(lover));
    }

    @Test
    void personShouldNotHaveSpouseAfterDivorce() {
        person.marriage(spouse);
        person.divorce();
        assertNull(person.getSpouse());
    }

    @Test
    void spouseShouldNotHaveSpouseAfterDivorce() {
        person.marriage(spouse);
        person.divorce();

        assertNull(spouse.getSpouse());
    }

    @Test
    void personShouldEarnSomeMoey() {
        person.earn(1000);

        Assertions.assertEquals(1000, person.getMoney());
    }

    @Test
    void personShouldEarnMuchMoney() {
        person.earn(1000);
        person.earn(500);

        Assertions.assertEquals(1500, person.getMoney());
    }

    @Test
    void personShouldNotEarnMinusMoney() {
        Exception exception = assertThrows(MinusMoneyExeption.class, () -> {
            person.earn(-100);
        });
        assertEquals("No minus money", exception.getMessage());
    }

    @Test
    void personShouldEarnMoneyAfterWork() {
        //zakonczyc po 1 sekundzie assertTimeoutPreemptively
        assertTimeout(Duration.ofMillis(1000), () -> {// o ile za dlugo
            person.work(100, 300);//pracuje 100ms dostaje cask 300
        });

        Assertions.assertEquals(300, person.getMoney());
    }

    @Test
    void testSomethingWithAssume() {
        System.setProperty("ENV", "PROD");

        assumeTrue(System.getProperty("ENV").equals("PROD"));

        assertTrue(true);
    }

    //    @Test
//    void emailShouldBeValid1(){
//        person.setEmail("jan.kowalski@szkolenia.pl");
//
//        assertTrue(person.isEmailValid());
//    }
//    @Test
//    void emailShouldBeValid2(){
//        person.setEmail("jan.kowalski@szkolenia.pl");
//
//        assertTrue(person.isEmailValid());
//    }
//    @Test
//    void emailShouldBeNotValid(){
//        //co jest
//        person.setEmail("@szkolenia.pl");
////co powinno byc
//        assertFalse(person.isEmailValid());
//    }
    @ParameterizedTest
    @ValueSource(strings = {"jan.kowalski@szkolenia.pl", "j@a.pl", " @ "})
//:)
    void emailsShouldBeValid(String email) {
        person.setEmail(email);

        Assertions.assertTrue(person.isEmailValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@szkolenia.pl", "ja.pl", " @"})
//:)
    void emailsShouldNotBeValid(String email) {
        person.setEmail(email);

        Assertions.assertFalse(person.isEmailValid());
    }

    // wtej metodzie fabrykujacej mamy trzy testy ktore powinny byc prawidlowe
    @ParameterizedTest
    @MethodSource(value = "provideValidEmails")
// nazwe naszej metody ponizej
    void emailsShouldByValidByMethodSource(String email) {//argument string email
        person.setEmail(email);

        Assertions.assertTrue(person.isEmailValid());
    }

    static Stream provideValidEmails() {
        return Stream.of("jan.kowalski@szkolenia.pl", "j@a.pl", " @ ");
    }

    @ParameterizedTest
    @MethodSource(value = "provideEmails")
    void checkEmailCorrections(String email, boolean expectedValidation) {
        person.setEmail(email);

        Assertions.assertEquals(expectedValidation, person.isEmailValid(), "Email: " + email + "should be:" + expectedValidation);
    }

    //zrwaca stream ktore dla kazdego argumentu jest wykonywany osobno test
    static Stream provideEmails() {
        return Stream.of(
                Arguments.of("jan.kowalski@szkolenia.pl", true),
                //po kropce przystko dziedziczy po obiekcie
                Arguments.of("@szkolenia.pl", false)
        );
    }

    //csvsource jest odzelanay kazdy wiesz od kolumn

    @ParameterizedTest
    @CsvSource(value = {"jan.kowalski@szkolenia.pl, true"," szkolenia.pl, false"})
    void checkEmailsCorrectionByCscSource(String email, boolean expectedValidation){
        person.setEmail(email);

        Assertions.assertEquals(expectedValidation, person.isEmailValid());
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/emails.csv")
    void checkEmailsCorrectionFromFile (String email, boolean exeptedValidation){
        person.setEmail(email);

        Assertions.assertEquals(exeptedValidation, person.isEmailValid());
    }
    @Test
    void testTwoArgumentsConstructor (){
        Person newPerson = new Person("Jan Kowalski", 18);

        Java6Assertions.assertThat(newPerson.getName())
                .isEqualTo("Jan Kowalski")
                .isEqualToIgnoringCase("Jan Kowalski")
                .isNotNull();

        Java6Assertions.assertThat(newPerson.getAge())
                .isEqualTo(18)
                .isLessThan(20)
                .isGreaterThan(10)
                .isBetween(0,100);
    }
    //mokito
    @Test
    void personShouldGoForWalkWithAnimal (){
        Animal animalMock = mock(Animal.class);
        long time= 100;
        int cash=40;
        String place ="PARK";

        //act/when
        person.workForWalk(time,cash, animalMock,place);

        //assert/then
        verify(animalMock).goForWalk("PARK");
        Assertions.assertEquals(40, person.getMoney());
    }
    @Test
    void spouseCheatingOnPersonn (){
        //arrange/given
        Person newSpouseMock=mock(Person.class);
        //act/when
        person.marriage(newSpouseMock);
//        when(newSpouseMock.IsMarriedWithAnotherPerson(person)).thenReturn(true);
        //asert/then
        verify(newSpouseMock).marriage(person);
        Assertions.assertFalse(newSpouseMock.IsMarriedWithAnotherPerson(person));
    }
}
package pl.sda.testowe.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.testowe.Person;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserManagerTest {

    String email = "jan@szkolenie.pl";
//    Datebase datebaseMock = mock(Datebase.class);
//    Network networkMock = mock(Network.class);
//    Person userMock = mock(Person.class);
//    UserManager userManager = new UserManager(datebaseMock, networkMock);

    @Mock
    Datebase datebaseMock;
    @Mock
    Network networkMock;
    @Mock
    Person userMock;
    @InjectMocks
    UserManager userManager;

    @Test
    void loginSucces() {
        when(userMock.getEmail()).thenReturn(email);

        boolean result = userManager.login(userMock);

        assertTrue(result);
        Assertions.assertEquals(userMock, userManager.getUser());
        Assertions.assertEquals(email, userMock.getEmail());
        verify(datebaseMock).save(userMock);
        verify(networkMock).upload(userMock);
    }

    @Test
    void loginFailure() {
        Person user = null;
        boolean result = userManager.login(user);
        assertFalse(result);
        verify(datebaseMock, never()).save(user);
        verify(networkMock, never()).upload(user);
    }
}
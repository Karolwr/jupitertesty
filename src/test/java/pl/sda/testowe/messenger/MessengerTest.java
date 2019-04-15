package pl.sda.testowe.messenger;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MessengerTest {
    @Test
    void ShouldSendMessage() {
        String message = "Cześć,  co tam u was?";
        String email = "jan.kowalski@szkolenie.pl";
        //dummy
        Template templateMock = mock(Template.class);
        //stub
        TemplateEngine templateEngineMock = mock(TemplateEngine.class);
        //mock(spy) tylko i wylacznie spy
        MailServer mailServerMock = mock(MailServer.class);
        //stub
        Client clientMock = mock(Client.class);

        Messenger messenger = new Messenger(mailServerMock, templateEngineMock);
//symulujemy zachowanbia
        //tempate cos robil:))
        //client cos robil wiec when
        when(templateEngineMock.prepareMessage(templateMock, clientMock)).thenReturn(message);
        //oczekujemy ze cos nam zwroci
        when(clientMock.getEmail()).thenReturn(email);//zwraca email
//obiekt jko testyujemy  jako sentmessage
        messenger.sendMessage(clientMock, templateMock);
        //weryfikacja
        verify(mailServerMock).send(email, message);//oczekujemy ze metody sendserver dojdzie wiadomosc z serwera i dosjdzie wiadomosc z templateengine
    }

}
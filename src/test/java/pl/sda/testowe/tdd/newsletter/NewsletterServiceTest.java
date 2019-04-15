package pl.sda.testowe.tdd.newsletter;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class NewsletterServiceTest {
    NewsletterService newsletterService = new NewsletterService();
    Client clientAMock = mock(Client.class, "ClientA");
    Client clientBMock = mock(Client.class, "ClientB");
    Message messageMock = mock(Message.class);

    @Test
    void subscibendClientShouldReceiveMesage() {
        newsletterService.addSubsciber(clientAMock);
        newsletterService.send(messageMock);

        verify(clientAMock).receiveMessage(messageMock);
    }

    @Test
    void messageShouldBeSendToManySubscibres() {
        //aranzacja
//cos sie dzieje:)
        newsletterService.addSubsciber(clientAMock);
        newsletterService.addSubsciber(clientBMock);
        newsletterService.send(messageMock);
//wweryfikacja
        verify(clientAMock).receiveMessage(messageMock);
        verify(clientBMock).receiveMessage(messageMock);
    }

    @Test
    void notSubscibedClientShouldNotReceiveMessage() {
        newsletterService.send(messageMock);

        verify(clientAMock, never()).receiveMessage(messageMock);
        verify(clientBMock, never()).receiveMessage(messageMock);
    }

    @Test
    void shouldSendOnlyOneMessageToManySubscibeSlients() {
        newsletterService.addSubsciber(clientAMock);
        newsletterService.addSubsciber(clientAMock);

        newsletterService.send(messageMock);

        verify(clientAMock, times(1)).receiveMessage(messageMock);
    }

    @Test
    void unsubcribedClientShouldNotReciceMessage() {
        newsletterService.addSubsciber(clientAMock);
        newsletterService.removeSubscber(clientAMock);

        newsletterService.send(messageMock);

        verify(clientAMock, never()).receiveMessage(messageMock);
    }
}
//zadanie regex przy da sie
//oraz split ze stringa
//string.split
//regex = ",|\n";

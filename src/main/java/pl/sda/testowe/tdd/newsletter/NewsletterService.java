package pl.sda.testowe.tdd.newsletter;

import java.util.HashSet;
import java.util.Set;

public class NewsletterService {
    private Set<Client> clients = new HashSet<>();

    public void addSubsciber(Client client) {
        clients.add(client);
    }

    public void send(Message message) {
        for (Client client : clients) {
            client.receiveMessage(message);
        }
    }

    public void removeSubscber(Client client) {
        clients.remove(client);
    }

}

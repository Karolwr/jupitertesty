package pl.sda.testowe.messenger;

public class Messenger {
    private MailServer mailServer;
    private TemplateEngine templateEngine;

    public Messenger(MailServer mailServer, TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }

    public void sendMessage(Client client, Template template) {
        String message = templateEngine.prepareMessage(template, client);
        String email = client.getEmail();
        //przechodzimy do serwera maili wysylamy wiadomosc i pobieramy wiadomosc od klienta
        mailServer.send(client.getEmail(), message);

    }
}
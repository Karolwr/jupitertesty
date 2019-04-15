package pl.sda.testowe.manager;

import pl.sda.testowe.Person;

public class UserManager {
    private Person user;//tworzymy zmienna lokalna user
    private Datebase datebase;
    private Network network;

    public UserManager(Datebase datebase, Network network) {
        this.datebase = datebase;
        this.network = network;
    }

    public boolean login(Person user) {
        if (user == null) {
            return false;
        }
        network.upload(user);
        datebase.save(user);

        this.user = user;
        return true;
    }

    public Person getUser() {
        return this.user;
    }
}

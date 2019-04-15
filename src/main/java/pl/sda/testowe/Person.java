package pl.sda.testowe;

import pl.sda.testowe.exceptions.MinusMoneyExeption;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Person {
    private String name;

    private Person spouse;

    private List<Person> children = new ArrayList<>();

    private int money;

    private String email;

    private int age;

    //sprawdzanie meaila
    //daszek sprawdza co bedzie
    //pomiedzy tymi wyrazami @
    private String regex = "^(.+)@(.+)$";//wyrazenia regularne
    //worzc do sprawdznia
    Pattern pattern = Pattern.compile(regex);

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this(name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Person getSpouse() {
        return spouse;
    }

    public int getMoney() {
        return money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public void marriage(Person newPersonInMyLife) {
        if (spouse == null && !newPersonInMyLife.IsMarriedWithAnotherPerson(this)) {
            this.spouse = newPersonInMyLife;
            spouse.marriage(this);
        }
    }

    public void divorce() {
        Person ex = this.spouse;
        this.spouse = null;

        if (ex.getSpouse() != null) {
            ex.divorce();
        }
    }

    //czy ta Beata posiada kogos innego niz my sami:)
    public boolean IsMarriedWithAnotherPerson(Person personToCheck) {
        if (spouse == null) {
            return false;
        }
        if (!spouse.equals(personToCheck)) {
            return true;
        } else {
            return false;
        }
    }

    public void earn(int cash) {
        if (cash < 0) {
            throw new MinusMoneyExeption("No minus money");
        }
        //jesli wszystko jest ok to nasze money powinna byc zwiekszone o kase ktora otrz
        //     money = money + cash;
        money += cash;
    }

    public void work(long time, int cash) {
        try {
            Thread.sleep(time);
            earn(cash);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //wychodzi na spacer i dostaje kase)
    public void workForWalk(long time, int cash, Animal animal, String place) {
        animal.goForWalk(place);
        work(time, cash);
    }

    public boolean isEmailValid() {
        return pattern.matcher(email).matches();
    }

    //macher bedzie sptrawdzal czy przynajmniej jeden zack @ i ptryznajmniej jeden znak

    //PowerMock
    private int howMuchEarn() {
        return 500;
    }
}

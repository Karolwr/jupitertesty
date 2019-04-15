package pl.sda.testowe.exceptions;

public class MinusMoneyExeption extends RuntimeException {
    public MinusMoneyExeption(String message) {
        super(message);
    }
}

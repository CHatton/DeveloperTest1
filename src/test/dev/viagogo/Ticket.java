package test.dev.viagogo;

public class Ticket {
    // using int to represent price in pennies to avoid floating point
    // arithmetic errors.
    private final int price;

    public Ticket(int price) {
        if (price <= 0) { // ticket must have a non-zero value.
            throw new IllegalArgumentException("Price must be > 0. Provided value: " + price);
        }
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

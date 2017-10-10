package viagogo.events;

public class Ticket {
    // using long to represent price in pennies to avoid floating point
    // arithmetic errors.
    private final long price;

    public Ticket(long price) {
        if (price <= 0) { // ticket must have a non-zero value.
            throw new IllegalArgumentException("Price must be > 0. Provided value: " + price);
        }
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("{price=%s}", price);
    }
}

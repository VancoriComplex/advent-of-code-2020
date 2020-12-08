package day7;

import java.util.Objects;

class Bag {

    private String name;
    private int quantity;

    Bag(String name) {
        this.name = name;
    }

    Bag(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Bag;
        return ((Bag) obj).name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("%d x %s", quantity, name);
    }

    public String getName() {
        return name;
    }

    Integer getQuantity() {
        return quantity;
    }

}

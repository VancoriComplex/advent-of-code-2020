package day7;

import java.util.Collections;
import java.util.Set;

class Bag {

    private String name;
    private int quantity;
    private Set<Bag> children;

    Bag(String name) {
        this.name = name;
        children = Collections.emptySet();
    }

    Bag(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
        children = Collections.emptySet();
    }

    Bag(String name, Set<Bag> children) {
        this.name = name;
        this.children = children;
    }

    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Bag;
        return ((Bag) obj).name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return String.format("%d x %s", quantity, name);
    }

    Integer getQuantity() {
        return quantity;
    }

    Set<Bag> getChildren() {
        return children;
    }

}

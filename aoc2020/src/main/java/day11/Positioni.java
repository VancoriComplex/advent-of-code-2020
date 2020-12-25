package day11;

import java.util.Objects;

class Positioni {

    final int x;
    final int y;

    public Positioni(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Positioni positioni = (Positioni) o;
        return x == positioni.x && y == positioni.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

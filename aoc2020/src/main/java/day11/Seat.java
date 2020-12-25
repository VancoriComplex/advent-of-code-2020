package day11;

import java.util.Objects;

class Seat {

    private boolean isOccupied;
    private final Positioni positioni;

    Seat(Positioni positioni) {
        this.positioni = positioni;
    }

    boolean isOccupied() {
        return isOccupied;
    }

    void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Positioni getPositioni() {
        return positioni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(positioni, seat.positioni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positioni);
    }
}

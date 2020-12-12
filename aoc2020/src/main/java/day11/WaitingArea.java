package day11;

import java.util.Set;
import java.util.stream.Collectors;

class WaitingArea {

    private final Set<Seat> seats;

    WaitingArea(Set<AreaPosition> positions) {
        seats = filterSeats(positions);
    }

    Set<Seat> getSeats() {
        return seats;
    }

    Set<Seat> getAdjacentsOf(Seat seat, int scope) {
        return seats.stream()
                .filter(seat1 -> (Math.abs(seat.x - seat1.x) <= scope)
                        && (Math.abs(seat.y - seat1.y) <= scope)
                        && seat1 != seat)
                .collect(Collectors.toSet());
    }

    int getOccupiedSeatsCount() {
        return (int) seats.stream().filter(Seat::isOccupied).count();
    }

    private Set<Seat> filterSeats(Set<AreaPosition> positions) {
        return positions.stream()
                .filter(areaPosition -> areaPosition instanceof Seat)
                .map(areaPosition -> (Seat) areaPosition)
                .collect(Collectors.toSet());
    }
}

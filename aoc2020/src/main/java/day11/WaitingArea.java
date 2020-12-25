package day11;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class WaitingArea {

    private static final Set<int[]> seatNeighbours;

    static {
        seatNeighbours = new HashSet<>();
        seatNeighbours.add(new int[] {0, 1});
        seatNeighbours.add(new int[] {1, 1});
        seatNeighbours.add(new int[] {1, 0});
        seatNeighbours.add(new int[] {1, -1});
        seatNeighbours.add(new int[] {0, -1});
        seatNeighbours.add(new int[] {-1, -1});
        seatNeighbours.add(new int[] {-1, 0});
        seatNeighbours.add(new int[] {-1, 1});
    }

    private final Map<Positioni, Seat> seats;

    WaitingArea(Map<Positioni, Seat> seats) {
        this.seats = seats;
    }

    Map<Positioni, Seat> getSeats() {
        return seats;
    }

    int getOccupiedSeatsCount() {
        return (int) seats.values().stream().filter(Seat::isOccupied).count();
    }

    Set<Seat> getNeighboursOf(Seat seat, int scope) {
        Set<Seat> neighbours = new HashSet<>();
        for (int[] neighbour : seatNeighbours) {
            for (int s = 1; s <= scope; s++) {
                int x = seat.getPositioni().x + neighbour[0]*s;
                int y = seat.getPositioni().y + neighbour[1]*s;
                Positioni potentialNeighbour = new Positioni(x, y);
                if (seats.containsKey(potentialNeighbour))
                    neighbours.add(seats.get(potentialNeighbour));
            }
        }
        return neighbours;
    }
}

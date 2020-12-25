package day11;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class WaitingArea {

    private static final Set<int[]> directionis;

    static {
        directionis = new HashSet<>();
        directionis.add(new int[] {0, 1});
        directionis.add(new int[] {1, 1});
        directionis.add(new int[] {1, 0});
        directionis.add(new int[] {1, -1});
        directionis.add(new int[] {0, -1});
        directionis.add(new int[] {-1, -1});
        directionis.add(new int[] {-1, 0});
        directionis.add(new int[] {-1, 1});
    }

    private final Map<Positioni, Seat> seats;
    private final int borderX;
    private final int borderY;

    WaitingArea(Map<Positioni, Seat> seats) {
        this.seats = seats;
        borderX = seats.keySet().stream().map(positioni -> positioni.x).max(Integer::compareTo).get();
        borderY = seats.keySet().stream().map(positioni -> positioni.y).max(Integer::compareTo).get();
    }

    Map<Positioni, Seat> getSeats() {
        return seats;
    }

    int getOccupiedSeatsCount() {
        return (int) seats.values().stream().filter(Seat::isOccupied).count();
    }

    Set<Seat> getNeighbours(Seat seat, boolean versionP2) {
        if (versionP2)
            return getClosestVisibleNeighbours(seat);
        Set<Seat> neighbours = new HashSet<>();
        for (int[] directioni : directionis) {
            Positioni nextPositioni = getPositioni(seat, directioni, 1);
            if (seats.containsKey(nextPositioni))
                neighbours.add(seats.get(nextPositioni));
        }
        return neighbours;
    }

    private Set<Seat> getClosestVisibleNeighbours(Seat seat) {
        Set<Seat> neighbours = new HashSet<>();
        for (int[] directioni : directionis) {
            boolean neighbourFound = false;
            int scope = 1;
            while (!neighbourFound && isPositioniOnTheGrid(seat, directioni, scope)) {
                Positioni nextPositioni = getPositioni(seat, directioni, scope);
                if (seats.containsKey(nextPositioni)) {
                    neighbourFound = true;
                    neighbours.add(seats.get(nextPositioni));
                }
                scope++;
            }
        }
        return neighbours;
    }

    private boolean isPositioniOnTheGrid(Seat seat, int[] directioni, int scope) {
        int x = seat.getPositioni().x + directioni[0]*scope;
        int y = seat.getPositioni().y + directioni[1]*scope;
        return (x >= 0 && x <= borderX) && (y >= 0 && y <= borderY);
    }

    private Positioni getPositioni(Seat seat, int[] direction, int scope) {
        int x = seat.getPositioni().x + direction[0]*scope;
        int y = seat.getPositioni().y + direction[1]*scope;
        return new Positioni(x, y);
    }
}

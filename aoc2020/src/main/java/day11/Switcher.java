package day11;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

class Switcher {

    boolean switchSeats(WaitingArea waitingArea, int maxOccupied, boolean versionP2) {
        AtomicBoolean stateChanged = new AtomicBoolean(false);
        Set<Seat> seatsToSwitch = getSeatsToSwitch(waitingArea, maxOccupied, versionP2);
        seatsToSwitch.forEach(seat -> {
            seat.setOccupied(!seat.isOccupied());
            stateChanged.set(true);
        });
        return stateChanged.get();
    }

    private Set<Seat> getSeatsToSwitch(WaitingArea waitingArea, int maxOccupied, boolean versionP2) {
        Set<Seat> seatsToSwitch = new HashSet<>();
        Set<Seat> neighbours;
        for (Seat seat : waitingArea.getSeats().values()) {
            neighbours = waitingArea.getNeighbours(seat, versionP2);
            if (!seat.isOccupied()
                    && neighbours.stream()
                    .noneMatch(Seat::isOccupied))
                seatsToSwitch.add(seat);

            if (seat.isOccupied()
                    && neighbours.stream()
                    .filter(Seat::isOccupied)
                    .count() > maxOccupied)
                seatsToSwitch.add(seat);
        }
        return seatsToSwitch;
    }
}

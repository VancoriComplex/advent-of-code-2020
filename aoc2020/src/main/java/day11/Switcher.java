package day11;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Switcher {

    void switchSeats(WaitingArea waitingArea, int occupiedAdjacentsLimit) {
        Set<Seat> seatsToSwitch = new HashSet<>();
        Set<Seat> adjacents;

        for (Seat seat : waitingArea.getSeats()) {
            adjacents = waitingArea.getAdjacentsOf(seat, 1);
            if (!seat.isOccupied()
                    && adjacents
                    .stream()
                    .noneMatch(Seat::isOccupied))
                seatsToSwitch.add(seat);

            if (seat.isOccupied()
                    && adjacents
                    .stream()
                    .filter(Seat::isOccupied)
                    .count() > occupiedAdjacentsLimit)
                seatsToSwitch.add(seat);
        }

        waitingArea.getSeats()
                .stream()
                .peek(seat -> {
                    if (seatsToSwitch.contains(seat))
                        seat.switchState();
                })
                .collect(Collectors.toSet());
    }
}

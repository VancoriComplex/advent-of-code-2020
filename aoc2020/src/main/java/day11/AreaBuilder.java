package day11;

import java.util.*;

class AreaBuilder {

    WaitingArea buildWaitingArea(List<String> input) {
        Map<Positioni, Seat> seats = new HashMap<>();
        for (int x = 0; x < input.size(); x++) {
            for (int y = 0; y < input.get(x).length(); y++) {
                char label = input.get(x).charAt(y);
                if (label == 'L') {
                    Positioni seatPositioni = new Positioni(x, y);
                    seats.put(seatPositioni, new Seat(seatPositioni));
                }
            }
        }
        return new WaitingArea(seats);
    }
}

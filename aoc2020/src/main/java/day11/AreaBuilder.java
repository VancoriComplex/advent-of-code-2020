package day11;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class AreaBuilder {
    WaitingArea buildArea(List<String> input) {
        Set<AreaPosition> positions = new HashSet<>();
        for (int x = 0; x < input.size(); x++) {
            for (int y = 0; y < input.get(x).length(); y++) {
                char state = input.get(x).charAt(y);
                switch (state) {
                    case 'L':
                        positions.add(new Seat(state, x, y));
                        break;
                    case '.':
                        positions.add(new Floor(state, x, y));
                }
            }
        }
        return new WaitingArea(positions);
    }
}

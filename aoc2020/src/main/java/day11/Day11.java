package day11;

import java.util.List;

public class Day11 {

    public static int part1(List<String> input) {
        AreaBuilder builder = new AreaBuilder();
        WaitingArea waitingArea = builder.buildWaitingArea(input);
        Switcher switcher = new Switcher();
        boolean stateChanged = true;
        while (stateChanged) {
            stateChanged = switcher.switchSeats(waitingArea, 3);
        }
        return waitingArea.getOccupiedSeatsCount();
    }
}

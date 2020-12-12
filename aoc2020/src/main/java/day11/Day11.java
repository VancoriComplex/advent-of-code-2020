package day11;

import java.util.List;

public class Day11 {

    public static class Part1 {

        public static int getFinalNumOfOccupiedSeats(List<String> input) {
            AreaBuilder builder = new AreaBuilder();
            WaitingArea waitingArea = builder.buildArea(input);
            Switcher switcher = new Switcher();
            int stateBeforeSwitch = -1;
            int stateAfterSwitch = -1;
            int count = 0;

            while (seatsHaveChanged(stateBeforeSwitch, stateAfterSwitch)) {
                System.out.println("in the loop " + ++count);
                stateBeforeSwitch = waitingArea.getOccupiedSeatsCount();
                switcher.switchSeats(waitingArea, 3);
                stateAfterSwitch = waitingArea.getOccupiedSeatsCount();
            }
            return stateAfterSwitch;
        }

        private static boolean seatsHaveChanged(int stateBeforeSwitch, int stateAfterSwitch) {
            if (stateBeforeSwitch == -1 || stateAfterSwitch == -1)
                return true;
            return stateBeforeSwitch != stateAfterSwitch;
        }
    }
}

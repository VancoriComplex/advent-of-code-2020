package day12;

import java.util.List;

public class Day12 {

    public static int part1(List<String> input) {

        ShipPosition shipPosition = new ShipPosition();
        ShipComputer shipComputer = new ShipComputer();

        for (String line : input) {
            Command command = new Command(line);
            shipComputer.calculatePosition(shipPosition, command);
        }

        return shipComputer.calculateManhattanDistance(shipPosition);
    }
}

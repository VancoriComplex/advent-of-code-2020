package day12;

import java.util.List;

public class Day12 {

    public static int part1(List<String> input) {
        SeaObject ship = new Ship();
        NavigationComputer navigationComputer = new NavigationComputer();
        for (String line : input) {
            Command command = new Command(line);
            navigationComputer.navigate(ship, command);
        }
        return navigationComputer.calculateManhattanDistance(ship);
    }

    public static int part2(List<String> input) {
        SeaObject ship = new Ship();
        SeaObject waypoint = new Waypoint();
        NavigationComputer navigationComputer = new NavigationComputer();
        for (String line : input) {
            Command command = new Command(line);
            navigationComputer.navigate(ship, waypoint, command);
        }
        return navigationComputer.calculateManhattanDistance(ship);
    }
}

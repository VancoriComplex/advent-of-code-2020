package day12;

import java.util.HashMap;
import java.util.Map;

class NavigationComputer {

    private static final Map<Character, Integer> directionIndicatorsMap = new HashMap<>();

    static {
        directionIndicatorsMap.put('N', 1);
        directionIndicatorsMap.put('S', -1);
        directionIndicatorsMap.put('E', 1);
        directionIndicatorsMap.put('W', -1);
        directionIndicatorsMap.put('R', 1);
        directionIndicatorsMap.put('L', -1);
    }

    int calculateManhattanDistance(Ship ship) {
        return Math.abs(ship.getHorizontal()) + Math.abs(ship.getVertical());
    }

    void calculatePosition(Ship ship, Command command) {
        switch (command.getAction()) {
            case 'F' -> {
                ship.setHorizontal(ship.getHorizontal() + command.getValue()*ship.getDirectionHorizontal());
                ship.setVertical(ship.getVertical() + command.getValue()*ship.getDirectionVertical());
            }
            case 'N', 'S' -> ship.setVertical(ship.getVertical() + command.getValue()*directionIndicatorsMap.get(command.getAction()));
            case 'E', 'W' -> ship.setHorizontal(ship.getHorizontal() + command.getValue()*directionIndicatorsMap.get(command.getAction()));
            case 'L', 'R' -> ship.rotate(command.getValue()/90, directionIndicatorsMap.get(command.getAction()));
        }
    }

    void calculatePosition(Ship ship, Waypoint waypoint, Command command) {
        switch (command.getAction()) {
            case 'F' -> {
                ship.setHorizontal(ship.getHorizontal() + waypoint.getHorizontal()*command.getValue());
                ship.setVertical((ship.getVertical()) + waypoint.getVertical()*command.getValue());
            }
            case 'N', 'S' -> waypoint.setVertical(waypoint.getVertical() + command.getValue()*directionIndicatorsMap.get(command.getAction()));
            case 'E', 'W' -> waypoint.setHorizontal(waypoint.getHorizontal() + command.getValue()*directionIndicatorsMap.get(command.getAction()));
            case 'L', 'R' -> waypoint.rotate(command.getValue()/90, directionIndicatorsMap.get(command.getAction()));
        }
    }
}

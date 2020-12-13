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

    int calculateManhattanDistance(SeaObject ship) {
        return Math.abs(ship.getHorizontalPosition()) + Math.abs(ship.getVerticalPosition());
    }

    void navigate(SeaObject ship, Command command) {
        switch (command.getAction()) {
            case 'F' -> {
                moveVertical(ship, command.getValue(), ship.getDirectionVertical());
                moveHorizontal(ship, command.getValue(), ship.getDirectionHorizontal());
            }
            case 'N', 'S' -> moveVertical(ship, command.getValue(), directionIndicatorsMap.get(command.getAction()));
            case 'E', 'W' -> moveHorizontal(ship, command.getValue(), directionIndicatorsMap.get(command.getAction()));
            case 'L', 'R' -> rotate(ship, command);
        }
    }

    void navigate(SeaObject ship, SeaObject waypoint, Command command) {
        switch (command.getAction()) {
            case 'F' -> {
                moveVertical(ship, command.getValue(), waypoint.getDirectionVertical());
                moveHorizontal(ship, command.getValue(), waypoint.getDirectionHorizontal());
            }
            case 'N', 'S' -> moveVertical(waypoint, command.getValue(), directionIndicatorsMap.get(command.getAction()));
            case 'E', 'W' -> moveHorizontal(waypoint, command.getValue(), directionIndicatorsMap.get(command.getAction()));
            case 'L', 'R' -> rotate(waypoint, command);
        }
    }

    private void moveVertical(SeaObject seaObject, int offset, int directionValue) {
        seaObject.setVerticalPosition(seaObject.getVerticalPosition() + offset*directionValue);
    }

    private void moveHorizontal(SeaObject seaObject, int offset, int directionValue) {
        seaObject.setHorizontalPosition(seaObject.getHorizontalPosition() + offset*directionValue);
    }

    private void rotate(SeaObject seaObject, Command command) {
        seaObject.rotate(command.getValue()/90, directionIndicatorsMap.get(command.getAction()));
    }
}

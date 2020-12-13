package day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ShipComputer {

    private static final Map<Character, Integer> directionIndicatorsMap = new HashMap<>();
    private static final List<int[]> rotationList = new ArrayList<>();

    static {
        directionIndicatorsMap.put('N', 1);
        directionIndicatorsMap.put('S', -1);
        directionIndicatorsMap.put('E', 1);
        directionIndicatorsMap.put('W', -1);
        directionIndicatorsMap.put('R', 1);
        directionIndicatorsMap.put('L', -1);
        rotationList.add(new int[] {directionIndicatorsMap.get('E'), 0});
        rotationList.add(new int[] {0, directionIndicatorsMap.get('S')});
        rotationList.add(new int[] {directionIndicatorsMap.get('W'), 0});
        rotationList.add(new int[] {0, directionIndicatorsMap.get('N')});
    }

    void calculatePosition(ShipPosition shipPosition, Command command) {

        switch (command.getCode()) {
            case 'F' -> {
                shipPosition.setHorizontalPosition(shipPosition.getHorizontalPosition() + command.getValue()*shipPosition.getHorizontalDirection());
                shipPosition.setVerticalPosition(shipPosition.getVerticalPosition() + command.getValue()*shipPosition.getVerticalDirection());
            }
            case 'N', 'S' ->
                    shipPosition.setVerticalPosition(shipPosition.getVerticalPosition() + command.getValue()*directionIndicatorsMap.get(command.getCode()));
            case 'E', 'W' ->
                    shipPosition.setHorizontalPosition(shipPosition.getHorizontalPosition() + command.getValue()*directionIndicatorsMap.get(command.getCode()));
            case 'L', 'R' ->
                    rotate(shipPosition, command);
        }
    }

    int calculateManhattanDistance(ShipPosition shipPosition) {
        return Math.abs(shipPosition.getHorizontalPosition()) + Math.abs(shipPosition.getVerticalPosition());
    }

    private void rotate(ShipPosition shipPosition, Command command) {
        int oldDirectionIndex = 0;

        for (int[] directionIndicator : rotationList) {
            if (shipPosition.getHorizontalDirection() == directionIndicator[0]
                    && shipPosition.getVerticalDirection() == directionIndicator[1])
                oldDirectionIndex = rotationList.indexOf(directionIndicator);
        }

        int rotationValue = command.getValue()/90*directionIndicatorsMap.get(command.getCode());

        int newDirectionIndex;
        if (rotationValue > 0)
            newDirectionIndex = (oldDirectionIndex + rotationValue)%rotationList.size();
        else
            newDirectionIndex = (oldDirectionIndex + (rotationList.size() + rotationValue))%rotationList.size();

        shipPosition.setHorizontalDirection(rotationList.get(newDirectionIndex)[0]);
        shipPosition.setVerticalDirection(rotationList.get(newDirectionIndex)[1]);
    }
}

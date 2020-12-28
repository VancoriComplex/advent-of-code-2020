package day17;

import java.util.*;

class Cube {

    private final Set<CubePosition> neighborPositions;
    private final Map<Integer, Integer> positionCoordinates;
    private final CubePosition position;
    private boolean active;

    Cube(CubePosition position) {
        this.position = position;
        positionCoordinates = new HashMap<>();
        positionCoordinates.put(0, position.x);
        positionCoordinates.put(1, position.y);
        positionCoordinates.put(2, position.z);
        positionCoordinates.put(3, position.w);
        neighborPositions = findNeighbors(position);
    }

    public CubePosition getPosition() {
        return position;
    }

    public Set<CubePosition> getNeighborPositions() {
        return neighborPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return Objects.equals(position, cube.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    boolean isActive() {
        return active;
    }

    void setActive(boolean active) {
        this.active = active;
    }

    private Set<CubePosition> findNeighbors(CubePosition position) {
        Set<CubePosition> neighborPositions = new HashSet<>();
        int[] offsets = new int[] {-1, 0, 1};
        int[] offsetCounters = new int[4];

        for (int combination = 0;
             combination < Math.pow(offsets.length, offsetCounters.length);
             combination++) {
            int[] nextCombination = new int[offsetCounters.length];
            for (int i = 0; i < nextCombination.length; i++) {
                nextCombination[i] = offsets[offsetCounters[i]] + positionCoordinates.get(i);
            }
            CubePosition nextNeighborPosition = new CubePosition(nextCombination[0], nextCombination[1], nextCombination[2], nextCombination[3]);
            if (!nextNeighborPosition.equals(position))
                neighborPositions.add(nextNeighborPosition);

            for (int counter = offsetCounters.length - 1; counter >= 0; counter--) {
                if (offsetCounters[counter] + 1 < offsets.length) {
                    offsetCounters[counter]++;
                    break;
                } else {
                    offsetCounters[counter] = 0;
                }
            }

        }
        return neighborPositions;
    }
}

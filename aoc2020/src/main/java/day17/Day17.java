package day17;

import java.util.*;

public class Day17 {

    public static long part1_part2(List<String> input) {
        Map<CubePosition, Cube> cubes = getInitialPocketDimensionState(input);
        bootUpEnergySource(cubes, 6);
        return cubes.values().stream().filter(Cube::isActive).count();
    }

    private static Map<CubePosition, Cube> getInitialPocketDimensionState(List<String> input) {
        Map<CubePosition, Cube> cubes = new HashMap<>();
        Cube nextCube;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                char state = input.get(i).charAt(j);
                nextCube = new Cube(new CubePosition(i, j, 0, 0));
                if (state == '#')
                    nextCube.setActive(true);
                cubes.put(nextCube.getPosition(), nextCube);
            }
        }
        return cubes;
    }

    private static void bootUpEnergySource(Map<CubePosition, Cube> cubes, int cycleCount) {
        for (int i = 0; i < cycleCount; i++) {
            addNewNeighbors(cubes);
            changeCubesState(cubes);
        }
    }

    private static void addNewNeighbors(Map<CubePosition, Cube> cubes) {
        Set<CubePosition> neighborPositions = new HashSet<>();
        for (Cube cube : cubes.values()) {
            neighborPositions.addAll(cube.getNeighborPositions());
        }
        for (CubePosition neighborPosition : neighborPositions) {
            cubes.putIfAbsent(neighborPosition, new Cube(neighborPosition));
        }
    }

    private static void changeCubesState(Map<CubePosition, Cube> cubes) {
        getCubesToChange(cubes).forEach(cube -> cube.setActive(!cube.isActive()));
    }

    private static Set<Cube> getCubesToChange(Map<CubePosition, Cube> cubes) {
        Set<Cube> toChange = new HashSet<>();
        for (Cube cube : cubes.values()) {
            if (hasToChange(cube, cubes))
                toChange.add(cube);
        }
        return toChange;
    }

    private static boolean hasToChange(Cube cube, Map<CubePosition, Cube> cubes) {
        int activeNeighborsCount = 0;
        for (CubePosition neighborPosition : cube.getNeighborPositions()) {
            if (cubes.containsKey(neighborPosition)) {
                if (cubes.get(neighborPosition).isActive())
                    activeNeighborsCount++;
            }
        }
        if (cube.isActive())
            return activeNeighborsCount > 3 || 2 > activeNeighborsCount;
        else
            return activeNeighborsCount == 3;
    }
}

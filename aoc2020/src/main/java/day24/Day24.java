package day24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day24 {

    private static Map<String, double[]> steps;
    private static double[][] neighbours;

    static {
        steps = new HashMap<>();
        steps.put("e", new double[] {1, 0});
        steps.put("w", new double[] {-1, 0});
        steps.put("ne", new double[] {0.5, 0.5});
        steps.put("nw", new double[] {-0.5, 0.5});
        steps.put("se", new double[] {0.5, -0.5});
        steps.put("sw", new double[] {-0.5, -0.5});

        neighbours = new double[][] {{1, 0}, {-1, 0}, {0.5, 0.5}, {-0.5, 0.5}, {0.5, -0.5}, {-0.5, -0.5}};
    }

    public static int part1(List<String> input) {
        Map<Tile, Boolean> tiles = new HashMap<>();
        for (String tileDirections : input) {
            Tile tile = getTile(tileDirections);
            flip(tile, tiles);
        }
        return (int) tiles.values().stream().filter(isBlack -> isBlack).count();
    }

    private static void flip(Tile tile, Map<Tile, Boolean> tiles) {
        if (!tiles.containsKey(tile)) {
            tile.setBlack(true);
            tiles.put(tile, tile.isBlack());
        } else {
            tiles.put(tile, !tiles.get(tile));
        }
    }

    private static Tile getTile(String tileDirections) {
        double[] position = new double[2];
        List<String> directions = getDirections(tileDirections);
        for (String direction : directions)
            calculateStep(direction, position);
        return new Tile(position[0], position[1]);
    }

    private static List<String> getDirections(String tileDirections) {
        List<String> directions = new ArrayList<>();
        String[] input = tileDirections.split("");
        String direction = "";
        for (int i = 0; i < input.length; i++) {
            if (!direction.isEmpty())
                direction = direction.concat(input[i]);
            else {
                direction = input[i];
            }
            if (!direction.equals("s") && !direction.equals("n")) {
                directions.add(direction);
                direction = "";
            }
        }
        return directions;
    }

    private static void calculateStep(String direction, double[] position) {
        position[0] += steps.get(direction)[0];
        position[1] += steps.get(direction)[1];
    }
}

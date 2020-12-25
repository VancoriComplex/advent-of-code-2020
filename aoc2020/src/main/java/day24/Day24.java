package day24;

import java.util.*;

public class Day24 {

    private static final Map<String, double[]> adjacents;

    static {
        adjacents = new HashMap<>();
        adjacents.put("e", new double[] {1, 0});
        adjacents.put("w", new double[] {-1, 0});
        adjacents.put("ne", new double[] {0.5, 0.5});
        adjacents.put("nw", new double[] {-0.5, 0.5});
        adjacents.put("se", new double[] {0.5, -0.5});
        adjacents.put("sw", new double[] {-0.5, -0.5});
    }

    public static int part1(List<String> renovationList) {
        Map<TileCoordinates, Tile> tiles = getTiles(renovationList);
        return (int) tiles.values().stream().filter(Tile::isBlack).count();
    }

    public static int part2(List<String> renovationList) {
        Map<TileCoordinates, Tile> tiles = getTiles(renovationList);
        tiles.putAll(getNewNeighbours(tiles));
        for (int i = 0; i < 100; i++) {
            Set<Tile> matchingTiles = getMatchingTiles(tiles);
            tiles.forEach((coordinates, tile) -> {
                if (matchingTiles.contains(tile))
                    tile.setBlack(!tile.isBlack());
            });
            tiles.putAll(getNewNeighbours(tiles));
        }
        return (int) tiles.values().stream().filter(Tile::isBlack).count();
    }

    private static Map<TileCoordinates, Tile> getTiles(List<String> renovationList) {
        Map<TileCoordinates, Tile> tiles = new HashMap<>();
        for (String tileCoordinates : renovationList) {
            Tile tile = getTile(tileCoordinates);
            if (!tiles.containsKey(tile.getCoordinates())) {
                tile.setBlack(true);
                tiles.put(tile.getCoordinates(), tile);
            } else {
                tiles.get(tile.getCoordinates()).setBlack(!tiles.get(tile.getCoordinates()).isBlack());
            }
        }
        return tiles;
    }

    private static Tile getTile(String tileCoordinates) {
        TileCoordinates coordinates = new TileCoordinates();
        List<String> directions = getDirections(tileCoordinates);
        for (String direction : directions)
            calculateCoordinates(direction, coordinates);
        return new Tile(coordinates);
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

    private static void calculateCoordinates(String direction, TileCoordinates coordinates) {
        coordinates.x += adjacents.get(direction)[0];
        coordinates.y += adjacents.get(direction)[1];
    }

    private static Map<TileCoordinates, Tile> getNewNeighbours(Map<TileCoordinates, Tile> tiles) {
        Map<TileCoordinates, Tile> newNeighbours = new HashMap<>();
        tiles.forEach((coordinates, tile) -> {
            for (String direction : adjacents.keySet()) {
                TileCoordinates neighbour = new TileCoordinates();
                neighbour.x = coordinates.x + adjacents.get(direction)[0];
                neighbour.y = coordinates.y + adjacents.get(direction)[1];
                if (!tiles.containsKey(neighbour))
                    newNeighbours.put(neighbour, new Tile(neighbour));
            }
        });
        return newNeighbours;
    }

    private static Set<Tile> getMatchingTiles(Map<TileCoordinates, Tile> tiles) {
        Set<Tile> matchingTiles = new HashSet<>();
        for (Map.Entry<TileCoordinates, Tile> tile : tiles.entrySet()) {
            int blackNeighbours = 0;
            for (String direction : adjacents.keySet()) {
                TileCoordinates neighbourCoordinates = new TileCoordinates();
                neighbourCoordinates.x = tile.getKey().x + adjacents.get(direction)[0];
                neighbourCoordinates.y = tile.getKey().y + adjacents.get(direction)[1];
                Tile neighbour = tiles.get(neighbourCoordinates);
                if (neighbour != null && neighbour.isBlack())
                    blackNeighbours++;
            }
            if (tile.getValue().isBlack()) {
                if (blackNeighbours == 0 || blackNeighbours > 2)
                    matchingTiles.add(tile.getValue());
            } else {
                if (blackNeighbours == 2)
                    matchingTiles.add(tile.getValue());
            }
        }
        return matchingTiles;
    }
}

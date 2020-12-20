package day20;

import java.util.List;

public class Day20 {

    public static long part1(List<String> input) {
        TileParser tileParser = new TileParser();
        List<Tile> tiles = tileParser.parseTiles(input);
        TileAnalyzer tileAnalyzer = new TileAnalyzer();
        long result = 1;
        for (Tile tile : tiles) {
            int bordersWithMatch = tileAnalyzer.countBordersWithMatch(tile, tiles);
            if (tile.getBorders().size() - bordersWithMatch == 2) {
                result *= tile.getId();
            }
        }
        return result;
    }
}

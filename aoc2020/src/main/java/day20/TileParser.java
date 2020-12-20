package day20;

import java.util.ArrayList;
import java.util.List;

class TileParser {

    List<Tile> parseTiles(List<String> input) {
        List<Tile> result = new ArrayList<>();
        Tile nextTile = new Tile();
        List<String> nextTileImage = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).isEmpty()) {
                nextTile.setImage(nextTileImage);
                result.add(nextTile);
                nextTile = new Tile();
                nextTileImage.clear();
            } else if (input.get(i).startsWith("Tile"))
                nextTile.setId(parseId(input.get(i)));
            else
                nextTileImage.add(input.get(i));
        }
        return result;
    }

    private long parseId(String s) {
        return Long.parseLong(s.split(" ")[1].replace(":", ""));
    }
}

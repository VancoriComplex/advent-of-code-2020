package day20;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TileAnalyzer {

    int countBordersWithMatch(Tile tile, List<Tile> tiles) {
        Set<String> bordersWithMatch = new HashSet<>();
        for (Tile tileToMatch : tiles) {
            if (tile.equals(tileToMatch))
                continue;
            for (String border : tile.getBorders())
                if (borderHasMatch(border, tileToMatch))
                    bordersWithMatch.add(border);
            if (bordersWithMatch.size() == tile.getBorders().size())
                return bordersWithMatch.size();
        }
        return bordersWithMatch.size();
    }

    private boolean borderHasMatch(String border, Tile tileToMatch) {
        for (String borderToMatch : tileToMatch.getBorders())
            if (borderMatches(border, borderToMatch))
                return true;
        return false;
    }

    private boolean borderMatches(String border, String borderToMatch) {
        String reversed = new StringBuilder(borderToMatch).reverse().toString();
        return (border.equals(borderToMatch) || border.equals(reversed));
    }
}

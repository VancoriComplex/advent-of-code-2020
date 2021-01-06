package day20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FragmentParser {

    Set<Fragment> parseFragments(List<String> input) {
        Set<Fragment> result = new HashSet<>();
        Fragment nextFragment = new Fragment();
        List<String> nextTileImage = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).isEmpty()) {
                nextFragment.setImage(parseImage(nextTileImage));
                result.add(nextFragment);
                nextFragment = new Fragment();
                nextTileImage.clear();
            } else if (input.get(i).startsWith("Tile"))
                nextFragment.setId(parseId(input.get(i)));
            else
                nextTileImage.add(input.get(i));
        }
        return result;
    }

    private long parseId(String s) {
        return Long.parseLong(s.split(" ")[1].replace(":", ""));
    }

    private String[][] parseImage(List<String> nextTileImage) {
        int size = nextTileImage.size();
        int index = 0;
        String[][] result = new String[size][size];
        for (String row : nextTileImage) {
            String[] nextRow = row.split("");
            result[index] = nextRow;
            index++;
        }
        return result;
    }
}

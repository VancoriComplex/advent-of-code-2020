package day3;

import java.util.List;

public class Day3 {

    public static class Part1 {
        public static int getTreesCount(
                List<String> input,
                int offsetRight,
                int offsetDown)
        {
            int treesCount = 0;
            int currentRowPosition = 0;
            int rowLength = input.get(0).length() - 1;

            for (int i = 0;
                 i < input.size() - offsetDown;
                 i += offsetDown) {
                if (currentRowPosition > rowLength - offsetRight)
                    currentRowPosition = currentRowPosition - rowLength - 1;
                if (input.get(i + offsetDown)
                        .charAt(currentRowPosition
                                + offsetRight) == '#')
                    treesCount++;
                currentRowPosition += offsetRight;
            }
            return treesCount;
        }
    }

    public static class Part2 {
        public static long getTreesMultiplied(
                List<String> input,
                int[][] offsets)
        {
            long treesMultiplied = 1;

            for (int[] offset : offsets)
                treesMultiplied *= Part1.getTreesCount(input, offset[0], offset[1]);

            return treesMultiplied;
        }
    }
}

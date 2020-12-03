package day3;

import java.util.List;

public class Day3 {

    public static class Part1 {
        public static int getTreesCount(
                List<String> slope,
                int offsetRight,
                int offsetDown)
        {
            int treesCount = 0;
            int slopePosition = 0;
            int slopeWidth = slope.get(0).length() - 1;

            for (int i = 0;
                 i < slope.size() - offsetDown;
                 i += offsetDown) {
                if (slopePosition > slopeWidth - offsetRight)
                    slopePosition = slopePosition - slopeWidth - 1;
                if (slope.get(i + offsetDown)
                        .charAt(slopePosition
                                + offsetRight) == '#')
                    treesCount++;
                slopePosition += offsetRight;
            }
            return treesCount;
        }
    }

    public static class Part2 {
        public static long getTreesMultiplied(
                List<String> slope,
                int[][] offsets)
        {
            long treesMultiplied = 1;

            for (int[] offset : offsets)
                treesMultiplied *= Part1.getTreesCount(slope, offset[0], offset[1]);

            return treesMultiplied;
        }
    }
}

import day3.Day3;
import util.HttpsReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class TestAdvent {
    public static void main(String[] args) {
        List<String> input = null;
        try {
            input = HttpsReader.getContent(new URL("https://adventofcode.com/2020/day/3/input"), "session=53616c7465645f5f11e0326ea0869d5ba95967b4a6efafe7ef87d296b656377ec5d6f3c505b7ac374c827b37d6cf1609");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert input != null;
        int[][] offsets = new int[][] {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        Long result = Day3.Part2.getTreesMultiplied(input, offsets);
        System.out.println(result);
    }
}

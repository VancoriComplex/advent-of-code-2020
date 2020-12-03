import day3.Day3;
import util.HttpsReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class TestAdvent {
    public static void main(String[] args) {
        List<String> input = null;
        try {
            input = HttpsReader.getContent(new URL("someUrl"), "sessionKey");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert input != null;
        int[][] offsets = new int[][] {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        Long result = Day3.Part2.getTreesMultiplied(input, offsets);
        System.out.println(result);
    }
}

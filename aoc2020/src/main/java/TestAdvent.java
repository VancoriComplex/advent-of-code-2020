import day10.Day10;
import util.HttpsReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static java.lang.System.out;

public class TestAdvent {
    public static void main(String[] args) {
        List<Long> input = null;
        try {
            input = HttpsReader.getContentAsLong(new URL("someUrl"), "session=Key");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert input != null;
        int result = Day10.Part1.getJoltDifferencesCountMultiplied(input);
        out.println(result);
    }
}

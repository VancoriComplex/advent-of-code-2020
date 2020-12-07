import day5.Day5;
import day6.Day6;
import util.HttpsReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class TestAdvent {
    public static void main(String[] args) {
        List<String> input = null;
        try {
            input = HttpsReader.getContent(new URL("someUrl"), "sessionKey");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert input != null;
        List<String> test = new ArrayList<>(Arrays.asList("abc", "xyz", "a"));
        int result = Day6.Part2.getAnswersCount(input);
        out.println(result);
    }
}

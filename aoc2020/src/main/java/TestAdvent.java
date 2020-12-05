import day5.Day5;
import util.HttpsReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<String> test = new ArrayList<>(Arrays.asList("FBFBBFFRLR"));
        int result = Day5.Part1.getHighestBoardingPassId(input);
        System.out.println(result);
    }
}

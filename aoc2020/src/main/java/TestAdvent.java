import day1.Day1;
import day2.Day2;
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
        Integer result = Day2.Part2.getValidPasswordsCount(input);
        System.out.println(result);
    }
}

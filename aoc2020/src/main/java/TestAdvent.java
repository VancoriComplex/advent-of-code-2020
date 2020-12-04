import day4.Day4;
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
        int result = Day4.Part1.getValidPassportsCount(input);
        System.out.println(result);
    }
}

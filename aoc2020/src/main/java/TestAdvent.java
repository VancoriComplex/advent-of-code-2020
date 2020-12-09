import day8.Day8;
import util.HttpsReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static java.lang.System.out;

public class TestAdvent {
    public static void main(String[] args) {
        List<String> input = null;
        try {
            input = HttpsReader.getContent(new URL("someUrl"), "session");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert input != null;
        int result = Day8.Part1.processInput(input);
        out.println(result);
    }
}

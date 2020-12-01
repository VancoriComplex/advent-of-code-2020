import day1.Day1;
import util.HttpsReader;

import java.io.IOException;
import java.net.URL;

public class TestAdvent {
    public static void main(String[] args) {
        String input = null;
        try {
            input = HttpsReader.getContent(new URL("someUrl"), "sessionKey");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert input != null;
        Integer result = Day1.Part1.processExpenseReport(input, 2020);
        System.out.println(result);
    }
}

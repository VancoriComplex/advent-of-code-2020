import day7.Day7;
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
        List<String> test = new ArrayList<>(Arrays.asList("light red bags contain 1 bright white bag, 2 muted yellow bags.",
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                "bright white bags contain 1 shiny gold bag.",
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                "faded blue bags contain no other bags.",
                "dotted black bags contain no other bags."));
        List<String> test2 = new ArrayList<>(Arrays.asList("shiny gold bags contain 2 dark red bags.",
                "dark red bags contain 2 dark orange bags.",
                "dark orange bags contain 2 dark yellow bags.",
                "dark yellow bags contain 2 dark green bags.",
                "dark green bags contain 2 dark blue bags.",
                "dark blue bags contain 2 dark violet bags.",
                "dark violet bags contain no other bags."));
        long result = Day7.Part2.getBagCapacity(input);
        out.println(result);
    }
}

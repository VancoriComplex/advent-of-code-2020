package day19;

import java.util.*;

public class Day19 {

    private static final Map<Integer, Integer> seen;

    static {
        seen = new HashMap<>();
        seen.put(8, 0);
        seen.put(11, 0);
    }

    public static int part1_part2(List<String> input) {
        Map<Integer, String> rules = getRules(input);
        Set<String> messages = getMessages(input);

        String ruleZero = resolveRule(0, rules);

        return (int) messages.stream().filter(message -> message.matches(ruleZero)).count();
    }

    private static String resolveRule(int key, Map<Integer, String> rules) {
        String result = "";
        if (seen.containsKey(key)) {
            seen.put(key, seen.get(key) + 1);
            if (seen.get(key) > 5)
                return result;
        }
        String value = rules.get(key);
        if (value.equals("\"a\"") || value.equals("\"b\""))
            return value.replaceAll("\"", "");
        else {
            for (String rule : value.split(" ")) {
                if (rule.equals("|"))
                    result = result.concat(rule);
                else
                    result = result.concat("(" + resolveRule(Integer.parseInt(rule), rules) + ")");
            }
        }
        return result;
    }

    private static Map<Integer, String> getRules(List<String> input) {
        Map<Integer, String> rules = new LinkedHashMap<>();
        for (String line : input) {
            if (line.isEmpty())
                break;
            String[] content = line.split(": ");
            int key = Integer.parseInt(content[0]);
            if (key == 8)
                content[1] = "42 | 42 8";
            if (key == 11)
                content[1] = "42 31 | 42 11 31";
            rules.put(key, content[1]);
        }
        return rules;
    }

    private static Set<String> getMessages(List<String> input) {
        Set<String> messages = new HashSet<>();
        boolean collecting = false;
        for (String line : input) {
            if (line.isEmpty()) {
                collecting = true;
                continue;
            }
            if (collecting)
                messages.add(line);
        }
        return messages;
    }
}

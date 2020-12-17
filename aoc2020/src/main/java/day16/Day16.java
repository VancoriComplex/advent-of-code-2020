package day16;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 {

    public static int part1(List<String> input) {
        Ticket yourTicket = new Ticket();
        Set<TicketRule> rules = new HashSet<>();
        boolean collectingTickets = false;
        int result = 0;

        for (int i = 0; i < input.size(); i++) {
            if (!input.get(i).isEmpty()) {
                if (collectingTickets) {
                    Integer[] ticketFields = getTicketFields(input.get(i));
                    result += getInvalidFieldsSum(ticketFields, rules);
                } else if (input.get(i).startsWith("your ticket")) {
                    Integer[] fields = getTicketFields(input.get(++i));
                    yourTicket.setFields(fields);
                } else if (input.get(i).startsWith("nearby tickets"))
                    collectingTickets = true;
                else {
                    rules.add(getTicketRule(input.get(i)));
                }
            }
        }
        return result;
    }

    public static long part2(List<String> input) {
        Ticket yourTicket = new Ticket();
        List<TicketRule> rules = new ArrayList<>();
        List<Ticket> validTickets = new ArrayList<>();
        boolean collectingTickets = false;

        //collect data from the input: rules, your ticket and nearby *valid* tickets
        for (int i = 0; i < input.size(); i++) {
            if (!input.get(i).isEmpty()) {
                if (collectingTickets) {
                    Integer[] fields = getTicketFields(input.get(i));
                    if (ticketIsValid(fields, rules))
                        validTickets.add(new Ticket(fields));
                } else if (input.get(i).startsWith("your ticket")) {
                    Integer[] fields = getTicketFields(input.get(++i));
                    yourTicket.setFields(fields);
                    validTickets.add(yourTicket);
                } else if (input.get(i).startsWith("nearby tickets"))
                    collectingTickets = true;
                else {
                    rules.add(getTicketRule(input.get(i)));
                }
            }
        }

        Map<Integer, Set<TicketRule>> possibleRules = new HashMap<>();
        Map<Integer, TicketRule> orderedRules = new HashMap<>();
        TicketRule matched = null;

        //collect possible rules for each field
        for (int i = 0; i < rules.size(); i++) {
            int finalI = i;
            Set<Integer> iFields = validTickets.stream().map(ticket -> ticket.getFields()[finalI]).collect(Collectors.toSet());
            Set<TicketRule> ruleSet = rules.stream().filter(ticketRule -> iFields.stream().allMatch(ticketRule::fieldIsInRange)).collect(Collectors.toSet());
            //if field has only one rule in a set, we know it's a match
            //we save it for later and put it in ordered rules map
            if (ruleSet.size() == 1) {
                matched = ruleSet.iterator().next();
                orderedRules.put(i, matched);
            }
            possibleRules.put(i, ruleSet);
        }

        //collect next valid rules for each field
        //by erasing from each possible rule set every rule that we found as a match,
        //starting from the first rule we found in previous iteration
        while (orderedRules.size() != rules.size()) {
            TicketRule finalMatched = matched;
            possibleRules.values().forEach(set -> set.remove(finalMatched));
            Optional<Map.Entry<Integer, Set<TicketRule>>> nextRule = possibleRules.entrySet().stream().filter(entry -> entry.getValue().size() == 1).findFirst();
            if (nextRule.isPresent()) {
                matched = nextRule.get().getValue().iterator().next();
                orderedRules.put(nextRule.get().getKey(), matched);
            }
        }

        long result = 1;
        //find rules starting with the word "departure"
        //and multiply the corresponding values from your ticket together
        for (Map.Entry<Integer, TicketRule> entry : orderedRules.entrySet()) {
            if (entry.getValue().getName().startsWith("departure"))
                result *= yourTicket.getFields()[entry.getKey()];
        }

        return result;
    }

    private static Integer[] getTicketFields(String fields) {
        List<Integer> result = new ArrayList<>();
        String[] fieldsToParse = fields.split(",");
        for (int i = 0; i < fieldsToParse.length; i++)
            result.add(Integer.parseInt(fieldsToParse[i]));
        return result.toArray(new Integer[0]);
    }

    private static TicketRule getTicketRule(String line) {
        String name = line.split(": ")[0];
        List<Integer> ranges = Arrays.stream(line.split("[\\D]"))
                .filter(x -> !x.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new TicketRule(name,
                new int[][] {{ranges.get(0), ranges.get(1)},
                        {ranges.get(2), ranges.get(3)}});
    }

    private static int getInvalidFieldsSum(Integer[] fields, Set<TicketRule> rules) {
        int result = 0;
        for (int i = 0; i < fields.length; i++) {
            int finalI = i;
            if (rules.stream()
                    .noneMatch(rule -> rule.fieldIsInRange(fields[finalI])))
                result += fields[i];
        }
        return result;
    }

    private static boolean ticketIsValid(Integer[] ticketFields, List<TicketRule> rules) {
        for (int field : ticketFields) {
            if (rules.stream().noneMatch(ticketRule -> ticketRule.fieldIsInRange(field)))
                return false;
        }
        return true;
    }
}

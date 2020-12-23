package day22;

import java.util.*;

public class Day22 {

    public static int part1(List<String> input) {
        List<List<Integer>> decks = parseDecks(input);
        List<Integer> deckOne = decks.get(0);
        List<Integer> deckTwo = decks.get(1);
        int winningDeck = Combat.play(deckOne, deckTwo);
        return winningDeckScore(decks.get(winningDeck));
    }

    public static int part2(List<String> input) {
        List<List<Integer>> decks = parseDecks(input);
        List<Integer> deckOne = decks.get(0);
        List<Integer> deckTwo = decks.get(1);
        int winningDeck = Combat.playRecursive(deckOne, deckTwo);
        return winningDeckScore(decks.get(winningDeck));
    }

    private static int winningDeckScore(List<Integer> winningDeck) {
        int result = 0;
        for (int i = 0; i < winningDeck.size(); i++) {
            result += winningDeck.get(i)*(i + 1);
        }
        return result;
    }

    private static List<List<Integer>> parseDecks(List<String> input) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> nextDeck = new ArrayList<>();
        for (int i = input.size() - 1; i >= 0; i--) {
            if (input.get(i).startsWith("Player")) {
                result.add(0, nextDeck);
                continue;
            }
            if (input.get(i).isEmpty()) {
                nextDeck = new ArrayList<>();
                continue;
            }
            nextDeck.add(Integer.parseInt(input.get(i)));
        }
        return result;
    }
}

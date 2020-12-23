package day22;

import java.util.*;

class Combat {

    static int play(List<Integer> deckOne, List<Integer> deckTwo) {
        while (!deckOne.isEmpty() && !deckTwo.isEmpty()) {
            playRound(deckOne, deckTwo);
        }
        return deckOne.isEmpty() ? 1 : 0;
    }

    static int playRecursive(List<Integer> deckOne, List<Integer> deckTwo) {
        Set<List<Integer[]>> playedDecks = new HashSet<>();
        while (!deckOne.isEmpty() && !deckTwo.isEmpty()) {
            int topCardDeckOne = deckOne.get(deckOne.size() - 1);
            int topCardDeckTwo = deckTwo.get(deckTwo.size() - 1);
            Integer[] preRoundDeckOne = new Integer[deckOne.size()];
            Integer[] preRoundDeckTwo = new Integer[deckTwo.size()];
            deckOne.toArray(preRoundDeckOne);
            deckTwo.toArray(preRoundDeckTwo);
            if (decksWerePlayed(playedDecks, preRoundDeckOne, preRoundDeckTwo)) {
                return 0;
            } else if (deckOne.size() > topCardDeckOne
                    && deckTwo.size() > topCardDeckTwo) {
                int winningDeck =
                        playRecursive(
                                getSubDeck(deckOne, topCardDeckOne),
                                getSubDeck(deckTwo, topCardDeckTwo)
                        );
                if (winningDeck == 0) {
                    takeCardFrom(deckTwo);
                    giveCardTo(deckOne, topCardDeckTwo);
                } else {
                    takeCardFrom(deckOne);
                    giveCardTo(deckTwo, topCardDeckOne);
                }
            } else {
                playRound(deckOne, deckTwo);
            }
            playedDecks.add(List.of(preRoundDeckOne, preRoundDeckTwo));
        }
        return deckOne.isEmpty() ? 1 : 0;
    }

    private static void playRound(List<Integer> deckOne, List<Integer> deckTwo) {
        int topCardDeckOne = deckOne.get(deckOne.size() - 1);
        int topCardDeckTwo = deckTwo.get(deckTwo.size() - 1);
        if (topCardDeckOne > topCardDeckTwo) {
            takeCardFrom(deckTwo);
            giveCardTo(deckOne, topCardDeckTwo);
        } else {
            takeCardFrom(deckOne);
            giveCardTo(deckTwo, topCardDeckOne);
        }
    }

    private static void takeCardFrom(List<Integer> deck) {
        deck.remove(deck.size() - 1);
    }

    private static void giveCardTo(List<Integer> deck, int newCard) {
        int topCard = deck.get(deck.size() - 1);
        deck.remove(deck.size() - 1);
        deck.add(0, topCard);
        deck.add(0, newCard);
    }

    private static boolean decksWerePlayed(Set<List<Integer[]>> playedDecks,
                                           Integer[] deckOne,
                                           Integer[] deckTwo) {
        return playedDecks.stream()
                .anyMatch(decks ->
                        Arrays.equals(decks.get(0), deckOne)
                                && Arrays.equals(decks.get(1), deckTwo));
    }

    private static List<Integer> getSubDeck(List<Integer> deck, int card) {
        List<Integer> result = new ArrayList<>();
        for (int i = deck.size() - card - 1; i < deck.size() - 1; i++) {
            result.add(deck.get(i));
        }
        return result;
    }
}

package day12;

class Command {

    private char action;
    private int value;

    Command(String line) {
        this.action = line.charAt(0);
        this.value = Integer.parseInt(line.substring(1));
    }

    char getAction() {
        return action;
    }

    int getValue() {
        return value;
    }
}

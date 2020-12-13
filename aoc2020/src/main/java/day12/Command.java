package day12;

class Command {

    private char code;
    private int value;

    Command(String line) {
        this.code = line.charAt(0);
        this.value = Integer.parseInt(line.substring(1));
    }

    char getCode() {
        return code;
    }

    int getValue() {
        return value;
    }
}

package day16;

class TicketRule {

    private String name;
    private int[][] ranges;

    TicketRule(String name, int[][] ranges) {
        this.name = name;
        this.ranges = ranges;
    }

    String getName() {
        return name;
    }

    boolean fieldIsInRange(int field) {
        return (ranges[0][0] <= field && field <= ranges[0][1]) || (ranges[1][0] <= field && field <= ranges[1][1]);
    }
}

package day16;

class Ticket {

    private Integer[] fields;

    Ticket() {}

    Ticket(Integer[] fields) {
        this.fields = fields;
    }

    void setFields(Integer[] fields) {
        this.fields = fields;
    }

    Integer[] getFields() {
        return fields;
    }
}

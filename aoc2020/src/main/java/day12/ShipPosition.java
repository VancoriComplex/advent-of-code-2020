package day12;

class ShipPosition {

    private int horizontalPosition;
    private int verticalPosition;
    private int horizontalDirection;
    private int verticalDirection;

    ShipPosition() {
        this(0, 0, 1, 0);
    }

    ShipPosition(int horizontalPosition, int verticalPosition, int horizontalDirection, int verticalDirection) {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
        this.horizontalDirection = horizontalDirection;
        this.verticalDirection = verticalDirection;
    }

    int getVerticalDirection() {
        return verticalDirection;
    }

    void setVerticalDirection(int verticalDirection) {
        this.verticalDirection = verticalDirection;
    }

    int getHorizontalPosition() {
        return horizontalPosition;
    }

    void setHorizontalPosition(int horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

    int getVerticalPosition() {
        return verticalPosition;
    }

    void setVerticalPosition(int verticalPosition) {
        this.verticalPosition = verticalPosition;
    }

    int getHorizontalDirection() {
        return horizontalDirection;
    }

    void setHorizontalDirection(int horizontalDirection) {
        this.horizontalDirection = horizontalDirection;
    }
}

package day24;

class Tile {

    private final TileCoordinates coordinates;
    private boolean isBlack;

    Tile(TileCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    TileCoordinates getCoordinates() {
        return coordinates;
    }

    boolean isBlack() {
        return isBlack;
    }

    void setBlack(boolean black) {
        isBlack = black;
    }
}

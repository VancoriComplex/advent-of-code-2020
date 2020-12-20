package day20;


import java.util.ArrayList;
import java.util.List;

class Tile {

    private long id;
    private List<String> image;
    private List<String> borders;

    void setId(long id) {
        this.id = id;
    }

    long getId() {
        return id;
    }

    void setImage(List<String> image) {
        this.image = image;
        borders = parseBorders(image);
    }

    List<String> getImage() {
        return image;
    }

    List<String> getBorders() {
        return borders;
    }

    private List<String> parseBorders(List<String> image) {
        List<String> result = new ArrayList<>();
        result.add(image.get(0));
        result.add(image.get(image.size() - 1));
        String leftBorder = "";
        String rightBorder = "";
        for (int i = 0; i < image.size(); i++) {
            leftBorder = leftBorder.concat(String.valueOf(image.get(i).charAt(0)));
            rightBorder = rightBorder.concat(String.valueOf(image.get(i).charAt(image.get(i).length() - 1)));
        }
        result.add(leftBorder);
        result.add(rightBorder);
        return result;
    }
}

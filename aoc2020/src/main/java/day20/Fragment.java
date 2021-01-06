package day20;

import java.util.Objects;

class Fragment {

    private long id;
    private String[][] image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fragment fragment = (Fragment) o;
        return id == fragment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    String[][] getImage() {
        return image;
    }

    void setImage(String[][] image) {
        this.image = image;
    }

    String[] getTopBorder() {
        return image[0];
    }

    String[] getBottomBorder() {
        return image[image.length - 1];
    }

    String[] getLeftBorder() {
        String[] leftBorder = new String[image.length];
        for (int i = 0; i < image.length; i++)
            leftBorder[i] = image[i][0];
        return leftBorder;
    }

    String[] getRightBorder() {
        String[] rightBorder = new String[image.length];
        for (int i = 0; i < image.length; i++)
            rightBorder[i] = image[i][image.length - 1];
        return rightBorder;
    }

    void flipHorizontal() {
        int l = image.length;
        String[][] flippedImage = new String[l][l];
        for (int row = 0; row < l; row++)
            for (int col = 0; col < l; col++)
                flippedImage[row][l - 1 - col] = image[row][col];
        image = flippedImage;
    }

    void flipVertical() {
        int l = image.length;
        String[][] flippedImage = new String[l][l];
        for (int row = 0; row < l; row++)
            for (int col = 0; col < l; col++)
                flippedImage[l - 1 - row][col] = image[row][col];
        image = flippedImage;
    }

    void rotate() {
        int l = image.length;
        String[][] rotatedImage = new String[l][l];
        for (int row = 0; row < l; row++)
            for (int col = 0; col < l; col++)
                rotatedImage[col][l-1-row] = image[row][col];
        image = rotatedImage;
    }
}

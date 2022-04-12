package tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private boolean collision = false;

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public boolean isCollision() {
        return collision;
    }
}

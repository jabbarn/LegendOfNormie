package entity;

import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int speed;
    protected BufferedImage up, up2, down, down2, left, left2, right, right2;
    protected String direction;
    protected boolean spriteMoving;
    protected int spriteCounter;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

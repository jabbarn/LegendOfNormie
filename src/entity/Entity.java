package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int speed;
    protected BufferedImage up, up2, down, down2, left, left2, right, right2;
    protected String direction;
    protected boolean spriteMoving;
    protected int spriteCounter;
    protected Rectangle hitbox;
    protected  int hitboxDefaultX, hitboxDefaultY;
    protected boolean collisionOn = false;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public String getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getHitboxDefaultX() {
        return hitboxDefaultX;
    }

    public int getHitboxDefaultY() {
        return hitboxDefaultY;
    }
}

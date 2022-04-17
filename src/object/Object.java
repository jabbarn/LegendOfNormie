package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Object {
    protected BufferedImage image;
    protected String name;
    protected boolean collision = false;
    protected int worldX, worldY;
    protected Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    protected  int hitboxDefaultX = 0;
    protected  int hitboxDefaultY = 0;

    public Object(String name, int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.name = name;
    }
    public void draw(GamePanel panel, Graphics2D graphics2D) {
        int screenX = worldX - panel.getPlayer().getX() + panel.getPlayer().getScreenX();
        int screenY = worldY - panel.getPlayer().getY() + panel.getPlayer().getScreenY();

        if( worldX + panel.getTileSize() > panel.getPlayer().getX() - panel.getPlayer().getScreenX() &&
            worldX - panel.getTileSize() < panel.getPlayer().getX() + panel.getPlayer().getScreenX() &&
            worldY + panel.getTileSize() > panel.getPlayer().getY() - panel.getPlayer().getScreenY() &&
            worldY - panel.getTileSize() < panel.getPlayer().getY() + panel.getPlayer().getScreenY())
        {
            graphics2D.drawImage(image, screenX, screenY, panel.getTileSize(), panel.getTileSize(), null);
        }
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getHitboxDefaultX() {
        return hitboxDefaultX;
    }

    public int getHitboxDefaultY() {
        return hitboxDefaultY;
    }

    public boolean isCollision() {
        return collision;
    }

    public String getName() {
        return name;
    }
}

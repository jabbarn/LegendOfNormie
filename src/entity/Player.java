package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    GamePanel panel;
    KeyHandler handler;

    public Player(GamePanel panel, KeyHandler handler) {
        this.panel = panel;
        this.handler = handler;
        this.setDefaultValues();
    }
    public void setDefaultValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 4;
    }
    public void update() {
        if(handler.isUpPressed()) {
            this.y -= this.speed;
        }
        else if (handler.isDownPressed()) {
            this.y += this.speed;
        }
        else if (handler.isLeftPressed()) {
            this.x -= this.speed;
        }
        else if (handler.isRightPressed()) {
            this.x += this.speed;
        }
    }
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(this.x, this.y, panel.getTileSize(), panel.getTileSize());
    }
}

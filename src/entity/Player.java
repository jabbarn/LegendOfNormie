package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel panel;
    KeyHandler handler;

    private final int screenX;
    private final int screenY;

    public Player(GamePanel panel, KeyHandler handler) {
        this.panel = panel;
        this.handler = handler;
        this.screenX = panel.getScreenWidth()/2 - (panel.getTileSize()/2);
        this.screenY = panel.getScreenHeight()/2 - (panel.getTileSize()/2);
        this.hitbox = new Rectangle(8, 16, this.panel.getTileSize()-16, this.panel.getTileSize()-16);
        this.setDefaultValues();
        this.getPlayerImage();
    }
    public void setDefaultValues() {
        this.x = this.panel.getTileSize() * 23;
        this.y = this.panel.getTileSize() * 21;
        this.speed = 4;
        this.direction = "down";
    }
    public void getPlayerImage() {
        try {
            this.up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            this.up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            this.down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            this.down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            this.left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            this.left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            this.right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            this.right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        } catch(IOException e) {
            e.getStackTrace();
        }
    }
    public void update() {
        if(this.handler.isUpPressed()) {
            this.direction = "up";
            this.checkSpriteMoving();
        }
        else if (this.handler.isDownPressed()) {
            this.direction = "down";
            this.checkSpriteMoving();
        }
        else if (this.handler.isLeftPressed()) {
            this.direction = "left";
            this.checkSpriteMoving();
        }
        else if (this.handler.isRightPressed()) {
            this.direction = "right";
            this.checkSpriteMoving();
        }
    }
    public void checkSpriteMoving() {
        this.collisionOn = false;
        this.panel.getChecker().checkTile(this);
        if(!this.collisionOn) {
            switch (this.direction) {
                case "up" -> this.y -= this.speed;
                case "down" -> this.y += this.speed;
                case "left" -> this.x -= this.speed;
                case "right" -> this.x += this.speed;
            }
        }
        this.spriteCounter++;
        if(this.spriteCounter > 12) {
            if (this.spriteMoving) {
                this.spriteMoving = false;
            }
            else if (!this.spriteMoving) {
                this.spriteMoving = true;
            }
            this.spriteCounter = 0;
        }
    }
    public void draw(Graphics2D graphics2D) {
//        graphics2D.setColor(Color.white);
//        graphics2D.fillRect(this.x, this.y, panel.getTileSize(), panel.getTileSize());
        BufferedImage image = switch (direction) {
            case "up" -> (this.spriteMoving ? this.up : this.up2);
            case "down" -> (this.spriteMoving ? this.down : this.down2);
            case "left" -> (this.spriteMoving ? this.left : this.left2);
            case "right" -> (this.spriteMoving ? this.right : this.right2);
            default -> null;
        };
        graphics2D.drawImage(image, this.screenX, this.screenY, this.panel.getTileSize(), this.panel.getTileSize(), null);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}

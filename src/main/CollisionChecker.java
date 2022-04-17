package main;

import entity.Entity;
//import entity.Player;
//
//import java.awt.*;

public class CollisionChecker {
    GamePanel panel;

    public CollisionChecker(GamePanel panel) {
        this.panel = panel;
    }
//    public void drawTile(Entity entity, Graphics2D graphics2D) {
//
//        Player p = (Player) entity;
//        graphics2D.setColor(Color.MAGENTA);
//        graphics2D.fillRect(p.getScreenX() + 8, p.getScreenY() + 8, entity.getHitbox().width, entity.getHitbox().height);
//
//    }
    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.getX() + entity.getHitbox().x;
        int entityRightWorldX = entity.getX() + entity.getHitbox().x + entity.getHitbox().width;
        int entityTop = entity.getY() + entity.getHitbox().y;
        int entityBottom = entity.getY() + entity.getHitbox().y + entity.getHitbox().height;

        int entityLeftCol = entityLeftWorldX / this.panel.getTileSize();
        int entityRightCol = entityRightWorldX / this.panel.getTileSize();
        int entityTopRow = entityTop / this.panel.getTileSize();
        int entityBottomRow = entityBottom / this.panel.getTileSize();

        int tileCorner1, tileCorner2;

        switch (entity.getDirection()) {
            case "up" -> {
                entityTopRow = (entityTop - entity.getSpeed()) / this.panel.getTileSize();
                tileCorner1 = this.panel.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileCorner2 = this.panel.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                if (this.panel.getTileManager().getTiles()[tileCorner1].isCollision() ||
                        this.panel.getTileManager().getTiles()[tileCorner2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottom + entity.getSpeed()) / this.panel.getTileSize();
                tileCorner1 = this.panel.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileCorner2 = this.panel.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (this.panel.getTileManager().getTiles()[tileCorner1].isCollision() ||
                        this.panel.getTileManager().getTiles()[tileCorner2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / this.panel.getTileSize();
                tileCorner1 = this.panel.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileCorner2 = this.panel.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (this.panel.getTileManager().getTiles()[tileCorner1].isCollision() ||
                        this.panel.getTileManager().getTiles()[tileCorner2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / this.panel.getTileSize();
                tileCorner1 = this.panel.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileCorner2 = this.panel.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (this.panel.getTileManager().getTiles()[tileCorner1].isCollision() ||
                        this.panel.getTileManager().getTiles()[tileCorner2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean isPlayer) {
        int index = -1;

        for (int i = 0; i < this.panel.getObjects().length; i++) {
            if(this.panel.getObjects()[i] != null) {
                entity.getHitbox().x += entity.getX();
                entity.getHitbox().y += entity.getY();

                this.panel.getObjects()[i].getHitbox().x += this.panel.getObjects()[i].getWorldX();
                this.panel.getObjects()[i].getHitbox().y += this.panel.getObjects()[i].getWorldY();

                switch (entity.getDirection()) {
                    case "up" -> {
                        entity.getHitbox().y -= entity.getSpeed();
                        if (entity.getHitbox().intersects(this.panel.getObjects()[i].getHitbox())) {
                            System.out.println(this.panel.getObjects()[i].isCollision());
                            if(this.panel.getObjects()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.getHitbox().y += entity.getSpeed();
                        if (entity.getHitbox().intersects(this.panel.getObjects()[i].getHitbox())) {
                            if(this.panel.getObjects()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.getHitbox().x -= entity.getSpeed();
                        if (entity.getHitbox().intersects(this.panel.getObjects()[i].getHitbox())) {
                            if(this.panel.getObjects()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.getHitbox().x += entity.getSpeed();
                        if (entity.getHitbox().intersects(this.panel.getObjects()[i].getHitbox())) {
                            if(this.panel.getObjects()[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(isPlayer) {
                                index = i;
                            }
                        }
                    }
                }
                entity.getHitbox().x = entity.getHitboxDefaultX();
                entity.getHitbox().y = entity.getHitboxDefaultY();
                this.panel.getObjects()[i].getHitbox().x = this.panel.getObjects()[i].getHitboxDefaultX();
                this.panel.getObjects()[i].getHitbox().y = this.panel.getObjects()[i].getHitboxDefaultY();
            }
        }
        return index;
    }
}

package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel panel;

    public CollisionChecker(GamePanel panel) {
        this.panel = panel;
    }
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
                tileCorner1 = this.panel.getManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileCorner2 = this.panel.getManager().getMapTileNum()[entityRightCol][entityTopRow];
                if (this.panel.getManager().getTiles()[tileCorner1].isCollision() ||
                        this.panel.getManager().getTiles()[tileCorner2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottom + entity.getSpeed()) / this.panel.getTileSize();
                tileCorner1 = this.panel.getManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileCorner2 = this.panel.getManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (this.panel.getManager().getTiles()[tileCorner1].isCollision() ||
                        this.panel.getManager().getTiles()[tileCorner2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / this.panel.getTileSize();
                tileCorner1 = this.panel.getManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileCorner2 = this.panel.getManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (this.panel.getManager().getTiles()[tileCorner1].isCollision() ||
                        this.panel.getManager().getTiles()[tileCorner2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / this.panel.getTileSize();
                tileCorner1 = this.panel.getManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileCorner2 = this.panel.getManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (this.panel.getManager().getTiles()[tileCorner1].isCollision() ||
                        this.panel.getManager().getTiles()[tileCorner2].isCollision()) {
                    entity.setCollisionOn(true);
                }
            }
        }
    }
}

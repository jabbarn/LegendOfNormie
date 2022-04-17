package main;

import object.Boots;
import object.Chest;
import object.Door;
import object.Key;

public class AssetManager {
    GamePanel panel;

    public AssetManager(GamePanel panel) {
        this.panel = panel;
    }
    public void set() {
        this.panel.getObjects()[0] = new Key(
                "key", 23*this.panel.getTileSize(), 7*this.panel.getTileSize()
        );
        this.panel.getObjects()[1] = new Key(
                "key", 23*this.panel.getTileSize(), 40*this.panel.getTileSize()
        );
        this.panel.getObjects()[2] = new Key(
                "key", 38*this.panel.getTileSize(), 8*this.panel.getTileSize()
        );
        this.panel.getObjects()[3] = new Door(
                "door", 10*this.panel.getTileSize(), 11*this.panel.getTileSize()
        );
        this.panel.getObjects()[4] = new Door(
                "door", 8*this.panel.getTileSize(), 28*this.panel.getTileSize()
        );
        this.panel.getObjects()[5] = new Door(
                "door", 12*this.panel.getTileSize(), 22*this.panel.getTileSize()
        );
        this.panel.getObjects()[6] = new Chest(
                "chest", 10*this.panel.getTileSize(), 7*this.panel.getTileSize()
        );
        this.panel.getObjects()[7] = new Boots(
                "boots", 37*this.panel.getTileSize(), 42*this.panel.getTileSize()
        );

    }
}

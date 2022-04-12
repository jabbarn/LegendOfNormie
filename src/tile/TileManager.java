package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TileManager {
    GamePanel panel;
    Tile[] tiles;
    int mapTileNum[][];

    public TileManager(GamePanel panel) {
        this.panel = panel;
        tiles = new Tile[10];
        mapTileNum = new int[panel.getMaxWorldCol()][panel.getMaxWorldRow()];
        getTileImage();
        loadMap("/maps/map01.txt");
    }
    public void loadMap(String map) {
        try {
            InputStream mapStream = Objects.requireNonNull(getClass().getResourceAsStream(map));
            BufferedReader reader = new BufferedReader(new InputStreamReader(mapStream));

            int col = 0;
            int row = 0;

            while(col < this.panel.getMaxWorldCol() && row < panel.getMaxWorldRow()) {
                String line = reader.readLine();
                while(col < panel.getMaxWorldCol()) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == panel.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch(Exception e) {
            e.getStackTrace();
        }
    }
    public void getTileImage() {
        try {
            ArrayList<String> arr = new ArrayList<String>(Arrays.asList(
                "/tiles/grass.png", "/tiles/wall.png", "/tiles/water.png",
                "/tiles/earth.png", "/tiles/tree.png", "/tiles/sand.png"
            ));

            for (String item: arr) {
                this.tiles[arr.indexOf(item)] = new Tile();
                this.tiles[arr.indexOf(item)].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(item))));
                if( Objects.equals(arr.get(arr.indexOf(item)), "/tiles/wall.png") ||
                    Objects.equals(arr.get(arr.indexOf(item)), "/tiles/water.png") ||
                    Objects.equals(arr.get(arr.indexOf(item)), "/tiles/tree.png"))
                {
                    this.tiles[arr.indexOf(item)].setCollision(true);
                }
            }
        } catch(IOException e) {
            e.getStackTrace();
        }
    }
    public void draw(Graphics2D graphics2D) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < this.panel.getMaxWorldCol() && worldRow < this.panel.getMaxWorldRow()) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * this.panel.getTileSize();
            int worldY = worldRow * this.panel.getTileSize();
            int screenX = worldX - this.panel.getPlayer().getX() + this.panel.getPlayer().getScreenX();
            int screenY = worldY - this.panel.getPlayer().getY() + this.panel.getPlayer().getScreenY();

            if( worldX + this.panel.getTileSize() > this.panel.getPlayer().getX() - this.panel.getPlayer().getScreenX() &&
                worldX - this.panel.getTileSize() < this.panel.getPlayer().getX() + this.panel.getPlayer().getScreenX() &&
                worldY + this.panel.getTileSize() > this.panel.getPlayer().getY() - this.panel.getPlayer().getScreenY() &&
                worldY - this.panel.getTileSize() < this.panel.getPlayer().getY() + this.panel.getPlayer().getScreenY())
            {
                graphics2D.drawImage(this.tiles[tileNum].getImage(), screenX, screenY, this.panel.getTileSize(), this.panel.getTileSize(), null);
            }
            worldCol++;

            if(worldCol == this.panel.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public Tile[] getTiles() {
        return tiles;
    }
}

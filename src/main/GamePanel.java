package main;

import entity.Player;
import object.Object;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private final int originalTileSize = 16; // 16x16 tiles
//  P1: scale is because modern resolutions have much higher pixel dimensions, the 16x16 is almost unnoticeable, that's why it gets scaled!
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale; // 48 pixels
    private final int maxScreenColumn = 20;
    private final int maxScreenRow = 15;
    private final int screenWidth = tileSize * maxScreenColumn;
    private final int screenHeight = tileSize * maxScreenRow;

    // world settings
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;
    private final int worldWidth = this.tileSize * this.maxWorldCol;
    private final int worldHeight = this.tileSize * this.maxWorldRow;

    private final TileManager tileManager = new TileManager(this);
    private final AssetManager assetManager = new AssetManager(this);
    protected KeyHandler handler = new KeyHandler();
    volatile Thread gameThread;
    private CollisionChecker checker = new CollisionChecker(this);

    private Player player = new Player(this, this.handler);
    private Object[] objects = new Object[10];

    private final int FPS = 60;
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.handler);
        this.setFocusable(true);
    }
    public void setupGame() {
        assetManager.set();
    }
    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }
    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        double drawInterval = 1e9d / this.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1e9d){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        player.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        tileManager.draw(graphics2D);
        for (Object object : this.objects) {
            if (object != null) {
                object.draw(this, graphics2D);
            }
        }
        player.draw(graphics2D);
        graphics2D.dispose();
    }
    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenColumn() {
        return maxScreenColumn;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public Player getPlayer() {
        return player;
    }

    public CollisionChecker getChecker() {
        return checker;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public Object[] getObjects() {
        return objects;
    }
}

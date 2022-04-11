package main;

import entity.Player;

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
    protected KeyHandler handler = new KeyHandler();
    volatile Thread gameThread;
    private Player player = new Player(this, handler);
    private final int FPS = 60;
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(handler);
        this.setFocusable(true);
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
        player.draw(graphics2D);
        graphics2D.dispose();
    }
    public int getTileSize() {
        return tileSize;
    }
}

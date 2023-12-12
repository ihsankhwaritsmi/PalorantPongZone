package main;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import arena.Arena;
import entities.Ball;
import entities.Enemy;
import entities.Player;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private Enemy enemy;
    private Arena arena;
    private Ball ball;
    private int chosenOneIndex;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public Game(int PLAYER) {
        this.chosenOneIndex = PLAYER;
        initClasses();
        gamePanel = new GamePanel(this, new Arena());
        gamePanel.add(new Arena().getLabel());
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        ball.playerCollisionCheck(player.getHBoxTopLeft().x, player.getHBoxTopLeft().y, player.getHBoxTopRight().x,player.getHBoxBottomRight().y,player);
        ball.enemyCollisionCheck(enemy.getHBoxTopLeft().x, enemy.getHBoxTopLeft().y, enemy.getHBoxTopRight().x,enemy.getHBoxBottomRight().y, enemy);
        startGameLoop();

    }

    private void initClasses() {
        arena = new Arena();
        ball = new Ball((int)(screenSize.getWidth()/2),(int)(screenSize.getHeight()/2));
        player = new Player((int)(screenSize.getWidth()/2)-500, (int)(screenSize.getHeight()/2)-200,256,160, this.chosenOneIndex);
        enemy = new Enemy((int)(screenSize.getWidth()/2)+500, (int)(screenSize.getHeight()/2)-200,256,160, this.chosenOneIndex);
        ball.playerCollisionCheck(player.getHBoxTopLeft().x, player.getHBoxTopLeft().y, player.getHBoxTopRight().x,player.getHBoxBottomRight().y, player);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        ball.playerCollisionCheck(player.getHBoxTopLeft().x, player.getHBoxTopLeft().y, player.getHBoxTopRight().x,player.getHBoxBottomRight().y, player);
        ball.enemyCollisionCheck(enemy.getHBoxTopLeft().x, enemy.getHBoxTopLeft().y, enemy.getHBoxTopRight().x,enemy.getHBoxBottomRight().y, enemy);
        player.update();
        enemy.update();
    }

    public void render(Graphics g) {
        arena.render(g);
        player.render(g);
        enemy.render(g);
        ball.render(g);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
        }

    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }
    public Player getPlayer() {
        return player;
    }
    public Enemy getEnemy(){return enemy;}
}
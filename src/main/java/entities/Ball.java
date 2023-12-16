package entities;

import main.Game;
import main.GamePanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ball extends Entity{
    private float radius = 20;
    private float diameter = radius*2;
    public float dx=2;
    public float dy=2;
    private BufferedImage buffer;
    private int bufferWidth, bufferHeight;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int playerScore = 0;
    private int enemyScore = 0;

    private Random random;
    public Ball(float x, float y) {
        super(x, y,40, 40);
        bouncing();
    }

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(237, 91, 59));
        g2d.fillOval((int) (x - radius), (int) (y - radius), (int) diameter, (int) diameter);
    }

    public void bouncing(){
        buffer = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_ARGB);

        Thread thread = new Thread(()->{
            while (true){
                bufferWidth = screenSize.width ;
                bufferHeight = screenSize.height;

                x = x + dx;
                y = y + dy;

                if (x - radius < 0){
//                    if(dx>2){
//                        dx--;
//                    }
                    dx = -dx;
//                    x = radius;
                    enemyScore++;


                }else if (x + radius > bufferWidth){
//                    if(dx>2){
//                        dy--;
//                    }
                    dx = -dx;
//                    x = bufferWidth - radius;
                    playerScore++;

                }

                if (y - radius< 0) {
                    dy = -dy;

//                    y = radius;
//                    setYDir();
                } else if (y + radius> bufferHeight-80) {
                    dy = -dy;
//                    y = bufferHeight - radius;
                }

                try {
                    Thread.sleep(5);  // Adjust the sleep duration for faster animation
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public float getRadius() {
        return radius;
    }

    public float getDiameter() {
        return diameter;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return x;
    }


    public Rectangle getBound(){
        return new Rectangle((int)x,(int)y,(int)diameter,(int)diameter);
    }

    public void playerCollisionCheck(float hx, float hy, float hx1, float hy1, Player player ){
        if(x-radius < hx1 && x-radius > hx && y > hy && y<hy1){
//            dx++;
            dx = -dx;
            player.setAttacking(true);
        }

    }

    public void enemyCollisionCheck(float hx, float hy, float hx1, float hy1, Enemy enemy ){
        if(x+radius < hx1 && x+radius>hx && y>hy && y<hy1){
//            dx++;
            dx = -dx;
            enemy.setAttacking(true);
        }
    }

    public int getPlayerScore(){
//        System.out.println(playerScore);
        return playerScore;
    }

    public int getEnemyScore() {
//        System.out.println(enemyScore);
        return enemyScore;
    }
}

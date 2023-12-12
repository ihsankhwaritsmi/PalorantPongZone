package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float x,y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;

    protected int offsetX1, offsetX2;

    private Point point;
    public Entity(float x, float y, int width, int height, int offsetX1, int offsetX2) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.offsetX1 = offsetX1;
        this.offsetX2 = offsetX2;
        initHitbox(x,y, width, height);

    }

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
//        initHitbox(x,y, width, height);
    }

    protected void drawHitbox(Graphics g){
        g.setColor(Color.PINK);
        g.drawRect((int)hitbox.x+offsetX1,(int) hitbox.y,(int) hitbox.width-offsetX2 ,(int) hitbox.height);
    }

    private void initHitbox(float x, float y, float width, float height){

        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void updateHitbox(){
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Point getHBoxTopLeft(){
        point = new Point((int)hitbox.getMinX()+offsetX1, (int)hitbox.getMinY());
        return point;
    }
    public Point getHBoxTopRight(){
        point = new Point((int)hitbox.getMaxX()-(offsetX2-offsetX1), (int)hitbox.getMinY());
        return point;
    }
    public Point getHBoxBottomLeft(){
        point = new Point((int)hitbox.getMinX()+offsetX1, (int)hitbox.getMaxY());
        return point;
    }

    public Point getHBoxBottomRight(){
        point = new Point((int)hitbox.getMaxX()-(offsetX2-offsetX1), (int)hitbox.getMaxY());
        return point;
    }






}

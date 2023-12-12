package entities;

import arena.Arena;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import static utils.Constants.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;
    private Arena ar = new Arena();

    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    private int chosenOneIndex;

    public Player(float x, float y,int width, int height, int chosenOneIndex) {
        super(x, y, width, height,90, 170);
        this.chosenOneIndex = chosenOneIndex;
        loadAnimations();
    }

    public void update() {
        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 256, 160, null);
        drawHitbox(g);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }

        }

    }

    private void setAnimation() {
        int startAni = playerAction;

        if (moving)
            playerAction = IDLE;

        else
            playerAction = IDLE;

        if (attacking)
            playerAction = ATTACK_1;

        if (startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
//        Arena ar = new Arena();

        moving = false;

        if (left && !right) {
            if(ar.collisonDetected(getHBoxTopLeft()) || ar.collisonDetected(getHBoxBottomLeft())){
                moving = false;
            }else{
                x -= playerSpeed;
                moving = true;
            }
        } else if (right && !left) {
            if(ar.collisonDetected(getHBoxTopRight()) || ar.collisonDetected(getHBoxBottomRight())){
                moving = false;
            }else{
                x += playerSpeed;
                moving = true;
            }

        }

        if (up && !down) {
            if(y>0){
                y -= playerSpeed;
                moving = true;
            }
        } else if (down && !up) {
            if(y<dimension.getHeight()-230){
                y += playerSpeed;
                moving = true;
            }
        }
    }

    private void loadAnimations() {
        String chosen;
        if(chosenOneIndex==1){
            chosen = "pisi_sprites.png";

        }else{
            chosen = "elsi_sprites.png";
        }
        InputStream is = getClass().getResourceAsStream("/"+chosen);
        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[2][4];
            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 256, j * 160, 256, 160);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }



}

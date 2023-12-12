package main;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import arena.Arena;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;

    private Arena arena;

    public GamePanel(Game game, Arena arena) {
        mouseInputs = new MouseInputs(this);
        this.arena = arena;
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void setPanelSize() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        setPreferredSize(size);
    }

    public void updateGame() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);

    }

    public Game getGame() {
        return game;
    }

    public int getGamePanelWidth(){
        return getWidth();
    }

    public int getGamePanelHeight(){
        return getHeight();
    }
}
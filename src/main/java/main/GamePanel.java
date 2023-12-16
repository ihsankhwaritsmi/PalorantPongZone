package main;
import java.awt.*;
import java.io.File;
import java.io.IOException;


import javax.swing.*;

import arena.Arena;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;
    private Arena arena;
    private JLabel label1;
    private  JLabel label2;
    private Font font;
    private JLabel spacer;

    public GamePanel(Game game, Arena arena) {
        mouseInputs = new MouseInputs(this);
        this.arena = arena;
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
//        addMouseListener(mouseInputs);
//        addMouseMotionListener(mouseInputs);

        try {
            // Create the font
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/res-fonts/Tungsten-Bold.ttf")).deriveFont(64f);

            // Register the font
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        label1 = new JLabel("0");
        spacer = new JLabel("                                ");
        label2 = new JLabel("0");
        label1.setFont(font);
        label2.setFont(font);
        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        add(label1);
        add(spacer);
        add(label2);

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

    public void updateText1(String text){
        label1.setText(text);
    }
    public void updateText2(String text){
        label2.setText(text);
    }
}
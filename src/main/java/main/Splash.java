package main;

import arena.ArenaRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash extends JFrame {
    private Timer timer;
    public Splash(){
        displaySplash();
    }

    public void displaySplash(){
        final JFrame w = new JFrame();
//        w.setSize(950,534);
        w.setLocationRelativeTo(null);
        w.setUndecorated(true);
        w.setExtendedState(JFrame.MAXIMIZED_BOTH);
        w.setVisible(true);


        JPanel panel = new JPanel();
        w.add(panel);
        String imagePath = "src/main/resources/SplashArt.png";

//        JLabel label = new JLabel(new ImageIcon(imagePath));

        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Image resizedImage = originalImage.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel label = new JLabel(resizedIcon);
        panel.add(label);
        panel.setBackground(new Color(4,1,19));
        panel.setForeground(Color.WHITE);

        JProgressBar progress = new JProgressBar(0,300);
        progress.setForeground(new Color(238, 48, 84));
        progress.setBackground(new Color(4,1,19));
        progress.setBorderPainted(false);
        w.add(BorderLayout.PAGE_END,progress);

        new ArenaRenderer();

        w.revalidate();
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = progress.getValue();
                if(x==300){
                    w.dispose();
                    Splash.this.setVisible(true);
                    timer.stop();
//                    new Game();
                    new Contract();
                }else{
                    progress.setValue(x+4);
                }
            }
        });

        timer.start();

    }

}

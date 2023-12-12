package arena;

import entities.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.AbstractLayoutCache;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Arena extends JPanel {

    private JLabel label;
    private BufferedImage combinedImage;
    private BufferedImage blackAndWhite;


    public Arena(){
        gatau();
    }
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private ImageIcon img = new ImageIcon("src/main/maps/the-map.png");
    private Image imger = img.getImage();
    public JLabel getLabel() {
        return label;
    }

    private void gatau(){
//        initPathImage();
        String imgpath1 = "src/main/maps/collisionblock.png";
        String imgpath2 = "src/main/maps/the-map.png";

        try{
            this.blackAndWhite = ImageIO.read(new File(imgpath1));
            BufferedImage illustration = ImageIO.read(new File(imgpath2));

            this.combinedImage = new BufferedImage(blackAndWhite.getWidth(),blackAndWhite.getHeight(), BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g = combinedImage.createGraphics();
//            g.drawImage(blackAndWhite, 0, 0, null);
//            g.drawImage(illustration, 0, 0, null);
//            g.dispose();



            this.label = new JLabel(new ImageIcon(combinedImage));

//            this.label.addMouseMotionListener(new MouseMotionAdapter() {
//                public void mouseMoved(MouseEvent e) {
//                    int x = e.getX();
//                    int y = e.getY();
//                    Color color = new Color(blackAndWhite.getRGB(x, y));
//
//                    if (color.equals(Color.BLACK)) {
//                        System.out.println("This is a collision block.");
//                    }else{
//                        System.out.println("This is not a collision block.");
//                    }
//
//
//                }
//            });



//            JFrame frame = new JFrame("Arena");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.getContentPane().add(label); // Add your JLabel to the JFrame
//            frame.pack();
//            frame.setVisible(true);


        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void render(Graphics g){

        g.drawImage(imger,0,0, null);
    }

    public boolean collisonDetected(Point p){
        Color color = new Color(blackAndWhite.getRGB(p.x,p.y));
        if (color.equals(Color.BLACK)) {
            return true;
        }
        return false;
    }

}

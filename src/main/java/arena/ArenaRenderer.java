package arena;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ArenaRenderer {

    private Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

    public ArenaRenderer(){
        initPathImage();
    }


    public void initPathImage(){
        String imgPath1 = "src/main/maps/sillhouette.png";
        String imgPath2 = "src/main/maps/the-thing.png";
        try{
            imageRescaler(imgPath1, "collisionblock.png");
            imageRescaler(imgPath2, "the-map.png");
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void imageRescaler(String path, String destName) throws IOException {

        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();

        Image scaledimage = image.getScaledInstance(screensize.width, screensize.height, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledimage);
        BufferedImage bufferedImage = new BufferedImage(screensize.width, screensize.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(scaledimage, 0, 0,null);
        g.dispose();

        File output = new File("src/main/maps/"+destName);
        ImageIO.write(bufferedImage,"png",output);
    }

    public static void main(String[] args) {
        new ArenaRenderer();
    }
}

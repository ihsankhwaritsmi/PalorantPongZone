package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Contract {
    private JFrame frame;
    private JLabel imageLabel;
    private List<String> imagePaths;
    private ArrayList<ImageIcon> imgList = new ArrayList<>();
    private int currentIndex;

    private int chosenOne;

    public Contract() {
        frame = new JFrame("Image Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(13, 13, 14));
        frame.add(panel);

        imageLabel = new JLabel();
        frame.getContentPane().add(imageLabel, BorderLayout.CENTER);


        JButton prevButton = new JButton("Previous");
        prevButton.setBorderPainted(false);
        prevButton.setBackground(new Color(63, 56, 139));
        prevButton.setForeground(Color.WHITE);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousImage();
            }
        });

        JButton nextButton = new JButton("Next");
        nextButton.setBorderPainted(false);
        nextButton.setBackground(new Color(58, 194, 113));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });

        JButton lockButton = new JButton("Lock!");
        lockButton.setBorderPainted(false);
        lockButton.setBackground(new Color(255, 70, 84));
        lockButton.setForeground(Color.BLACK);
        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Game(getChosenOne());
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(prevButton);
        buttonPanel.add(lockButton);
        buttonPanel.add(nextButton);



        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);


        imagePaths = new ArrayList<>();
        // Add image paths to the list (update accordingly)

        imagePaths.add("src/main/res/4.png");
        imagePaths.add("src/main/res/1.png");
        imagePaths.add("src/main/res/2.png");
        imagePaths.add("src/main/res/3.png");
//        imagePaths.add("src/main/res/4.png");
//        imagePaths.add("src/main/res/5.png");
        imagePaths.add("src/main/res/6.png");
        imagePaths.add("src/main/res/7.png");
        imagePaths.add("src/main/res/8.png");
        imagePaths.add("src/main/res/9.png");
        imagePaths.add("src/main/res/10.png");

//        ImageIcon element = new ImageIcon();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        for (String el : imagePaths){
            try{
                BufferedImage image = ImageIO.read(new File(el));
                Image scaledImage = image.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImage);
                imgList.add(icon);
            }catch (IOException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }



        currentIndex = 0;
        showImage();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showImage() {

        imageLabel.setIcon(imgList.get(currentIndex));
    }


    private void showNextImage() {
        int delay = 40; // Reduced delay to 10 milliseconds
        if(currentIndex==8){

        }else{
            Timer timer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Show the next image after the delay

                    currentIndex = (currentIndex + 1) % imagePaths.size();
                    showImage();

                    // Stop the Timer if we have reached the last image
                    if (currentIndex == imagePaths.size() - 1) {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });

            // Start the Timer
            timer.start();
            setChosenOne(1);
        }

    }
    private void showPreviousImage() {

        int delay =  40; // Reduced delay to 10 milliseconds
        if(currentIndex==0){

        }else{
            Timer timer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Show the next image after the delay
                    currentIndex = (currentIndex - 1) % imagePaths.size();
                    showImage();

                    // Stop the Timer if we have reached the last image
                    if (currentIndex == 0) {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.start();
            setChosenOne(0);

        }

    }

    public void setChosenOne(int chosenOne) {
        this.chosenOne = chosenOne;
    }

    public int getChosenOne() {
        return chosenOne;
    }
}

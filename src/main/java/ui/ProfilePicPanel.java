package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// This class is in the Frameworks & Drivers layer of clean architecture.

/**
 * This creates the panel that will display the user's profile picture and allow them to upload a new picture.
 */
public class ProfilePicPanel extends JPanel implements ActionListener {
    private final SpringLayout LAYOUT;
    private JLabel image;
    private JButton imgUpload;

    /**
     * The constructor for the ProfilePicPanel which sets up the panel and layout.
     */
    public ProfilePicPanel() {
        this.LAYOUT = new SpringLayout();
        setUpPanel();
        setUpLayout();
    }

    /**
     * This sets up the profile picture GUI by creating the JLabel and JButton necessary for the profile picture and
     * adding them to the panel.
     */
    private void setUpPanel() {
        this.setPreferredSize(new Dimension(300, 150));
        this.setLayout(LAYOUT);

        this.image = new JLabel();
        this.add(image);

        this.imgUpload = new JButton("Upload image");
        imgUpload.addActionListener(this);
        this.add(imgUpload);

        // Default Profile Picture
        ImageIcon imageIcon = new ImageIcon("images/profile_picture.png");
        Image img = imageIcon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(img));
        this.add(image);
    }

    /**
     * This aligns the JLabel and JButton so that they are placed correctly within the ProfilePicPanel.
     */
    private void setUpLayout() {
        LAYOUT.putConstraint(SpringLayout.WEST, image, 0, SpringLayout.WEST, this);
        LAYOUT.putConstraint(SpringLayout.WEST, imgUpload, 20, SpringLayout.EAST, image);

        LAYOUT.putConstraint(SpringLayout.NORTH, image, 0, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.VERTICAL_CENTER, imgUpload, 0, SpringLayout.VERTICAL_CENTER, image);
    }

    /**
     * This updates the profile picture if the upload button is pressed and an image file is chosen and opened.
     * @param e The event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == imgUpload) {
            final JFileChooser imgFiles = new JFileChooser();
            int returnVal = imgFiles.showOpenDialog(ProfilePicPanel.this);
            String fileName;
            String filePath;
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fileName = imgFiles.getSelectedFile().getName();
                filePath = imgFiles.getCurrentDirectory().toString();
                try {
                    BufferedImage profilePic = ImageIO.read(new File(filePath + "/" + fileName));
                    Image scaled_img = profilePic.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
                    image.setIcon(new ImageIcon(scaled_img));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}

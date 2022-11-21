package UI;

import entities.Review;
import entities.User;
import useCase.displayProfile.ProfilePresenter;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

// This class is in the Frameworks & Drivers layer of clean architecture.
// TODO Should I allow a user to update their username and email
// TODO also must plugin to presenter and decide if we will allow people to
//  choose profile pictures, right now I have a default picture set.

public class ProfilePage extends Page {
    private final String PAGETITLE = "Profile Page";
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private SpringLayout LAYOUT;
    private String username;
    private String email;
    private int rating;
    private ArrayList<Object> reviews;
    private ArrayList<Object> listings;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel ratingLabel;
    private JLabel titleLabel;
    private ImageIcon imageIcon;
    private JLabel image;
    //private ArrayList<JPanel> reviewsPanel;
    //private ArrayList<JPanel> listingsPanel;


    public ProfilePage(String username, String email, int rating, ArrayList<Object> reviews,
                       ArrayList<Object> listings) {
        super("Scamazon.ca");
        this.LAYOUT = new SpringLayout();
        this.username = username;
        this.email = email;
        this.rating = rating;
        this.reviews = reviews;
        this.listings = listings;
        this.imageIcon = new ImageIcon("profile_picture.png");
        setUpPanel();
        setUpLayout();
    }

    private void setUpPanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(LAYOUT);

        titleLabel = new JLabel(PAGETITLE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        this.add(titleLabel);

        usernameLabel = new JLabel("Username: " + username);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        this.add(usernameLabel);

        emailLabel = new JLabel("Email: " + email);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        this.add(emailLabel);

        ratingLabel = new JLabel("Rating: " + String.valueOf(rating));
        ratingLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        this.add(ratingLabel);

        Image img = imageIcon.getImage();
        Image imgscale = img.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(imgscale);
        image = new JLabel(scaledImage);
        this.add(image);
    }




        // TODO NEED REVIEW AND LISTING PANEL GUIS
        // ArrayList<JPanel> reviewsPanel = new ArrayList<>();
        // for (Object i : reviews) {
        //     reviewsPanel.add(i.createReviewPanel);
        // }
        // ArrayList<JPanel> listingsPanel = new ArrayList<>();
        // for (Object i : listings) {
        //     reviewsPanel.add(i.createListingPanel);
        // }
        // Make it scrollable:
        // JScrollPane scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        //       JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private void setUpLayout() {
        // Aligning everything to the middle:
        LAYOUT.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER,
                this);
        LAYOUT.putConstraint(SpringLayout.HORIZONTAL_CENTER, image, 0, SpringLayout.HORIZONTAL_CENTER,
                titleLabel);
        LAYOUT.putConstraint(SpringLayout.HORIZONTAL_CENTER, usernameLabel, 0, SpringLayout.HORIZONTAL_CENTER,
                titleLabel);
        LAYOUT.putConstraint(SpringLayout.HORIZONTAL_CENTER, emailLabel, 0, SpringLayout.HORIZONTAL_CENTER,
                titleLabel);
        LAYOUT.putConstraint(SpringLayout.HORIZONTAL_CENTER, ratingLabel, 0, SpringLayout.HORIZONTAL_CENTER,
                titleLabel);

        // Aligning everything vertically:
        LAYOUT.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, image, 30, SpringLayout.SOUTH, titleLabel);
        LAYOUT.putConstraint(SpringLayout.NORTH, usernameLabel, 30, SpringLayout.SOUTH, image);
        LAYOUT.putConstraint(SpringLayout.NORTH, emailLabel, 30, SpringLayout.SOUTH, usernameLabel);
        LAYOUT.putConstraint(SpringLayout.NORTH, ratingLabel, 30, SpringLayout.SOUTH, emailLabel);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ProfilePage("EthanS", "ethanstark24@hotmail.com", 10,
                new ArrayList<>(), new ArrayList<>());
    }
}


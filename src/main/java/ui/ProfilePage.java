package ui;

import database.AddReview;
import database.ReviewDatabaseGateway;
import use_case.display_profile.*;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;

import static main.Main.getCurrentUser;

// This class is in the Frameworks & Drivers layer of clean architecture.
// TODO Should I allow a user to update their username and email
// right now I have a default profile picture set.

/**
 * This is the GUI for the user's profile page which creates a request for the user's profile and displays the page.
 */
public class ProfilePage extends Page {
    private final String PAGE_TITLE;
    private final SpringLayout LAYOUT;
    private final String username;
    private final String email;
    private final double rating;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel ratingLabel;
    private JLabel titleLabel;
    private JLabel image;
    private JPanel reviewForm;


    /**
     * The ProfilePage constructor which creates a request and sets the necessary attributes from the
     * response model received.
     * @param pageTitle The title of the application.
     */
    public ProfilePage(String pageTitle) {
        super("Scamazon.ca");
        this.LAYOUT = new SpringLayout();
        this.PAGE_TITLE = pageTitle;

        // TODO for now I use the current user to get the username
        this.username = getCurrentUser().getUsername();

        // Make a request for the user's profile.
        ReviewDatabaseGateway profileGateway = new AddReview();
        ProfileOutputBoundary profilePresenter = new ProfilePresenter();
        ProfileInputBoundary inputBoundary = new ProfileInteractor(profileGateway, profilePresenter);
        ProfileController profileController = new ProfileController(inputBoundary);
        // TODO add a try-catch in case IOException is thrown if user doesn't exist
        ProfileResponseModel user = profileController.createRequest(this.username);

        this.email = user.getEmail();
        this.rating = user.getRating();

        setUpPanel();
        setUpLayout();
    }

    /**
     * This sets up the ProfilePage GUI by creating all the necessary JLabels, JButtons, and JPanels and
     * adding them to the ProfilePage.
     */
    private void setUpPanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(LAYOUT);

        titleLabel = new JLabel(PAGE_TITLE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        this.add(titleLabel);

        usernameLabel = new JLabel("Username: " + username);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(usernameLabel);

        emailLabel = new JLabel("Email: " + email);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(emailLabel);

        ratingLabel = new JLabel("Rating: " + rating);
        ratingLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(ratingLabel);

        reviewForm = new ProfileReviewPanel();
        this.add(reviewForm);

        ImageIcon imageIcon = new ImageIcon("images/profile_picture.png");
        Image img = imageIcon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH);
        image = new JLabel(new ImageIcon(img));
        this.add(image);
    }

    /**
     * This aligns each JLabel, JButton, and JPanel so that they are placed correctly within the ProfilePage.
     */
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
        LAYOUT.putConstraint(SpringLayout.HORIZONTAL_CENTER, reviewForm, 0, SpringLayout.HORIZONTAL_CENTER,
                titleLabel);

        // Aligning everything vertically:
        LAYOUT.putConstraint(SpringLayout.NORTH, titleLabel, 160, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, image, 20, SpringLayout.SOUTH, titleLabel);
        LAYOUT.putConstraint(SpringLayout.NORTH, usernameLabel, 20, SpringLayout.SOUTH, image);
        LAYOUT.putConstraint(SpringLayout.NORTH, emailLabel, 20, SpringLayout.SOUTH, usernameLabel);
        LAYOUT.putConstraint(SpringLayout.NORTH, ratingLabel, 20, SpringLayout.SOUTH, emailLabel);
        LAYOUT.putConstraint(SpringLayout.NORTH, reviewForm, 20, SpringLayout.SOUTH, ratingLabel);
    }
}
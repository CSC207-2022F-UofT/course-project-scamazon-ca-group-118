package UI;

import database.GetUser;
import database.ReviewDatabaseGateway;
import useCase.displayProfile.*;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;

import static Main.Main.getCurrentUser;

// This class is in the Frameworks & Drivers layer of clean architecture.

/**
 * This is the GUI for the user's profile page which creates a request for the user's profile and displays the page.
 */
public class ProfilePage extends Page {
    private final String PAGETITLE;
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private final SpringLayout LAYOUT;
    private String username;
    private String email;
    private double rating;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel ratingLabel;
    private JLabel titleLabel;
    private JPanel reviewForm;
    private JPanel profilePicPanel;
    private JLabel errorMessage = new JLabel();

    /**
     * The ProfilePage constructor which creates a request and sets the necessary attributes from the
     * response model received.
     * @param pageTitle The title of the application.
     */
    public ProfilePage(String pageTitle) {
        super("Scamazon.ca");
        this.LAYOUT = new SpringLayout();
        this.PAGETITLE = pageTitle;
        this.username = getCurrentUser().getUsername();

        // Make a request for the user's profile.
        ReviewDatabaseGateway profileGateway = new GetUser();
        ProfileOutputBoundary profilePresenter = new ProfilePresenter();
        ProfileInputBoundary inputBoundary = new ProfileInteractor(profileGateway, profilePresenter);
        ProfileController profileController = new ProfileController(inputBoundary);
        try {
            ProfileResponseModel user = profileController.createRequest(username);
            this.email = user.getEmail();
            this.rating = user.getRating();
        } catch (NoSuchUser exception) {
            String error = exception.getMessage();
            errorMessage.setText(error);
            this.username = "";
            this.email = "";
            this.rating = 0;
        }
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

        titleLabel = new JLabel(PAGETITLE);
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

        this.add(errorMessage);

        reviewForm = new ProfileReviewPanel();
        this.add(reviewForm);

        profilePicPanel = new ProfilePicPanel();
        this.add(profilePicPanel);
    }

    /**
     * This aligns each JLabel, JButton, and JPanel so that they are placed correctly within the ProfilePage.
     */
    private void setUpLayout() {
        // Aligning everything to the middle:
        LAYOUT.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER,
                this);
        LAYOUT.putConstraint(SpringLayout.HORIZONTAL_CENTER, errorMessage, 0, SpringLayout.HORIZONTAL_CENTER,
                titleLabel);
        LAYOUT.putConstraint(SpringLayout.HORIZONTAL_CENTER, profilePicPanel, 84, SpringLayout.HORIZONTAL_CENTER,
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
        LAYOUT.putConstraint(SpringLayout.NORTH, errorMessage, 0, SpringLayout.SOUTH, titleLabel);
        LAYOUT.putConstraint(SpringLayout.NORTH, profilePicPanel, 0, SpringLayout.SOUTH, errorMessage);
        LAYOUT.putConstraint(SpringLayout.NORTH, usernameLabel, 5, SpringLayout.SOUTH, profilePicPanel);
        LAYOUT.putConstraint(SpringLayout.NORTH, emailLabel, 20, SpringLayout.SOUTH, usernameLabel);
        LAYOUT.putConstraint(SpringLayout.NORTH, ratingLabel, 20, SpringLayout.SOUTH, emailLabel);
        LAYOUT.putConstraint(SpringLayout.NORTH, reviewForm, 20, SpringLayout.SOUTH, ratingLabel);
    }
}
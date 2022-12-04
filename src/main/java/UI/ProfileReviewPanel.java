package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the panel that is used to review another user from the profile page.
 */
public class ProfileReviewPanel extends JPanel implements ActionListener {
    private final SpringLayout layout;
    private JTextField usernameReviewed;
    private JPanel usernameInfo;
    private JPanel ratingInfo;
    private int rating = 0;
    private JLabel ratingChosen;
    private JButton submit;
    private JButton reset;
    private JLabel successfulReview;
    private JLabel errorMessage;

    /**
     * The constructor for the ProfileReviewPanel.
     */
    public ProfileReviewPanel() {
        this.layout = new SpringLayout();
        setUpPanel();
        setUpLayout();
    }

    /**
     * This sets up the ProfileReviewPanel by creating all the necessary JLabels, JButtons, and JPanels and
     * adding them to the ProfileReviewPanel.
     */
    private void setUpPanel() {
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(640, 400));

        JLabel usernameLabel = new JLabel("Username of the user you wish to review: ");
        usernameReviewed = new JTextField(15);
        usernameInfo = new JPanel();
        usernameInfo.add(usernameLabel);
        usernameInfo.add(usernameReviewed);

        ratingChosen = new JLabel("");
        successfulReview = new JLabel("");
        errorMessage = new JLabel("");

        JLabel ratingLabel = new JLabel("Give them a rating between 1 and 5: ");
        RatingButton rating1 = new RatingButton("1");
        RatingButton rating2 = new RatingButton("2");
        RatingButton rating3 = new RatingButton("3");
        RatingButton rating4 = new RatingButton("4");
        RatingButton rating5 = new RatingButton("5");

        rating1.addActionListener(this);
        rating2.addActionListener(this);
        rating3.addActionListener(this);
        rating4.addActionListener(this);
        rating5.addActionListener(this);

        ratingInfo = new JPanel();
        ratingInfo.add(ratingLabel);
        ratingInfo.add(rating1);
        ratingInfo.add(rating2);
        ratingInfo.add(rating3);
        ratingInfo.add(rating4);
        ratingInfo.add(rating5);

        submit = new JButton("Submit review");
        submit.addActionListener(this);

        reset = new JButton("Reset");
        reset.addActionListener(this);

        this.add(usernameInfo);
        this.add(ratingInfo);
        this.add(ratingChosen);
        this.add(submit);
        this.add(reset);
        this.add(successfulReview);
        this.add(errorMessage);
    }

    /**
     * This aligns each JLabel, JButton, and JPanel so that they are placed correctly within the ProfileReviewPanel.
     */
    private void setUpLayout() {
        layout.putConstraint(SpringLayout.NORTH, usernameInfo, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, ratingInfo, 10, SpringLayout.SOUTH, usernameInfo);
        layout.putConstraint(SpringLayout.NORTH, ratingChosen, 0, SpringLayout.SOUTH, ratingInfo);
        layout.putConstraint(SpringLayout.WEST, ratingChosen, 5, SpringLayout.WEST, ratingInfo);
        layout.putConstraint(SpringLayout.NORTH, submit, 30, SpringLayout.SOUTH, ratingInfo);
        layout.putConstraint(SpringLayout.NORTH, reset, 30, SpringLayout.SOUTH, ratingInfo);
        layout.putConstraint(SpringLayout.WEST, reset, 20, SpringLayout.EAST, submit);
        layout.putConstraint(SpringLayout.NORTH, successfulReview, 15, SpringLayout.SOUTH, submit);
        layout.putConstraint(SpringLayout.WEST, successfulReview, 5, SpringLayout.WEST, ratingInfo);
        layout.putConstraint(SpringLayout.NORTH, errorMessage, 15, SpringLayout.SOUTH, submit);
        layout.putConstraint(SpringLayout.WEST, errorMessage, 5, SpringLayout.WEST, ratingInfo);
    }

    /**
     * This updates the panel depending on what event occurred.
     * @param e The event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof RatingButton) {
            String num = ((RatingButton) e.getSource()).getText();
            rating = Integer.parseInt(num);
            ratingChosen.setText("Rating set to " + num + ".");
            errorMessage.setText("");
            successfulReview.setText("");
        } else if (e.getSource() == reset) {
            rating = 0;
            usernameReviewed.setText("");
            ratingChosen.setText("");
            errorMessage.setText("");
            successfulReview.setText("");
        } else if (e.getSource() == submit) {
            String username = usernameReviewed.getText();
            if (username.equals("")) {
                successfulReview.setText("");
                errorMessage.setText("You must enter a username.");
            } else if (rating == 0) {
                successfulReview.setText("");
                errorMessage.setText("You must pick a rating.");
            } else {
                /* TODO Update User with username's reviews.
                    Need to add these methods to ReviewDatabaseGateway and create a review request.
                    void updateUserReviews(String username);
                    boolean checkUserWithUsername(String username) throws IOException; */
                rating = 0;
                errorMessage.setText("");
                usernameReviewed.setText("");
                ratingChosen.setText("");
                successfulReview.setText("Review successful.");
            }
        }
    }
}

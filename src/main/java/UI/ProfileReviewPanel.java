package UI;

import Main.Main;
import forms.ReviewForm;
import useCase.writeReview.ReviewPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// This class is in the Frameworks & Drivers layer of clean architecture.

/**
 * This is the GUI that is used to review another user.
 */
public class ProfileReviewPanel extends JPanel implements ActionListener {
    private final SpringLayout LAYOUT;
    private JTextField usernameReviewed;
    private JPanel usernameInfo;
    private JPanel ratingInfo;
    private int rating = 0;
    private JLabel ratingChosen;
    private JButton submit;
    private JButton reset;
    private JLabel message;

    /**
     * The constructor for the ProfileReviewPanel.
     */
    public ProfileReviewPanel() {
        this.LAYOUT = new SpringLayout();
        setUpPanel();
        setUpLayout();
    }

    /**
     * This sets up the ProfileReviewPanel by creating all the necessary JLabels, JButtons, and JPanels and
     * adding them to the ProfileReviewPanel.
     */
    private void setUpPanel() {
        this.setLayout(LAYOUT);
        this.setPreferredSize(new Dimension(640, 400));

        JLabel usernameLabel = new JLabel("Username of the user you wish to review: ");
        usernameReviewed = new JTextField(15);
        usernameInfo = new JPanel();
        usernameInfo.add(usernameLabel);
        usernameInfo.add(usernameReviewed);

        ratingChosen = new JLabel("");
        message = new JLabel("");

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
        this.add(message);
    }

    /**
     * This aligns each JLabel, JButton, and JPanel so that they are placed correctly within the ProfileReviewPanel.
     */
    private void setUpLayout() {
        LAYOUT.putConstraint(SpringLayout.NORTH, usernameInfo, 0, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, ratingInfo, 10, SpringLayout.SOUTH, usernameInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, ratingChosen, 0, SpringLayout.SOUTH, ratingInfo);
        LAYOUT.putConstraint(SpringLayout.WEST, ratingChosen, 5, SpringLayout.WEST, ratingInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, submit, 30, SpringLayout.SOUTH, ratingInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, reset, 30, SpringLayout.SOUTH, ratingInfo);
        LAYOUT.putConstraint(SpringLayout.WEST, reset, 20, SpringLayout.EAST, submit);
        LAYOUT.putConstraint(SpringLayout.NORTH, message, 15, SpringLayout.SOUTH, submit);
        LAYOUT.putConstraint(SpringLayout.WEST, message, 5, SpringLayout.WEST, ratingInfo);
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
            message.setText("");
        } else if (e.getSource() == reset) {
            rating = 0;
            usernameReviewed.setText("");
            ratingChosen.setText("");
            message.setText("");
        } else if (e.getSource() == submit) {
            String reviewerUsername = Main.getCurrentUser().getUsername();
            String reviewedUsername = usernameReviewed.getText();
            if (reviewerUsername.equals(reviewedUsername)) {
                message.setText("You can't review yourself.");
            } else {
                ReviewForm form = new ReviewForm(reviewerUsername, reviewedUsername, rating);
                try {
                    message.setText(new ReviewPresenter(form).getMessage());
                    rating = 0;
                    usernameReviewed.setText("");
                    ratingChosen.setText("");
                } catch (IOException exception) {
                    // TODO I'm not sure yet if this try-catch is necessary.
                }
            }
        }
    }
}

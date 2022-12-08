package ui;

import javax.swing.*;

/**
 * This is used to create a JButton used for rating a user.
 */
public class RatingButton extends JButton {

    /**
     * The constructor for the RatingPanel.
     * @param rating The string corresponding to the value of this RatingButton once clicked.
     */
    public RatingButton(String rating) {
        super(rating);
    }
}

package UI;

import javax.swing.*;

/**
 * This is used to create a JPanel which is used for rating users.
 */
public class RatingPanel extends JPanel {

    /**
     * The constructor for the RatingPanel.
     * @param ratingLabel The label that will go beside all the buttons.
     * @param rating1 The button that corresponds to a rating of 1.
     * @param rating2 The button that corresponds to a rating of 2.
     * @param rating3 The button that corresponds to a rating of 3.
     * @param rating4 The button that corresponds to a rating of 4.
     * @param rating5 The button that corresponds to a rating of 5.
     */
    public RatingPanel(JLabel ratingLabel, JButton rating1, JButton rating2, JButton rating3, JButton rating4,
                       JButton rating5) {
        this.add(ratingLabel);
        this.add(rating1);
        this.add(rating2);
        this.add(rating3);
        this.add(rating4);
        this.add(rating5);
    }
}

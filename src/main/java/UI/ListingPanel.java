package UI;

import javax.swing.*;

public class ListingPanel extends JPanel {

    public ListingPanel(JButton title, JLabel price, JLabel images, JLabel description, JLabel date) {
        this.add(title);
        this.add(price);
        this.add(images);
        this.add(description);
        this.add(date);
    }
}

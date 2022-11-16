package pages;

import javax.swing.*;

public class LabelButtonPanel extends JPanel {

    public LabelButtonPanel(JLabel label, JPanel buttonPanel) {
        this.add(label);
        this.add(buttonPanel);
    }
}

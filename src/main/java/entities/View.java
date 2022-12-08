package entities;

import main.Main;
import ui.NavBar;
import ui.Page;


import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    /**
     * The NavBar to be displayed at the top of each View window
     */
    private final NavBar NAV_BAR;
    /**
     * The Page that holds all components to be displayed in this View Frame, including the navBar
     */
    private Page currentPage;

    public View() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * The layout manager for this View
         */
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        NAV_BAR = new NavBar();
        currentPage = Main.getCurrentPage();

        layout.putConstraint(SpringLayout.NORTH, currentPage, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, currentPage, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, NAV_BAR, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, NAV_BAR, 0, SpringLayout.WEST, this);

        this.add(NAV_BAR);
        this.add(currentPage);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Updates the View Frame with any changes to the currentPage and/or navBar
     */
    public void update() {
        this.remove(currentPage);
        currentPage = Main.getCurrentPage();
        NAV_BAR.updateNavBar();
        this.add(Main.getCurrentPage());
        this.setVisible(true);
    }

}

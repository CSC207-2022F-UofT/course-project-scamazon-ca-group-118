package entities;

import Main.Main;
import UI.NavBar;
import UI.Page;


import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    /**
     * The NavBar to be displayed at the top of each View window
     */
    private NavBar navBar;
    /**
     * The Page that holds all components to be displayed in this View Frame, including the navBar
     */
    private Page currentPage;

    /**
     * The layout manager for this View
     */
    private SpringLayout layout;

    public View() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layout = new SpringLayout();
        this.setLayout(layout);

        navBar = new NavBar();
        currentPage = Main.getCurrentPage();

        layout.putConstraint(SpringLayout.NORTH, currentPage, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, currentPage, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, navBar, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, navBar, 0, SpringLayout.WEST, this);

        this.add(navBar);
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
        navBar.updateNavBar();
        this.add(Main.getCurrentPage());
        this.setVisible(true);
    }

}

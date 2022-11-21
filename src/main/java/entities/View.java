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
    static NavBar navBar;
    /**
     * The Page that holds all components to be displayed in this View Frame, including the navBar
     */
    static Page currentPage;

    public View() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        navBar = new NavBar();
        currentPage = Main.getCurrentPage();
        currentPage.add(navBar);

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
        currentPage.add(navBar);
        this.add(currentPage);
        this.pack();
        this.setVisible(true);

    }

}

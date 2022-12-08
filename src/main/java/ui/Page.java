package ui;

import javax.swing.*;


public abstract class Page extends JPanel {
    public final int WIDTH = 1280;
    public final int HEIGHT = 720;

    private String title;

    public Page(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // TODO: missing submit
}

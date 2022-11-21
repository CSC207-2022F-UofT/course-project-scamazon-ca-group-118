package UI;

import javax.swing.*;


public abstract class Page extends JPanel {

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

package pages;

import javax.swing.*;

public abstract class Page extends JFrame{
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

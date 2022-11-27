package forms;

import javax.swing.*;
import java.io.IOException;

public abstract class Form extends JFrame {
    private String title;

    public Form(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    abstract boolean validateForm() throws IOException;
    abstract void submitForm() throws IOException;
}

package forms;

public abstract class Form {
    private String title;

    public Form(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle (String title) {
        this.title = title;
    }

    protected abstract boolean validateForm();
    protected abstract void submitForm();
}

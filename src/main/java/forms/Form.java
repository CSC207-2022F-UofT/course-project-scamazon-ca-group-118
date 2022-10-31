package forms;

public abstract class Form {
    private String title;
    private String submit;

    public String getTitle() {
        return this.title;
    }
    public void setTitle (String title) {
        this.title = title;
    }
    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }
    // TODO: Implement
    abstract boolean validateForm();
    abstract void submitForm();
}

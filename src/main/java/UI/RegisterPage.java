package UI;

import forms.RegisterForm;

public class RegisterPage extends Page {

    //Register form will be embedded inside
    private RegisterForm form;

    public RegisterPage(String title, RegisterForm form) {
        super(title);
        this.form = form;
        //TODO implement this so that I can somehow get a JPanel with graphics from View
    }

    //submit page override
}

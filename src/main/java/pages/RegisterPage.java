package pages;

import forms.RegisterForm;

public class RegisterPage extends Page{

    //Register form will be embedded inside
    private RegisterForm form;

    public RegisterPage(String title, RegisterForm form){
        super(title);
        this.form = form;
    }

    //submit page override
}

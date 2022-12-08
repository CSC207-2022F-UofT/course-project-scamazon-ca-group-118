package use_case.add_to_cart;

import forms.AddToCartForm;

import java.io.IOException;

public class AddToCartPresenter {
    private String message;

    public AddToCartPresenter(AddToCartForm addToCartForm) throws IOException {
        this.message = addToCartForm.getMessage();
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

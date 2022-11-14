package addToCart;

import forms.AddToCartForm;

public class AddToCartPresenter {
    private String message;

    public AddToCartPresenter(AddToCartForm addToCartForm) {
        this.message = addToCartForm.getMessage();
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

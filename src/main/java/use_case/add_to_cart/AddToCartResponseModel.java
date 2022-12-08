package use_case.add_to_cart;

import java.io.IOException;

public class AddToCartResponseModel {
    private String message;

    public AddToCartResponseModel(AddToCartRequestModel requestModel) throws IOException {
        AddToCartInteractor interactor = new AddToCartInteractor(requestModel);
        this.message = interactor.getMessage();

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

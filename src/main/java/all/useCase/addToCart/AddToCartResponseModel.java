package all.useCase.addToCart;

public class AddToCartResponseModel {
    private String message;

    public AddToCartResponseModel(AddToCartRequestModel requestModel) {
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

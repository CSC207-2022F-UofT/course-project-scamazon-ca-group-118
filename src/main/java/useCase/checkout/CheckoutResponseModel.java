package useCase.checkout;


import java.io.IOException;

public class CheckoutResponseModel {
    private String message;

    /**
     * The constructor for CheckoutResponseModel
     *
     * @param requestModel the CheckoutRequestModel that will be used as an argument for the CheckoutInteractor
     *                     that this CheckoutResponseModel retrieves data from
     */
    public CheckoutResponseModel(CheckoutRequestModel requestModel) throws IOException {
        CheckoutInteractor interactor = new CheckoutInteractor(requestModel.getUsername());
        interactor.removeListings();
    }
}

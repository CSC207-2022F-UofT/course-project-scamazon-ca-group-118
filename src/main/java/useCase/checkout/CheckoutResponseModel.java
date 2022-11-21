package useCase.checkout;

<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> origin/main

public class CheckoutResponseModel {
    private String message;

    /**
     * The constructor for CheckoutResponseModel
     *
     * @param requestModel the CheckoutRequestModel that will be used as an argument for the CheckoutInteractor
     *                     that this CheckoutResponseModel retrieves data from
     */
<<<<<<< HEAD
    public CheckoutResponseModel(CheckoutRequestModel requestModel) throws IOException {
        CheckoutInteractor interactor = new CheckoutInteractor(requestModel);
=======
    public CheckoutResponseModel(CheckoutRequestModel requestModel) {
        CheckoutInteractor interactor = new CheckoutInteractor(requestModel.getUsername());
        interactor.removeListings();
>>>>>>> origin/main
    }
}

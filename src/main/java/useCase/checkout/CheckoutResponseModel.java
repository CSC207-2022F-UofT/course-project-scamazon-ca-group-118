package useCase.checkout;


import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public class CheckoutResponseModel {
    private String message;

    /**
     * The constructor for CheckoutResponseModel
     *
     * @param requestModel the CheckoutRequestModel that will be used as an argument for the CheckoutInteractor
     *                     that this CheckoutResponseModel retrieves data from
     */
    public CheckoutResponseModel(CheckoutRequestModel requestModel) throws IOException, CsvException {
        CheckoutInteractor interactor = new CheckoutInteractor(requestModel);
        this.message = interactor.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

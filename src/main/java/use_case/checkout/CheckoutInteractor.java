package use_case.checkout;


import com.opencsv.exceptions.CsvException;
import database.DatabaseController;
import entities.User;
import main.Main;

import java.io.IOException;

/**
 * The CheckoutInteractor class takes in a CheckoutRequestModel and generates the necessary data for the
 * CheckoutResponseModel
 */
public class CheckoutInteractor {
    private User buyer;

    /**
     * The constructor for the CheckoutInteractor class
     *
     * @param requestModel the request model that's data will be manipulated
     */
    public CheckoutInteractor(CheckoutRequestModel requestModel) throws IOException {
        this.buyer = requestModel.getBuyer();
    }

    public String getMessage() throws IOException, CsvException {
        this.removeListings();
        return "You have successfully checked out";
    }

    public void removeListings() throws IOException, CsvException {
        DatabaseController db = new DatabaseController();
        db.checkoutRemoveListings();
        Main.setCurrentUser(db.getUserWithUsername(buyer.getUsername()));
    }
}

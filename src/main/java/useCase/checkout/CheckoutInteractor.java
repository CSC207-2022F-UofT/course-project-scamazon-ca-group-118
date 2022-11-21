package useCase.checkout;

import database.DatabaseController;
import database.GetUser;
import entities.User;
import entities.Cart;
import entities.Listing;

import java.io.IOException;

/**
 * The CheckoutInteractor class takes in a CheckoutRequestModel and generates the necessary data for the
 * CheckoutResponseModel
 */
public class CheckoutInteractor {
    private final User buyer;

    /**
     * The constructor for the ReviewInteractor class
     *
     * @param requestModel the request model that's data will be manipulated
     */
    public CheckoutInteractor(CheckoutRequestModel requestModel) throws IOException {
        this.buyer = getUserWithUsername(requestModel.getBuyerUsername());
    }

    /**
     * Returns the user from the database with the given username
     *
     * @param username the username being searched for
     * @return the user with the given username
     */
    private User getUserWithUsername(String username) throws IOException {
        return new GetUser().getUserWithUsername(username);
}

    private void removeListings() throws IOException {
        Cart cart = this.buyer.getCart();
        for (Listing listing : cart.getItems()) {
            String seller = listing.getSellerUsername();
            User sellerObject = new DatabaseController().getUserWithUsername(seller);
            sellerObject.removeListing(listing);
        }
    }
}

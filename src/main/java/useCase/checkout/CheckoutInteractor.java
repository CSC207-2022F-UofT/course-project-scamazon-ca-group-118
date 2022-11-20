package useCase.checkout;

import database.GetUser;
import entities.User;
import entities.Cart;
import entities.Listing;

/**
 * The CheckoutInteractor class takes in a CheckoutRequestModel and generates the necessary entities.data for the
 * CheckoutResponseModel
 */
public class CheckoutInteractor {
    private final User buyer;

    /**
     * The constructor for the ReviewInteractor class
     *
     * @param requestModel the request model that's entities.data will be manipulated
     */
    public CheckoutInteractor(CheckoutRequestModel requestModel) {
        this.buyer = getUserWithUsername(requestModel.getBuyerUsername());
    }

    /**
     * Returns the user from the database with the given username
     *
     * @param username the username being searched for
     * @return the user with the given username
     */
    private User getUserWithUsername(String username) {
        return new GetUser().getUserWithUsername(username);
    }

    private void removeListings() {
        Cart cart = this.buyer.getCart();
        for (Listing listing: cart.getItems()) {
            User seller = listing.getSeller();
            seller.removeListing(listing);
        }
    }
}

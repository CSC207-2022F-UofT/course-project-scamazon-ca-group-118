package use_case.checkout;

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
    private final User BUYER;

    /**
     * The constructor for the ReviewInteractor class
     *
     * @param username the username entered by the User
     */
    public CheckoutInteractor(String username) throws IOException {
        this.BUYER = new GetUser().getUserWithUsername(username);
    }

    public String getMessage() throws IOException {
        this.removeListings();
        return "You have successfully checked out";
    }

    //removes all items in buyer User's cart by removing each item from the seller User's listings
    public void removeListings() throws IOException {
        Cart cart = this.BUYER.getCart();
        for (Listing listing : cart.getItems()) {
            String sellerUsername = listing.getSellerUsername();
            User seller = new GetUser().getUserWithUsername(sellerUsername);
            seller.removeListing(listing);
        }
    }
}

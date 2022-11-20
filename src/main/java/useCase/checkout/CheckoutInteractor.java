package useCase.checkout;

import database.GetUser;
import entities.User;
import entities.Cart;
import entities.Listing;
import useCase.login.CheckPassword;
import useCase.login.LoginFailed;

/**
 * The CheckoutInteractor class takes in a CheckoutRequestModel and generates the necessary data for the
 * CheckoutResponseModel
 */
public class CheckoutInteractor {
    private User buyer;
    private String username;

    /**
     * The constructor for the ReviewInteractor class
     *
     * @param username the username entered by the User
     */
    public CheckoutInteractor(String username) {
        this.username = username;
        this.buyer = new GetUser().getUserWithUsername(this.username);
    }

    //removes all items in buyer User's cart by removing each item from the seller User's listings
    public void removeListings() {
        Cart cart = this.buyer.getCart();
        for (Listing listing: cart.getItems()) {
            User seller = listing.getSeller();
            seller.removeListing(listing);
        }
    }
}

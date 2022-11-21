package useCase.checkout;

import database.DatabaseController;
import database.GetUser;
import entities.User;
import entities.Cart;
import entities.Listing;
import useCase.login.CheckPassword;
import useCase.login.LoginFailed;

import java.io.IOException;

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
<<<<<<< HEAD
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
=======
    public CheckoutInteractor(String username) {
        this.username = username;
        this.buyer = new GetUser().getUserWithUsername(this.username);
    }

    //removes all items in buyer User's cart by removing each item from the seller User's listings
    public void removeListings() {
>>>>>>> origin/main
        Cart cart = this.buyer.getCart();
        for (Listing listing : cart.getItems()) {
            String seller = listing.getSellerUsername();
            User sellerObject = new DatabaseController().getUserWithUsername(seller);
            sellerObject.removeListing(listing);
        }
    }
}

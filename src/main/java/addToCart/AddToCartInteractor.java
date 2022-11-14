package addToCart;

import database.GetUser;
import entities.User;
import features.Listing;

public class AddToCartInteractor {
    private User buyer;
    private Listing listing;

    public AddToCartInteractor(AddToCartRequestModel requestModel) {
        this.buyer = getUserWithUsername(requestModel.getBuyerUsername());
        this.listing = requestModel.getListing();
    }

    private User getUserWithUsername(String buyerUsername) {
        return new GetUser().getUserWithUsername(buyerUsername);
    }

    private void addToCart() {
        this.buyer.addToCart(this.listing);
    }

    public String getMessage() {
        if (this.buyer.getCart().getItems().contains(this.listing)) {
            return "Item successfully added to cart";
        } else {
            return "Unable to add item to cart";
        }
    }

    public User getBuyer() {
        return this.buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Listing getListing() {
        return this.listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }
}

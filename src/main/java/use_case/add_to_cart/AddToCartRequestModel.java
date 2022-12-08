package use_case.add_to_cart;

import entities.Listing;

public class AddToCartRequestModel {
    private String buyerUsername;
    private Listing listing;
    private boolean canAddToCart;

    public AddToCartRequestModel(String buyerUsername, Listing listing, boolean canAddToCart) {
        this.buyerUsername = buyerUsername;
        this.listing = listing;
        this.canAddToCart = canAddToCart;
        
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public void setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }

    public boolean isCanAddToCart() {
        return canAddToCart;
    }

    public void setCanAddToCart(boolean canAddToCart) {
        this.canAddToCart = canAddToCart;
    }
}

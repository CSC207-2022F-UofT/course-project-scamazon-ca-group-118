package pages;

import features.Listing;
import forms.AddToCartForm;

public class ListingDetailPage extends Page {
    private Listing listing;
    private boolean canAddToCart = true;
    private AddToCartForm addToCartForm;

    public ListingDetailPage(String title, Listing listing) {
        super(title);
        this.listing = listing;
        // TODO: figure out currentUser
        this.addToCartForm = new AddToCartForm(currentUser.username, listing);
        this.canAddToCart = addToCartForm.getCanAddToCart();
    }

}

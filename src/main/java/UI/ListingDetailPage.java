package UI;

import entities.Listing;
import forms.AddToCartForm;
import entities.User;

public class ListingDetailPage extends Page {
    private Listing listing;
    private boolean canAddToCart = true;
    private AddToCartForm addToCartForm;

    public ListingDetailPage(String title, Listing listing) {
        super(title);
        this.listing = listing;
        // TODO: figure out currentUser

        //this.addToCartForm = new AddToCartForm(currentUser.username, listing);
        this.canAddToCart = addToCartForm.getCanAddToCart();
    }

}

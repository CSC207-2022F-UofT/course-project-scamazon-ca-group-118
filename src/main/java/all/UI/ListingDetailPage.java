package all.UI;

import all.entities.Listing;
import all.forms.AddToCartForm;

import static all.entities.User.currentUser;

public class ListingDetailPage extends Page {
    private Listing listing;
    private boolean canAddToCart = true;
    private AddToCartForm addToCartForm;

    public ListingDetailPage(String title, Listing listing) {
        super(title);
        this.listing = listing;
        // TODO: figure out currentUser
        this.addToCartForm = new AddToCartForm(currentUser.getUsername(), listing);
        this.canAddToCart = addToCartForm.getCanAddToCart();
    }

}

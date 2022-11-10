package pages;

import features.Listing;

public class ListingDetailPage extends Page {
    private Listing listing;
    private boolean canAddToCart = true;

    public ListingDetailPage(String title, Listing listing) {
        super(title);
        this.listing = listing;
        // TODO: how do i access currentUser? will this involve a database interactor?
        for (Listing l : currentUser.getCart().getListings()) {
            if (l.getId() == listing.getId()) {
                this.canAddToCart = false;
                break;
            }
        }
    }

    // this is the onclick method of the swing AddToCart button. it is only clickable if
    // canAddToCart == true. otherwise, the button should be greyed out
    public void addToCart() {
        currentUser.setCart(currentUser.getCart().add(this.listing));
        // TODO: navigate to ListingListPage
    }

    public Listing getListing() {
        return listing;
    }
}

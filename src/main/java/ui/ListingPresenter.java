package ui;
import entities.Listing;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter class for the lists
 */
public class ListingPresenter {
    private List<Listing> listings = new ArrayList<>();

    //maybe get the listings from a different layer to show?
    public ListingPresenter(List<Listing> listings) {
        this.listings = listings;
    }

    // this method should show the listings based on other layers i think?
    public List<Listing> displayListings() {
        return listings;
    }

}

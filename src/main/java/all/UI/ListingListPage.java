package all.UI;
import all.entities.Listing;

import java.util.List;

public class ListingListPage extends Page {
    private List<Listing> listings;

    // super call from page, assume every page title will be listings?
    public ListingListPage(List<Listing> listings) {
        super("Listings");
        this.listings = listings;
    }
    // getters and setters
    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    public List<Listing> showListings() {
        // TODO: Implement GUI here? or this will show listings
        // return data;
        return null;
    }

    // will show listings
    // TODO: method for GUI
//    public void GUI(List <Listing> listings) {
//
//    }

    // I think the plan is to call showListings with our data variable as a parameter
}

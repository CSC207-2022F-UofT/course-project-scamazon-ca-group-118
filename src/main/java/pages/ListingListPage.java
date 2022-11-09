package pages;
import features.Listing;
import features.Search;

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

    // will show listings
    // TODO: Will change once GUI is implemented
    public void showListings(List <Listing> listings) {
        for (Listing List : listings) {
            System.out.println(List);
        }
    }
}

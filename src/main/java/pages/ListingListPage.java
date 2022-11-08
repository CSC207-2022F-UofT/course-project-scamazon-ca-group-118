package pages;
import features.Listing;
import features.Search;

import java.util.List;

public class ListingListPage {
    private List<Listing> listings;

    public ListingListPage(List<Listing> listings) {
        this.listings = listings;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }
    public void showListings(List <Listing> listings) {
        for (Listing List : listings) {
            System.out.println(List);
        }
    }
}

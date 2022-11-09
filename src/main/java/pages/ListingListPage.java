package pages;
import features.Listing;
import features.Search;
import entities.DatabaseInteractor;

import java.util.List;

public class ListingListPage extends Page implements DatabaseInteractor{
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



    // Implementing all methods from interface
    // placeholder since I won't need this method
    public void create() {
        return;
    }

    public void update() {
        return;
    }
    public void delete() {
        return;
    }

    public T query() {
        // TODO: Implement query which returns data, will have to access database which is not yet setup
        return data;
    }
    T data = DatabaseInteractor.query();

    // will show listings
    // TODO: Will change once GUI is implemented
    public void showListings(List <Listing> listings) {
        for (Listing List : listings) {
            System.out.println(List);
        }
    }

    // I think the plan is to call showListings with our data variable as a parameter
}

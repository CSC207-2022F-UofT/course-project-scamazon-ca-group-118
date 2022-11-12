package database;

import features.Listing;

import java.util.ArrayList;
import java.util.List;

public class ListingDatabaseController implements ListingDatabaseGateway, DatabaseInteractor{

    private List<Listing> listings;

    public ListingDatabaseController(String keyword) {
        this.listings = getListingWithSearch(keyword);
    }

    public ListingDatabaseController() {
        this.listings = getListingDefault();
    }

    @Override
    public List<Listing> getListingWithSearch(String keyword) {
        List<Listing> listings = new ArrayList<>();
        // this will go through database and retrieve first 10 listings with keyword in the listing name
//        for (int i=0; i < 10; i++) {
//          listings.append(query(keyword))
//        }
        return null;
    }


    @Override
    public List<Listing> getListingDefault() {
        List<Listing> listings = new ArrayList<>();
        // this will go through database and retrieve first 10 listings
//        for (int i=0; i < 10; i++) {
//            listings.append(query())
//        }
        return listings;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    // methods from database interactor
    // I only need query so maybe use solid principle of I?
    @Override
    public boolean create() {
        return false;
    }

    @Override
    public List<Listing> query() {
        return null;
    }

    public List<Listing> query(String keyword) {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}

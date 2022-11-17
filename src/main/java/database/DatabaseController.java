package database;


import entities.Listing;
import entities.User;

import java.util.List;

public class DatabaseController<T> implements CreateListingDatabaseGateway, ReviewDatabaseGateway,
        ListingDatabaseGateway{
    String table = null;

    public DatabaseController() {
    }

    @Override
    public User getUserWithUsername(String username) {
        return null;
    }

    @Override
    public List<Listing> getListingWithSearch(String keyword) {
        return null;
    }

    @Override
    public List<Listing> getListingDefault() {
        return null;
    }
}

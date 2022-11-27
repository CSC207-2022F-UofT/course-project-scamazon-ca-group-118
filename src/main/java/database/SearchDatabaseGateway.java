package database;

import entities.Listing;

import java.util.ArrayList;

public interface SearchDatabaseGateway {

    /**
     * Uses a query to search the database for listings.
     *
     * @param query A String to be used to search for listings
     * @return A ArrayList of Listings matyching query
     */
    ArrayList<Listing> getListings(String query);
}

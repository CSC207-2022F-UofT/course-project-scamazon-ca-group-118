package useCase.Search;

import database.ListingDatabaseGateway;
import entities.Listing;

import java.io.IOException;
import java.util.ArrayList;

public class SearchInteractor {

    ArrayList<Listing> listings;

    public SearchInteractor(String query, ListingDatabaseGateway databaseGateway) throws IOException {
        listings = databaseGateway.getListingWithSearch(query);
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }
}

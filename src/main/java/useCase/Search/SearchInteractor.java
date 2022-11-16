package useCase.Search;

import database.SearchDatabaseGateway;
import entities.Listing;

import java.util.ArrayList;

public class SearchInteractor {

    ArrayList<Listing> listings;

    public SearchInteractor(String query, SearchDatabaseGateway databaseGateway) {
        listings = databaseGateway.getListings(query);
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }
}

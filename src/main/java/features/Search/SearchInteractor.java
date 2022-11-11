package features.Search;

import features.Listing;

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

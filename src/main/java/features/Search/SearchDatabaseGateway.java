package features.Search;

import features.Listing;

import java.util.ArrayList;

public interface SearchDatabaseGateway {

    ArrayList<Listing> getListings(String query);
}

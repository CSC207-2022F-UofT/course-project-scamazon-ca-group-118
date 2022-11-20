package all.database;

import all.entities.Listing;

import java.util.ArrayList;

public interface SearchDatabaseGateway {

    ArrayList<Listing> getListings(String query);
}

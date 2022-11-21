package database;
import entities.Listing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ListingDatabaseGateway {
    /**
     * Should get the first x (10?) amount of listings to show based on a search keyword
     * @param keyword search phrase
     * @return returns a list of lists
     */
    ArrayList<Listing> getListingWithSearch(String keyword) throws IOException;

    /**
     * Should get the first x (10?) amount of listings to show without a search keyword
     * @return returns a list of lists
     */
    ArrayList<Listing> getAllListings() throws IOException;
}

package database;
import entities.Listing;

import java.util.List;

public interface ListingDatabaseGateway {
    /**
     * Should get the first x (10?) amount of listings to show based on a search keyword
     * @param keyword search phrase
     * @return returns a list of lists
     */
    List<Listing> getListingWithSearch(String keyword);

    /**
     * Should get the first x (10?) amount of listings to show without a search keyword
     * @return returns a list of lists
     */
    List<Listing> getListingDefault();
}

package database;

import entities.User;
import features.Listing;

import java.util.List;

public interface ReviewDatabaseGateway {
    /**
     * should retrieve the User with the specified username from the database, or an empty User if no user has
     * this username
     *
     * @param username the username that will be searched for in the database
     * @return User whose username is username, or an empty User
     */
    User getUserWithUsername(String username);

    interface ListingDatabaseGateway {
        /**
         * Should retrieve the first x (10?) amount of listings to display on the page based on search keywords
         * @param keyword which is what was entered into the search bar
         * @return list of listings
         */

        List<Listing> getListingsWithSearch(String keyword);

        /**
         * Should retrieve the first x (10?) amount of listings to display on the page as a default
         * @return list of listings
         */

        List<Listing> getListingsDefault();
    }

    class ListingDatabaseController {
    }
}

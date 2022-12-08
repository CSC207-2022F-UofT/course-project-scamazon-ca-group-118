package database;

import entities.Listing;
import entities.User;

import java.io.IOException;

public interface ListingDetailDatabaseGateway {

    /**
     * checks if the current listing selected is already in the user's cart
     * @param currentUser current user object
     * @param listing current listing selected
     * @return true if it is a duplicate
     * @throws IOException throws in case of IOException
     */
    boolean currentUserHasListingInCart(User currentUser, Listing listing) throws IOException;

    void addListingToUserCart(User currentUser, Listing listing) throws IOException;
}

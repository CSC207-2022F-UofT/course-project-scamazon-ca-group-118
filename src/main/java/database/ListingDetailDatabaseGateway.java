package database;

import entities.Listing;
import entities.User;

import java.io.IOException;

public interface ListingDetailDatabaseGateway {

    boolean currentUserHasListingInCart(User currentUser, Listing listing);

    void addListingToUserCart(User currentUser, Listing listing) throws IOException;
}

package database;

import java.io.IOException;

public interface CartDatabaseGateway {
    /**
     * Adds a listing to the cart from the listing detail page
     * @param ID pass the id of the listing to be removed
     */
    void removeFromCart(int ID) throws IOException;

}

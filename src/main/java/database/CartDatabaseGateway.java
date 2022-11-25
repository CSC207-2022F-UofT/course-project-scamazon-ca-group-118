package database;

import java.io.IOException;

public interface CartDatabaseGateway {
    /**
     * Adds a listing to the cart from the listing detail page
     *
     */
    void removeFromCart() throws IOException;

}

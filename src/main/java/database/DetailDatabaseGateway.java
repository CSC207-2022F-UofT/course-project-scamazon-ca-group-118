package database;

import java.io.IOException;

public interface DetailDatabaseGateway {
    /**
     * Adds a listing to the cart from the listing detail page
     *
     */
    void addToCart() throws IOException;
}

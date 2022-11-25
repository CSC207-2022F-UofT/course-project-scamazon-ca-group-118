package database;

import java.io.IOException;

public interface DetailDatabaseGateway {
    /**
     * Adds a listing to the cart from the listing detail page
     * @param ID pass the id of the listing to be added to cart
     */
    void addToCart(int ID) throws IOException;
}

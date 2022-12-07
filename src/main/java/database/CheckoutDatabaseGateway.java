package database;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public interface CheckoutDatabaseGateway {

    /**
     * given the current user, remove all the listings after someone checks out
     */
    void checkoutRemoveListings() throws IOException, CsvException;
}

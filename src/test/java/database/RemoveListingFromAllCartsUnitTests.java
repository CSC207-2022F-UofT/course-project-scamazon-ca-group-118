package database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class RemoveListingFromAllCartsUnitTests {
    public static final DatabaseController db = new DatabaseController();

    @BeforeAll
    public static void setUp() {
        db.setListingTablePath("src/test/java/database/Listings.csv");
        db.setUserTablePath("src/test/java/database/Users.csv");
        File usersCSV = new File(db.getUserTablePath());
        File listingsCSV = new File(db.getListingTablePath());
    }

    @BeforeEach
    public void resetCSVFiles() throws IOException {
        File usersCSV = new File(db.getUserTablePath());
        if (usersCSV.delete()) {
            usersCSV.createNewFile();
        }
        File listingsCSV = new File(db.getListingTablePath());
        if (listingsCSV.delete()) {
            listingsCSV.createNewFile();
        }
    }

    @AfterAll
    public static void deleteCSVFiles() {
        File usersCSV = new File(db.getUserTablePath());
        usersCSV.delete();
        File listingsCSV = new File(db.getListingTablePath());
        listingsCSV.delete();
    }

    @Test
    public void removeFromAllCartsOneListing() {

    }
}

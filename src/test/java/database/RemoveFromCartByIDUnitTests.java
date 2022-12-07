package database;

import entities.Listing;
import entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class RemoveFromCartByIDUnitTests {
    public static final DatabaseController db = new DatabaseController();

    @BeforeAll
    public static void setUp() throws IOException {
        db.setListingTablePath("src/test/java/database/Listings.csv");
        db.setUserTablePath("src/test/java/database/Users.csv");
        File usersCSV = new File(db.getUserTablePath());
        usersCSV.createNewFile();
        File listingsCSV = new File(db.getListingTablePath());
        listingsCSV.createNewFile();
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
        Listing.setNextId(0);
        User.setNextId(0);
    }

    @AfterAll
    public static void deleteCSVFiles() {
        File usersCSV = new File(db.getUserTablePath());
        usersCSV.delete();
        File listingsCSV = new File(db.getListingTablePath());
        listingsCSV.delete();
    }

    @Test
    public void removeSingleListingFromSingleCart() throws IOException {
        db.createUser("seller", "pass", "pass@pass");
        db.createListing("seller", "title", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.createUser("test", "test", "test@test.com");
        db.addListingToUserCart(db.getUserWithUsername("test"), db.getListingByID(0));
        User user = db.getUserWithUsername("test");
        assert user.getCart().getItems().size() == 1;
        assert user.getCart().getItems().get(0).equals(db.getListingByID(0));
    }

    @Test
    public void removeMultipleListingsFromSingleCart() throws IOException {
        db.createUser("seller", "pass", "pass@pass");
        db.createListing("seller", "title1", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("seller", "title2", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.createUser("test", "test", "test@test.com");
        db.addListingToUserCart(db.getUserWithUsername("test"), db.getListingByID(0));
        db.addListingToUserCart(db.getUserWithUsername("test"), db.getListingByID(1));
        User user = db.getUserWithUsername("test");
        assert user.getCart().getItems().size() == 2;
        assert user.getCart().getItems().get(0).equals(db.getListingByID(0));
        assert user.getCart().getItems().get(1).equals(db.getListingByID(1));
    }
}
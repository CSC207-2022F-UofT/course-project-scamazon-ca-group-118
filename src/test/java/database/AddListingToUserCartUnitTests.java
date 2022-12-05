package database;

import entities.Cart;
import entities.Listing;
import entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddListingToUserCartUnitTests {
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
    }

    @AfterAll
    public static void deleteCSVFiles() {
        File usersCSV = new File(db.getUserTablePath());
        usersCSV.delete();
        File listingsCSV = new File(db.getListingTablePath());
        listingsCSV.delete();
    }

    @Test
    public void testAddListingToEmptyCart() throws IOException {
        db.createUser("test", "test", "test");
        User user = db.getUserWithUsername("test");
        Listing listing = new Listing(0, "seller", "title", LocalDate.EPOCH,
                100, "description", "imagePath");
        db.addListingToUserCart(user, listing);
        User expectedUser = new User(0, "test", "test", "test", new ArrayList<>(),
                new ArrayList<>(), new Cart(new ArrayList<>(List.of(listing))));
        User actualUser = db.getUserWithUsername("test");
        assert actualUser.equals(expectedUser);
    }
}

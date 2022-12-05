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
    public void testAddListingToEmptyCart() throws IOException {
        db.createUser("test", "test", "test");
        User user = db.getUserWithUsername("test");
        Listing listing = new Listing(0, "seller", "title", LocalDate.EPOCH,
                100, "description", "imagePath");
        db.createListing("seller", "title", 100, LocalDate.EPOCH, "description", "imagePath");
        db.addListingToUserCart(user, listing);
        User expectedUser = new User(0, "test", "test", "test", new ArrayList<>(),
                new ArrayList<>(), new Cart(new ArrayList<>(List.of(listing))));
        User actualUser = db.getUserWithUsername("test");
        assert actualUser.equals(expectedUser);
    }

    @Test
    public void testAddListingToSingleCart() throws IOException {
        db.createUser("test", "test", "test");
        User user = db.getUserWithUsername("test");
        Listing listing1 = new Listing(0, "seller", "title1", LocalDate.EPOCH,
                100, "description", "imagePath");
        Listing listing2 = new Listing(1, "seller", "title2", LocalDate.EPOCH,
                100, "description", "imagePath");
        db.createListing("seller", "title1", 100, LocalDate.EPOCH, "description", "imagePath");
        db.createListing("seller", "title2", 100, LocalDate.EPOCH, "description", "imagePath");
        // correctly added one listing to cart
        db.addListingToUserCart(user, listing1);
        user = db.getUserWithUsername("test");
        assert user.getCart().getItems().size() == 1;
        assert user.getCart().getItems().get(0).equals(listing1);

        // correctly added next listing to cart
        db.addListingToUserCart(user, listing2);
        user = db.getUserWithUsername("test");
        assert user.getCart().getItems().size() == 2;
        assert user.getCart().getItems().get(1).equals(listing2);
    }
}

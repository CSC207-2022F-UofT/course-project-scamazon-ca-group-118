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
    public void removeFromAllCartsOneListing() throws IOException {
        db.createUser("sellerUser", "Pass", "email1");
        db.createUser("currUser", "Pass", "email2");
        db.createUser("user3", "Pass", "email3");

        db.createListing("sellerUser", "title1", 1, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("sellerUser", "title2", 1, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("user3", "title3", 1, LocalDate.EPOCH, "desc", "imagePath");

        User currUser = db.getUserWithUsername("currUser");
        User user3 = db.getUserWithUsername("user3");

        Listing listing1 = db.getListingByID(0);
        Listing listing2 = db.getListingByID(1);

        db.addListingToUserCart(currUser, listing1);
        db.addListingToUserCart(currUser, listing2);
        db.addListingToUserCart(user3, listing1);
        db.addListingToUserCart(user3, listing2);
        db.removeListingFromAllCarts(0);

        Cart currUserCart = currUser.getCart();
        Cart user3Cart = user3.getCart();

        assert (currUserCart.g)
    }
}

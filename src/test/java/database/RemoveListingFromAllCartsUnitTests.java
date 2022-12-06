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

        currUser = db.getUserWithUsername("currUser");
        user3 = db.getUserWithUsername("user3");
        Cart currUserCart = currUser.getCart();
        Cart user3Cart = user3.getCart();

        assert (currUserCart.getItems().size() == 1);
        assert (user3Cart.getItems().size() == 1);

        assert (currUser.getCart().getItems().get(0).getId() == 1);
        assert (user3.getCart().getItems().get(0).getId() == 1);
    }

    @Test
    public void removeFromAllCartsAllListings() throws IOException {
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
        db.removeListingFromAllCarts(1);

        currUser = db.getUserWithUsername("currUser");
        user3 = db.getUserWithUsername("user3");
        Cart currUserCart = currUser.getCart();
        Cart user3Cart = user3.getCart();

        assert (currUserCart.getItems().size() == 0);
        assert (user3Cart.getItems().size() == 0);

    }
}

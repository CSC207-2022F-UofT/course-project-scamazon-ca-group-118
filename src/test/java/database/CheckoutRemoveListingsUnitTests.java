package database;

import main.Main;
import com.opencsv.exceptions.CsvException;
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

public class CheckoutRemoveListingsUnitTests {
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
    public void checkoutRemoveListingsThreeListings() throws IOException, CsvException {
        db.createListing("1", "title1", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("1", "title2", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("1", "title3", 100, LocalDate.EPOCH, "desc", "imagePath");

        db.createUser("currUser", "Password", "email");

        User currUser = db.getUserWithUsername("currUser");
        Main.setCurrentUser(currUser);

        ArrayList<Listing> listings = db.getAllListings();
        for (Listing listing : listings) {
            db.addListingToUserCart(Main.getCurrentUser(), listing);
        }
        User currUserUpdated = db.getUserWithUsername("currUser");
        Main.setCurrentUser(currUserUpdated);

        db.checkoutRemoveListings();

        ArrayList<Listing> clearedListings = db.getAllListings();
        assert clearedListings.size() == 0;
    }

    @Test
    public void checkoutRemoveListingsZeroListings() throws IOException, CsvException {
        db.createListing("1", "title1", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("1", "title2", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("1", "title3", 100, LocalDate.EPOCH, "desc", "imagePath");

        db.createUser("currUser", "Password", "email");

        User currUser = db.getUserWithUsername("currUser");
        Main.setCurrentUser(currUser);

        User currUserUpdated = db.getUserWithUsername("currUser");
        Main.setCurrentUser(currUserUpdated);

        db.checkoutRemoveListings();

        ArrayList<Listing> clearedListings = db.getAllListings();
        assert clearedListings.size() == 3;
    }

    @Test
    public void checkoutRemoveListingsOneListing() throws IOException, CsvException {
        db.createListing("1", "title1", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("1", "title2", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("1", "title3", 100, LocalDate.EPOCH, "desc", "imagePath");

        db.createUser("currUser", "Password", "email");

        User currUser = db.getUserWithUsername("currUser");
        Main.setCurrentUser(currUser);
        Listing listing1 = db.getListingByID(1);
        db.addListingToUserCart(Main.getCurrentUser(), listing1);
        User currUserUpdated = db.getUserWithUsername("currUser");
        Main.setCurrentUser(currUserUpdated);

        db.checkoutRemoveListings();

        ArrayList<Listing> clearedListings = db.getAllListings();
        assert clearedListings.size() == 2;
        assert clearedListings.get(1).getId() == 2;
        assert clearedListings.get(1).getTitle().equals("title3");
    }
}

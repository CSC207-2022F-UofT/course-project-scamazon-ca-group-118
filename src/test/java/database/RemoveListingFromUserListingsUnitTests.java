package database;

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

public class RemoveListingFromUserListingsUnitTests {
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
    public void testDeleteListingFromSingleUserListings() throws IOException, CsvException {
        db.createUser("1", "1", "1@1");
        db.createListing("1", "title", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.removeListingFromUserListings(0);
        User user = db.getUserWithUsername("1");
        assert user.getListings().size() == 0;
    }

    @Test
    public void testDeleteMultipleListingsFromSingleUserListings() throws IOException, CsvException {
        db.createUser("1", "1", "1@1");
        db.createListing("1", "title1", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("1", "title2", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("1", "title3", 100, LocalDate.EPOCH, "desc", "imagePath");
        db.removeListingFromUserListings(0);
        User user = db.getUserWithUsername("1");
        assert user.getListings().size() == 2;
        assert user.getListings().get(0).getId() == 1;
        assert user.getListings().get(1).getId() == 2;

        db.removeListingFromUserListings(2);
        user = db.getUserWithUsername("1");
        assert user.getListings().size() == 1;
        assert user.getListings().get(0).getId() == 1;

        db.removeListingFromUserListings(1);
        user = db.getUserWithUsername("1");
        assert user.getListings().size() == 0;
    }
}

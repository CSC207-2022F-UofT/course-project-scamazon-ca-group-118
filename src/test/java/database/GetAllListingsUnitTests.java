package database;

import entities.Listing;
import entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetAllListingsUnitTests {
    public static final DatabaseController db = new DatabaseController();

    @BeforeAll
    public static void setUp() {
        db.setListingTablePath("src/test/java/database/Listings.csv");
        db.setUserTablePath("src/test/java/database/Users.csv");
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


    @Test
    public void testEmptyDatabase() throws IOException {
        ArrayList<Listing> listings = db.getAllListings();
        assert listings.isEmpty();
    }

    @Test
    public void testSingleDatabase() throws IOException {
        db.createListing("seller", "title", 1, LocalDate.EPOCH, "desc", "imagePath");
        ArrayList<Listing> actualListings = db.getAllListings();
        Listing expectedListing1 = new Listing(0, "seller", "title", LocalDate.EPOCH,
                1, "desc", "imagePath");
        ArrayList<Listing> expectedListings = new ArrayList<>(List.of(expectedListing1));
        assert actualListings.get(0).equals(expectedListings.get(0));
    }

    @Test
    public void testManyDatabase() throws IOException {
        db.createListing("seller1", "title1", 1, LocalDate.EPOCH, "desc1", "imagePath1");
        db.createListing("seller2", "title2", 2, LocalDate.EPOCH, "desc2", "imagePath2");
        db.createListing("seller3", "title3", 3, LocalDate.EPOCH, "desc3", "imagePath3");
        ArrayList<Listing> actualListings = db.getAllListings();
        Listing expectedListing1 = new Listing(1, "seller1", "title1", LocalDate.EPOCH,
                1, "desc1", "imagePath1");
        Listing expectedListing2 = new Listing(2, "seller2", "title2", LocalDate.EPOCH,
                2, "desc2", "imagePath2");
        Listing expectedListing3 = new Listing(3, "seller3", "title3", LocalDate.EPOCH,
                3, "desc3", "imagePath3");
        ArrayList<Listing> expectedListings = new ArrayList<>(List.of(expectedListing1, expectedListing2, expectedListing3));

        for (int i = 0; i < expectedListings.size(); i++) {
            assert expectedListings.get(i).equals(actualListings.get(i));
        }
    }
}

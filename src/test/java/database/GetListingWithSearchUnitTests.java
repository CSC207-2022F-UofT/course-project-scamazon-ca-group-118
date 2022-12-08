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
import java.util.ArrayList;

public class GetListingWithSearchUnitTests {
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
    public void getListingWithSearchThreeListings() throws IOException {
        db.createListing("sellerUser", "title1", 1, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("sellerUser", "title2", 1, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("user3", "title3", 1, LocalDate.EPOCH, "desc", "imagePath");

        ArrayList<Listing> listings = db.getListingWithSearch("title");
        assert listings.size() == 3;
        assert listings.get(0).getId() == 0;
        assert listings.get(1).getTitle().equals("title2");
    }

    @Test
    public void getListingWithSearchNoneReturned() throws IOException {
        db.createListing("sellerUser", "title1", 1, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("sellerUser", "title2", 1, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("user3", "title3", 1, LocalDate.EPOCH, "desc", "imagePath");

        ArrayList<Listing> listings = db.getListingWithSearch("abc");
        assert listings.size() == 0;

    }

    @Test
    public void getListingWithSearchOneReturned() throws IOException {
        db.createListing("sellerUser", "title1", 1, LocalDate.EPOCH, "desctest", "imagePath");
        db.createListing("sellerUser", "title2", 1, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("user3", "title3", 1, LocalDate.EPOCH, "desc", "imagePath");

        ArrayList<Listing> listings = db.getListingWithSearch("1");
        assert listings.size() == 1;
        assert listings.get(0).getId() == 0;
        assert listings.get(0).getDescription().equals("desctest");
    }
}

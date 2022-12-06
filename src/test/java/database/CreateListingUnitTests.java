package database;

import com.opencsv.CSVWriter;
import entities.Cart;
import entities.Listing;
import entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateListingUnitTests {
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
    public void testCreateOneListing() throws IOException {
        // call method
        db.createListing("sellerUser", "title", 1, LocalDate.EPOCH, "desc", "imagePath");

        BufferedReader reader = new BufferedReader(new FileReader(db.getListingTablePath()));
        String expected = "0;sellerUser;title;1.00;1970-01-01;desc;imagePath";
        String result = "";
        String currLine;
        while ((currLine = reader.readLine()) != null) {
            result += currLine;
        }
        // test
        assert result.equals(expected);

    }

    @Test
    public void testCreateTwoListings() throws IOException {
        // call method
        db.createListing("sellerUser", "title", 1, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("currUser", "title", 1, LocalDate.EPOCH, "desc", "imagePath");


        BufferedReader reader = new BufferedReader(new FileReader(db.getListingTablePath()));
        String expected = "0;sellerUser;title;1.00;1970-01-01;desc;imagePath1;currUser;title;1.00;1970-01-01;desc;imagePath";
        String result = "";
        String currLine;
        while ((currLine = reader.readLine()) != null) {
            result += currLine;
            System.out.println(result);
        }
        // test
        assert result.equals(expected);

    }

    @Test
    public void testCreateThreeListings() throws IOException {
        // call method
        db.createListing("sellerUser", "title", 1, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("currUser", "title", 1, LocalDate.EPOCH, "desc", "imagePath");
        db.createListing("user3", "title3", 13, LocalDate.EPOCH, "desc3", "imagePath");


        BufferedReader reader = new BufferedReader(new FileReader(db.getListingTablePath()));
        String expected = "0;sellerUser;title;1.00;1970-01-01;desc;imagePath" +
                "1;currUser;title;1.00;1970-01-01;desc;imagePath" +
                "2;user3;title3;13.00;1970-01-01;desc3;imagePath";
        String result = "";
        String currLine;
        while ((currLine = reader.readLine()) != null) {
            result += currLine;
            System.out.println(result);
        }
        // test
        assert result.equals(expected);
    }

    @Test
    public void testAddListingUpdatesUserListings() throws IOException {
        db.createUser("user", "pass", "email");
        db.createListing("user", "title", 100, LocalDate.EPOCH, "desc", "imagePath");
        User user = db.getUserWithUsername("user");
        assert user.getListings().size() == 1;
        assert user.getListings().get(0).getId() == 0;

        db.createListing("user", "title2", 100, LocalDate.EPOCH, "desc2", "imagePath");
        user = db.getUserWithUsername("user");
        assert user.getListings().size() == 2;
        assert user.getListings().get(0).getId() == 0;
        assert user.getListings().get(1).getId() == 1;
    }
}

package database;

import com.opencsv.CSVWriter;
import entities.Cart;
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
}

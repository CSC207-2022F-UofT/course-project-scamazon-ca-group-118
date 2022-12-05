package database;

import com.opencsv.CSVWriter;
import entities.Cart;
import entities.Listing;
import entities.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class getListingByUserUnitTests {
    private static final DatabaseController db = new DatabaseController();

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
    public void testListingByUserOneUserTwoListings() throws IOException {
        // populate listings csv file
        FileWriter listingCSV = new FileWriter(db.getListingTablePath());
        CSVWriter listingWriter = new CSVWriter(listingCSV, ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> listingData = new ArrayList<String[]>();
        listingData.add(new String[]{"0", "currUser", "title", "100", db.convertLocalDateToStringDate(LocalDate.EPOCH), "description", "imagePath"});
        listingData.add(new String[]{"1", "currUser", "title", "100", db.convertLocalDateToStringDate(LocalDate.EPOCH), "description", "imagePath"});
        listingWriter.writeAll(listingData);
        listingWriter.close();
        // populate users csv file
        FileWriter userCSV = new FileWriter(db.getUserTablePath());
        CSVWriter userWriter = new CSVWriter(userCSV, ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> userData = new ArrayList<String[]>();
        userData.add(new String[]{"0", "currUser", "password", "curr@user.com", "[4]", "[0]", "[1]"});
        userWriter.writeAll(userData);
        userWriter.close();
        // test
        String userString = "0;currUser;password;curr@user.com;[0];[0];[0]";
        User userObject = db.createUserObject(userString);
        ArrayList<Listing> actualObject = db.getListingsByUser(userObject.getUsername());

        ArrayList<Listing> expectedObject = new ArrayList<>();
        Listing listing1 = new Listing(0, "currUser", "title", LocalDate.EPOCH, 100,"description", "imagePath");
        Listing listing2 = new Listing(1, "currUser", "title", LocalDate.EPOCH, 100, "description", "imagePath");
        expectedObject.add(listing1);
        expectedObject.add(listing2);
        String expectedObjectString = "";
        String actualObjectString = "";
        for (Listing listing : actualObject) {
            actualObjectString += (db.createListingString(listing));
        }
        for (Listing listing : expectedObject) {
            expectedObjectString +=  db.createListingString(listing);
        }

        System.out.println(expectedObjectString);
        System.out.println(actualObjectString);
        assert (expectedObjectString.equals(actualObjectString));
    }
}

package database;

import com.opencsv.CSVWriter;
import entities.Cart;
import entities.Listing;
import entities.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class CheckUserWithUsernameUnitTests {
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
    public void testUserInDatabase() throws IOException {
        // populate users csv file
        FileWriter userCSV = new FileWriter(db.getUserTablePath());
        CSVWriter userWriter = new CSVWriter(userCSV, ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> userData = new ArrayList<String[]>();
        userData.add(new String[]{"0", "currUser", "password", "curr@user.com", "[4,5,0]", "[0,1]", "[2,3]"});
        userWriter.writeAll(userData);
        userWriter.close();
        // test
        // should return true since it's a duplicate
        assert db.checkUserWithUsername("currUser");
    }

    @Test
    public void testNotUserInDatabase() throws IOException {
        // populate users csv file
        FileWriter userCSV = new FileWriter(db.getUserTablePath());
        CSVWriter userWriter = new CSVWriter(userCSV, ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> userData = new ArrayList<String[]>();
        userData.add(new String[]{"0", "currUser", "password", "curr@user.com", "[4,5,0]", "[0,1]", "[2,3]"});
        userWriter.writeAll(userData);
        userWriter.close();
        // test
        // should return false since it's not a duplicate
        assert !db.checkUserWithUsername("currUser12345");
    }
}

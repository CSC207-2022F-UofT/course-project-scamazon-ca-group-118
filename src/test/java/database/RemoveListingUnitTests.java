package database;

import com.opencsv.CSVWriter;
import entities.Cart;
import entities.Listing;
import entities.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveListingUnitTests {
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
    public void testRemoveSingleListing() throws IOException {
        FileWriter listingCSV = new FileWriter(db.getListingTablePath());
        CSVWriter listingWriter = new CSVWriter(listingCSV, ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> listingData = new ArrayList<String[]>();
        listingData.add(new String[]{"0", "currUser", "title", "100", db.convertLocalDateToStringDate(LocalDate.EPOCH), "description", "imagePath"});
        listingWriter.writeAll(listingData);
        listingWriter.close();
        // read csv file after deletion
        db.removeListing(0);
        BufferedReader reader = new BufferedReader(new FileReader(db.getListingTablePath()));
        String expected = "";
        String result = "";
        String currLine;
        while ((currLine = reader.readLine()) != null) {
            result += currLine;
        }

        // test
        assert result.equals(expected);
    }

    @Test
    public void testRemoveSingleListingOneRemains() throws IOException {
        FileWriter listingCSV = new FileWriter(db.getListingTablePath());
        CSVWriter listingWriter = new CSVWriter(listingCSV, ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> listingData = new ArrayList<String[]>();
        listingData.add(new String[]{"0", "currUser", "title", "100", db.convertLocalDateToStringDate(LocalDate.EPOCH), "description", "imagePath"});
        listingData.add(new String[]{"1", "sellerUser", "title", "100", db.convertLocalDateToStringDate(LocalDate.EPOCH), "description", "imagePath"});
        listingWriter.writeAll(listingData);
        listingWriter.close();
        // read csv file after deletion
        db.removeListing(1);
        BufferedReader reader = new BufferedReader(new FileReader(db.getListingTablePath()));
        String expected = "0;currUser;title;100;1970-01-01;description;imagePath";
        String result = "";
        String currLine;
        while ((currLine = reader.readLine()) != null) {
            result += currLine;
        }
        // test
        assert result.equals(expected);
    }

    @Test
    public void testRemoveTwoListingsInARow() throws IOException {
        FileWriter listingCSV = new FileWriter(db.getListingTablePath());
        CSVWriter listingWriter = new CSVWriter(listingCSV, ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> listingData = new ArrayList<String[]>();
        listingData.add(new String[]{"0", "currUser", "title", "100", db.convertLocalDateToStringDate(LocalDate.EPOCH), "description", "imagePath"});
        listingData.add(new String[]{"1", "sellerUser", "title", "100", db.convertLocalDateToStringDate(LocalDate.EPOCH), "description", "imagePath"});
        listingWriter.writeAll(listingData);
        listingWriter.close();
        // read csv file after deletion
        db.removeListing(1);
        db.removeListing(0);
        BufferedReader reader = new BufferedReader(new FileReader(db.getListingTablePath()));
        String expected = "";
        String result = "";
        String currLine;
        while ((currLine = reader.readLine()) != null) {
            result += currLine;
        }
        // test
        assert result.equals(expected);
    }



}

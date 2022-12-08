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
import java.util.List;

public class SerializerUnitTests {
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
        Listing.setNextId(0);
        User.setNextId(0);
    }

    @Test
    public void testCreateUserObjectFromStringEmptyArrays() throws IOException {
        String userString = "0;currUser;password;curr@user.com;[];[];[]";
        User actualObject = db.createUserObject(userString);
        User expectedObject = new User(0, "currUser", "password", "curr@user.com",
                new ArrayList<>(), new ArrayList<>(), new Cart());
        assert actualObject.equals(expectedObject);
    }

    @Test
    public void testCreateUserObjectFromStringOneArrays() throws IOException {
        // populate listings csv file
        FileWriter listingCSV = new FileWriter(db.getListingTablePath());
        CSVWriter listingWriter = new CSVWriter(listingCSV, ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> listingData = new ArrayList<String[]>();
        listingData.add(new String[]{"0", "currUser", "title", "100", db.convertLocalDateToStringDate(LocalDate.EPOCH), "description", "imagePath"});
        listingData.add(new String[]{"1", "sellerUsername", "title", "100", db.convertLocalDateToStringDate(LocalDate.EPOCH), "description", "imagePath"});
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
        String userString = "0;currUser;password;curr@user.com;[4];[0];[1]";
        User actualObject = db.createUserObject(userString);
        User expectedObject = new User(0, "currUser", "password", "curr@user.com",
                new ArrayList<>(List.of(4)),
                new ArrayList<>(List.of(new Listing(0, "currUser", "title", LocalDate.EPOCH, 100, "description", "imagePath"))),
                new Cart(new ArrayList<>(List.of(new Listing(1, "sellerUsername", "title", LocalDate.EPOCH, 100, "description", "imagePath"))))
        );
        assert actualObject.equals(expectedObject);
    }

    @Test
    public void testCreateUserObjectFromStringMultipleArrays() throws IOException {
        // populate listings csv file
        FileWriter listingCSV = new FileWriter(db.getListingTablePath());
        CSVWriter listingWriter = new CSVWriter(listingCSV, ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        List<String[]> listingData = new ArrayList<String[]>();
        listingData.add(new String[]{"0", "currUser", "myListing1", "75", db.convertLocalDateToStringDate(LocalDate.EPOCH), "myDescription1", "myImagePath1"});
        listingData.add(new String[]{"1", "currUser", "myListing2", "100", db.convertLocalDateToStringDate(LocalDate.EPOCH), "myDescription2", "myImagePath2"});
        listingData.add(new String[]{"2", "sellerUsername", "title1", "100", db.convertLocalDateToStringDate(LocalDate.EPOCH), "description1", "imagePath1"});
        listingData.add(new String[]{"3", "sellerUsername", "title2", "50", db.convertLocalDateToStringDate(LocalDate.EPOCH), "description2", "imagePath2"});
        listingWriter.writeAll(listingData);
        listingWriter.close();
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
        String userString = "0;currUser;password;curr@user.com;[4,5,0];[0,1];[2,3]";
        User actualObject = db.createUserObject(userString);
        User expectedObject = new User(0, "currUser", "password", "curr@user.com",
                new ArrayList<>(List.of(4, 5, 0)),
                new ArrayList<>(List.of(
                        new Listing(0, "currUser", "myListing1", LocalDate.EPOCH, 75, "myDescription1", "myImagePath1"),
                        new Listing(1, "currUser", "myListing2", LocalDate.EPOCH, 100, "myDescription2", "myImagePath2"))),
                new Cart(new ArrayList<>(List.of(
                        new Listing(2, "sellerUsername", "title1", LocalDate.EPOCH, 100, "description1", "imagePath1"),
                        new Listing(3, "sellerUsername", "title2", LocalDate.EPOCH, 50, "description2", "imagePath2"))))
        );
        assert actualObject.equals(expectedObject);
    }

    @Test
    public void testCreateUserStringFromObjectEmptyArrays() {
        User userObject = new User(0, "currUser", "password", "curr@user.com",
                new ArrayList<>(), new ArrayList<>(), new Cart());
        String actualString = db.createUserString(userObject);
        String expectedString = "0;currUser;password;curr@user.com;[];[];[]";
        assert actualString.equals(expectedString);
    }

    @Test
    public void testCreateUserStringFromObjectOneArrays() {
        User userObject = new User(0, "currUser", "password", "curr@user.com",
                new ArrayList<>(List.of(4)),
                new ArrayList<>(List.of(new Listing(0, "currUser", "title", LocalDate.EPOCH, 100, "description", "imagePath"))),
                new Cart(new ArrayList<>(List.of(new Listing(1, "sellerUsername", "title", LocalDate.EPOCH, 100, "description", "imagePath"))))
        );
        String actualString = db.createUserString(userObject);
        String expectedString = "0;currUser;password;curr@user.com;[4];[0];[1]";
        assert actualString.equals(expectedString);
    }

    @Test
    public void testCreateUserStringFromObjectMultipleArrays() {
        User userObject = new User(0, "currUser", "password", "curr@user.com",
                new ArrayList<>(List.of(4, 5, 0)),
                new ArrayList<>(List.of(
                        new Listing(0, "currUser", "myListing1", LocalDate.EPOCH, 75, "myDescription1", "myImagePath1"),
                        new Listing(1, "currUser", "myListing2", LocalDate.EPOCH, 100, "myDescription2", "myImagePath2"))),
                new Cart(new ArrayList<>(List.of(
                        new Listing(2, "sellerUsername", "title1", LocalDate.EPOCH, 100, "description1", "imagePath1"),
                        new Listing(3, "sellerUsername", "title2", LocalDate.EPOCH, 50, "description2", "imagePath2"))))
        );
        String actualString = db.createUserString(userObject);
        String expectedString = "0;currUser;password;curr@user.com;[4,5,0];[0,1];[2,3]";
        assert actualString.equals(expectedString);
    }

    @Test
    public void testCreateListingObjectFromString() {
        String listingString = "0;seller;title;100;1970-01-01;description;imagePath";
        Listing actualObject = db.createListingObject(listingString);
        Listing expectedObject = new Listing(0, "seller", "title", LocalDate.EPOCH,
                100, "description", "imagePath");
        assert actualObject.equals(expectedObject);
    }

    @Test
    public void testCreateListingStringFromObject() {
        Listing listingObject = new Listing(0, "seller", "title", LocalDate.EPOCH,
                100, "description", "imagePath");
        String actualString = db.createListingString(listingObject);
        String expectedString = "0;seller;title;100.00;1970-01-01;description;imagePath";
        assert actualString.equals(expectedString);
    }
}

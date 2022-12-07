package database;

import entities.Listing;
import entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class AddReviewUnitTests {
    private static final DatabaseController db = new DatabaseController();

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
    public void testAddSingleReviewToSingleUser() throws IOException {
        db.createUser("test", "test", "test@test");
        User user = db.getUserWithUsername("test");
        db.addReview(user, 4);
        user = db.getUserWithUsername("test");
        assert user.getReviews().size() == 1;
        assert user.getReviews().get(0) == 4;
    }

    @Test
    public void testAddMultipleReviewsToSingleUser() throws IOException {
        db.createUser("test", "test", "test@test.com");
        User user = db.getUserWithUsername("test");
        db.addReview(user, 4);
        db.addReview(user, 1);
        user = db.getUserWithUsername("test");
        assert user.getReviews().size() == 2;
        assert user.getReviews().contains(1) && user.getReviews().contains(4);
    }

    @Test
    public void testAddSingleReviewToMultipleUsers() throws IOException {
        db.createUser("test1", "test", "test@test.com");
        db.createUser("test2", "test", "test@test.com");
        User user1 = db.getUserWithUsername("test1");
        User user2 = db.getUserWithUsername("test2");
        db.addReview(user1, 4);
        db.addReview(user2, 1);
        user1 = db.getUserWithUsername("test1");
        user2 = db.getUserWithUsername("test2");
        assert user1.getReviews().size() == 1 && user1.getReviews().get(0) == 4;
        assert user2.getReviews().size() == 1 && user2.getReviews().get(0) == 1;
    }
}
package use_case.write_review;

import database.DatabaseController;
import database.GetUser;
import database.RegisterGatewayImplementation;
import entities.Cart;
import entities.Listing;
import entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReviewInteractorUnitTest {
    private static User user = new User(
            1, "user",
            "12345",
            "user@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    private static User reviewer = new User(
            2, "reviewer",
            "password",
            "reviewer@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    public static final DatabaseController db = new DatabaseController();

    @BeforeAll
    public static void setUp() throws IOException {
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
        if(Objects.isNull(new GetUser().getUserWithUsername(user.getUsername()))){
            new RegisterGatewayImplementation().createUser(user.getUsername(), user.getEmail(), user.getPassword());
        }
        if(Objects.isNull(new GetUser().getUserWithUsername(reviewer.getUsername()))){
            new RegisterGatewayImplementation().createUser(reviewer.getUsername(),
                    reviewer.getEmail(), reviewer.getPassword());
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
    void reviewInteractorUserExists() throws IOException {
        ReviewRequestModel userExistsRequestModel = new ReviewRequestModel(reviewer.getUsername(),
                user.getUsername(), 1);
        assert new ReviewInteractor(userExistsRequestModel).getMessage().equals("Review Successful");
        List<Integer> reviews = new GetUser().getUserWithUsername(user.getUsername()).getREVIEWS();
        assert reviews.get(reviews.size() - 1) == 1;
    }

    @Test
    void reviewInteractorUserDoesntExist() {
        ReviewRequestModel requestModel = new ReviewRequestModel(user.getUsername(),
                "UserThatShouldNeverExistDoNotRegister", 2);
        assert new ReviewInteractor(requestModel).getMessage().equals(
                "Review Unsuccessful: No User exists with this username");
    }
}

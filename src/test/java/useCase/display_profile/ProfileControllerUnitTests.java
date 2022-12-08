package useCase.display_profile;

import database.DatabaseController;
import database.GetUser;
import database.ReviewDatabaseGateway;
import entities.User;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileControllerUnitTests {
    public static final DatabaseController db = new DatabaseController();

    @BeforeAll
    public static void setUp() throws IOException {
        db.setListingTablePath("src/test/java/database/Listings.csv");
        db.setUserTablePath("src/test/java/database/Users.csv");
        File usersCSV = new File(db.getUserTablePath());
        usersCSV.createNewFile();
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
    void checkSetUp() {
        ReviewDatabaseGateway profileGateway = new GetUser();
        ProfileOutputBoundary profilePresenter = new ProfilePresenter();
        ProfileInputBoundary inputBoundary = new ProfileInteractor(profileGateway, profilePresenter);
        ProfileController profileController = new ProfileController(inputBoundary);

        assertNotNull(profileGateway);
        assertNotNull(profilePresenter);
        assertNotNull(profileController.getInputBoundary());
        assertNotNull(profileController);
        assert profileController.getInputBoundary() == inputBoundary;
    }

    @Test
    void checkSetUpUsingSetter() {
        ReviewDatabaseGateway profileGateway = new GetUser();
        ProfileOutputBoundary profilePresenter = new ProfilePresenter();
        ProfileInputBoundary inputBoundary = new ProfileInteractor(profileGateway, profilePresenter);
        ProfileController profileController = new ProfileController(null);
        profileController.setInputBoundary(inputBoundary);

        assertNotNull(profileGateway);
        assertNotNull(profilePresenter);
        assertNotNull(inputBoundary);
        assertNotNull(profileController);
        assert profileController.getInputBoundary() == inputBoundary;
    }


    @Test
    void createRequestEmpty() {
        String username = "";
        ReviewDatabaseGateway profileGateway = new GetUser();
        ProfileOutputBoundary profilePresenter = new ProfilePresenter();
        ProfileInputBoundary inputBoundary = new ProfileInteractor(profileGateway, profilePresenter);
        ProfileController profileController = new ProfileController(inputBoundary);
        try {
            profileController.createRequest(username);
            assert false;
        } catch (NoSuchUser e) {
            String message = e.getMessage();
            assert message.equals("This user does not exist.") || message.equals("Something went wrong.");
        }
    }

    @Test
    void createRequestSavedUser() {
        String username = "seller";
        ReviewDatabaseGateway profileGateway = new GetUser();
        ProfileOutputBoundary profilePresenter = new ProfilePresenter();
        ProfileInputBoundary inputBoundary = new ProfileInteractor(profileGateway, profilePresenter);
        ProfileController profileController = new ProfileController(inputBoundary);
        try {
            ProfileResponseModel output = profileController.createRequest(username);
            assert output.getUsername().equals(username);
            assert output.getEmail().equals("seller@seller.com");
            assert output.getRating() == 4.0;
            assert output.getReviews().equals(new ArrayList<>(List.of(4)));
        } catch (NoSuchUser e) {
            assert false;
        }
    }

    @Test
    void createRequestNonSavedUser() {
        String username = "Ethan";
        DatabaseController db = new DatabaseController();
        try {
            boolean userExistence = db.checkUserWithUsername(username);
            if (!userExistence) {
                ReviewDatabaseGateway profileGateway = new GetUser();
                ProfileOutputBoundary profilePresenter = new ProfilePresenter();
                ProfileInputBoundary inputBoundary = new ProfileInteractor(profileGateway, profilePresenter);
                ProfileController profileController = new ProfileController(inputBoundary);
                try {
                    profileController.createRequest(username);
                    assert false;
                } catch (NoSuchUser e2) {
                    String message = e2.getMessage();
                    assert message.equals("This user does not exist.") || message.equals("Something went wrong.");
                }
            }
        } catch (IOException e1) {
            assert false;
        }
    }
}

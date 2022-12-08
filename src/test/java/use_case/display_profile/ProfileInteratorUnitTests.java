package use_case.display_profile;

import com.opencsv.CSVWriter;
import database.DatabaseController;
import database.ReviewDatabaseGateway;
import entities.User;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileInteratorUnitTests {
    public static final DatabaseController db = new DatabaseController();
    @BeforeAll
    public static void setUp() throws IOException {
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
        User.setNextId(0);
    }
    @AfterAll
    public static void deleteCSVFiles() {
        File usersCSV = new File(db.getUserTablePath());
        usersCSV.delete();
    }
    @Test
    void checkSetUp() {
        ProfileOutputBoundary profilePresenter = new ProfilePresenter();
        ProfileInteractor interactor = new ProfileInteractor(db, profilePresenter);
        assertNotNull(db);
        assertNotNull(profilePresenter);
        assertNotNull(interactor);
        assert interactor.getGateway().equals(db);
        assert interactor.getOutputBoundary().equals(profilePresenter);
    }

    @Test
    void checkSetUpUsingSetter() {
        ProfileOutputBoundary profilePresenter = new ProfilePresenter();
        ProfileInteractor interactor = new ProfileInteractor(null, null);
        interactor.setGateway(db);
        interactor.setOutputBoundary(profilePresenter);
        assertNotNull(profilePresenter);
        assertNotNull(interactor);
        assert interactor.getGateway().equals(db);
        assert interactor.getOutputBoundary().equals(profilePresenter);
    }

    @Test
    void createRequestEmpty() {
        String username = "";
        ProfileOutputBoundary profilePresenter = new ProfilePresenter();
        ProfileInputBoundary inputBoundary = new ProfileInteractor(db, profilePresenter);
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
        String username = "Ethan";
        ProfileOutputBoundary profilePresenter = new ProfilePresenter();
        ProfileInputBoundary inputBoundary = new ProfileInteractor(db, profilePresenter);
        ProfileController profileController = new ProfileController(inputBoundary);
        try {
            FileWriter userCSV = new FileWriter(db.getUserTablePath());
            CSVWriter userWriter = new CSVWriter(userCSV, ';', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            List<String[]> userData = new ArrayList<>();
            userData.add(new String[]{"0", username, "password123", "Ethan@gmail.com", "[4,5,0]", "[0,1]", "[2,3]"});
            userWriter.writeAll(userData);
            userWriter.close();

            ProfileResponseModel output = profileController.createRequest(username);
            assert output.getUsername().equals(username);
            assert output.getEmail().equals("Ethan@gmail.com");
            assert output.getRating() == 3.0;
            assert output.getReviews().equals(new ArrayList<>(List.of(4, 5, 0)));
        } catch (NoSuchUser | IOException e) {
            assert false;
        }
    }

        @Test
        void createRequestNonSavedUser() {
            String username = "abcdefg";
            ProfileOutputBoundary profilePresenter = new ProfilePresenter();
            ProfileInputBoundary inputBoundary = new ProfileInteractor(db, profilePresenter);
            ProfileController profileController = new ProfileController(inputBoundary);
            try {
                profileController.createRequest(username);
                assert false;
            } catch (NoSuchUser e) {
                String message = e.getMessage();
                assert message.equals("This user does not exist.") || message.equals("Something went wrong.");
            }
        }
}


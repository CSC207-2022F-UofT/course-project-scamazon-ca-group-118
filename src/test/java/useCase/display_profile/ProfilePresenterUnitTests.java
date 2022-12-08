package useCase.display_profile;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProfilePresenterUnitTests {

    @Test
    void ProfilePresenterSetUp() {
        ProfilePresenter presenter = new ProfilePresenter();
        assertNotNull(presenter);
    }

    @Test
    void ProfilePresenterDisplayFailEmpty() {
        ProfilePresenter presenter = new ProfilePresenter();
        try {
            presenter.displayFail("");
            assert false;
        } catch (NoSuchUser e) {
            assert e.getMessage().equals("");
        }
    }

    @Test
    void ProfilePresenterDisplayFailNonEmpty() {
        ProfilePresenter presenter = new ProfilePresenter();
        String message = "This user does not exist.";
        try {
            presenter.displayFail(message);
            assert false;
        } catch (NoSuchUser e) {
            assert e.getMessage().equals(message);
        }
    }

    @Test
    void ProfilePresenterDisplaySuccessEmpty() {
        ProfilePresenter presenter = new ProfilePresenter();
        String username = "";
        String email = "";
        double rating = 0.0;
        ArrayList<Integer> reviews = new ArrayList<>();
        ProfileResponseModel responseModel = new ProfileResponseModel(username, email, rating, reviews);
        ProfileResponseModel result = presenter.displaySuccess(responseModel);

        assert result.getUsername().equals(username);
        assert result.getEmail().equals(email);
        assert result.getRating() == rating;
        assert result.getReviews().equals(reviews);
    }

    @Test
    void ProfilePresenterDisplaySuccessNonEmpty() {
        ProfilePresenter presenter = new ProfilePresenter();
        String username = "Harry";
        String email = "hairee@gmail.com";
        double rating = 4.9;
        ArrayList<Integer> reviews = new ArrayList<Integer>(List.of(5, 4, 2, 3, 4, 1));
        ProfileResponseModel responseModel = new ProfileResponseModel(username, email, rating, reviews);
        ProfileResponseModel result = presenter.displaySuccess(responseModel);

        assert result.getUsername().equals(username);
        assert result.getEmail().equals(email);
        assert result.getRating() == rating;
        assert result.getReviews().equals(reviews);
    }
}

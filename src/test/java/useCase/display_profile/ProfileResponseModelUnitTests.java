package useCase.display_profile;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


public class ProfileResponseModelUnitTests {

    @Test
    void ProfileResponseModelCreation() {
        ArrayList<Integer> reviews = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        ProfileResponseModel responseModel = new ProfileResponseModel("Ethan", "ethan3@hotmail.com",
                3, reviews);
        assert responseModel.getUsername().equals("Ethan");
        assert responseModel.getEmail().equals("ethan3@hotmail.com");
        assert responseModel.getRating() == 3;
        assert responseModel.getReviews().equals(reviews);
    }

    @Test
    void ProfileResponseModelCreationWithSetters() {
        ProfileResponseModel responseModel = new ProfileResponseModel(null, null, 0.0,
                null);
        ArrayList<Integer> reviews = new ArrayList<>(List.of(1));
        responseModel.setUsername("toronto123");
        responseModel.setEmail("NEWfie@yahoo.com");
        responseModel.setRating(5);
        responseModel.setReviews(reviews);
        assert responseModel.getUsername().equals("toronto123");
        assert responseModel.getEmail().equals("NEWfie@yahoo.com");
        assert responseModel.getRating() == 5;
        assert responseModel.getReviews().equals(reviews);
    }

}

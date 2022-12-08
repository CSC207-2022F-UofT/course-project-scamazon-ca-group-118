package useCase.display_profile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileRequestModelUnitTests {

    @Test
    void ProfileResponseModelCreation() {
        String username = "";
        ProfileRequestModel requestModel = new ProfileRequestModel(username);

        assertNotNull(requestModel);
        assert requestModel.getUsername().equals(username);
    }

    @Test
    void ProfileResponseModelCreationWithSetters() {
        String username = "hello789";
        ProfileRequestModel requestModel = new ProfileRequestModel(null);
        requestModel.setUsername(username);

        assertNotNull(requestModel);
        assert requestModel.getUsername().equals(username);
    }
}

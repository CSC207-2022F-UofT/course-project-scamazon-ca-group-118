package use_case.display_profile;
import database.ReviewDatabaseGateway;
import entities.User;

import java.io.IOException;

// This class is in the application business rules layer of clean architecture.

public class ProfileInteractor implements ProfileInputBoundary {
    // Note: I use ReviewDatabaseGateway because it has the same methods the ProfilePage needs.
    private final ReviewDatabaseGateway gateway;
    private final ProfileOutputBoundary output;
    // TODO do we need a User or UserFactory attribute here?

    /**
     * The ProfileInteractor constructor which assigns the gateway and output boundary needed to carry out the use case.
     * @param gateway The gateway to access the database for the necessary user information.
     * @param output The output boundary to pass the response model between layers.
     */
    public ProfileInteractor(ReviewDatabaseGateway gateway, ProfileOutputBoundary output) {
        this.gateway = gateway;
        this.output = output;
    }

    /**
     *
     * @param requestModel The request model instantiated by the user.
     * @return The ProfileResponseModel needed to display the profile page.
     */
    @Override
    public ProfileResponseModel create(ProfileRequestModel requestModel){
        try {
            String username = requestModel.getUsername();
            User user = gateway.getUserWithUsername(username);
            ProfileResponseModel responseModel = new ProfileResponseModel(username, user.getEmail(),
                    user.calculateRating(), "", user.getREVIEWS());
            return output.displaySuccess(responseModel);
        } catch (IOException error) {
            return output.displayFail("No such user exists with given username.");
        }

    }
}


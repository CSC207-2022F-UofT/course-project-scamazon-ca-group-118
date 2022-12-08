package useCase.display_profile;
import database.ReviewDatabaseGateway;
import entities.User;

import java.io.IOException;
import java.util.Objects;

// This class is in the application business rules layer of clean architecture.

/**
 * This is the ProfileInteractor for the display profile page use case which gets the user from the database using the
 * gateway and passing the response model returned to the profile presenter through the ProfileOutputBoundary.
 */
public class ProfileInteractor implements ProfileInputBoundary {
    // Note: I use ReviewDatabaseGateway because it has the same methods the ProfilePage needs, and it would
    // be redundant to have two gateways with the same methods.
    private final ReviewDatabaseGateway gateway;
    private final ProfileOutputBoundary outputBoundary;

    /**
     * The ProfileInteractor constructor which assigns the gateway and output boundary needed to carry out the use case.
     * @param gateway The gateway to access the database for the necessary user information.
     * @param outputBoundary The output boundary to pass the response model between layers.
     */
    public ProfileInteractor(ReviewDatabaseGateway gateway, ProfileOutputBoundary outputBoundary) {
        this.gateway = gateway;
        this.outputBoundary = outputBoundary;
    }

    /**
     * This uses the ProfileRequestModel and creates the ProfileResponseModel and passes it to the ProfilePresenter.
     * @param requestModel The request model instantiated by the user.
     * @return The ProfileResponseModel needed to display the profile page.
     */
    @Override
    public ProfileResponseModel create(ProfileRequestModel requestModel) {
        try {
            String username = requestModel.getUsername();
            User user = gateway.getUserWithUsername(username);
           /* TODO user.getProfilePic is not in user as of right now so we can add it as a field or not have a
               profile picture. */
            if (Objects.isNull(user)) {
                return outputBoundary.displayFail("This user does not exist.");
            } else {
                ProfileResponseModel responseModel = new ProfileResponseModel(username, user.getEmail(),
                        user.calculateRating(), user.getREVIEWS());
                return outputBoundary.displaySuccess(responseModel);
            }
        } catch (IOException error) {
            return outputBoundary.displayFail("Something went wrong.");
        }
    }

    /**
     * Gets the Gateway from this ProfileInteractor.
     * @return The ReviewDatabaseGateway associated with this ProfileController.
     */
    public ReviewDatabaseGateway getGateway() {
        return this.gateway;
    }

    /**
     * Gets the OutputBoundary from this ProfileInteractor.
     * @return The ProfileOutputBoundary associated with this ProfileInteractor.
     */
    public ProfileOutputBoundary getOutputBoundary() {
        return this.outputBoundary;
    }
}


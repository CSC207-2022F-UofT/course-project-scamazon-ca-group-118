package useCase.displayProfile;

// This interface is in the application business rules layer of clean architecture.

/**
 * This is the input boundary used for the display profile page use case which allows for abstraction between the
 * ProfileInteractor and ProfileController.
 */
public interface ProfileInputBoundary {

    /**
     * This creates a request for a profile page and passes it to the ProfileInteractor.
     * @param requestModel The request model for the profile page.
     * @return The response model received after passing the request model to the ProfileInteractor.
     */
    ProfileResponseModel create(ProfileRequestModel requestModel);
}

package use_case.display_profile;

// This class is in the interface adapters layer of clean architecture.

/**
 * This is the controller used for the display profile page use case which is used to pass the necessary request model
 * to the ProfileInteractor through the ProfileInputBoundary.
 */
public class ProfileController {
    private ProfileInputBoundary inputBoundary;

    /**
     * The constructor for the ProfileController which sets the given input boundary to the controller object.
     * @param inputBoundary The ProfileInputBoundary for calling the profile interactor.
     */
    public ProfileController(ProfileInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * This creates the request for the profile page, instantiated by the user with username.
     * @param username The ProfileResponseModel created by the profile interactor.
     * @return The ProfileResponseModel for passing the output of the profile interactor to the presenter.
     */
    public ProfileResponseModel createRequest(String username) {
        ProfileRequestModel requestModel = new ProfileRequestModel(username);
        return inputBoundary.create(requestModel);
    }

    /**
     * Gets the InputBoundary from this ProfileController.
     * @return The ProfileInputBoundary associated with this ProfileController.
     */
    public ProfileInputBoundary getInputBoundary() {
        return this.inputBoundary;
    }

    /**
     * Sets the InputBoundary of this ProfileController.
     * @param inputBoundary The new ProfileInputBoundary to update this ProfileController with.
     */
    public void setInputBoundary(ProfileInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }
}

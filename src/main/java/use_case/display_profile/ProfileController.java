package use_case.display_profile;

// This class is in the interface adapters layer of clean architecture.

public class ProfileController {
    private final ProfileInputBoundary input;

    /**
     * The constructor for the ProfileController which sets the given input boundary to the controller object.
     * @param input The ProfileInputBoundary for calling the profile interactor.
     */
    public ProfileController(ProfileInputBoundary input) {
        this.input = input;
    }

    /**
     * This creates the request for the profile page, instantiated by the user with username.
     * @param username The ProfileResponseModel created by the profile interactor.
     * @return The ProfileResponseModel for passing the output of the profile interactor to the presenter.
     */
    public ProfileResponseModel createRequest(String username) {
        ProfileRequestModel requestModel = new ProfileRequestModel(username);
        return input.create(requestModel);
    }
}

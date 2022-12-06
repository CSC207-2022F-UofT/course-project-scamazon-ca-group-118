package useCase.displayProfile;

// This class is in the interface adapters layer of clean architecture.

/**
 * This is the controller used for the display profile page use case which is used to pass the necessary request model
 * to the ProfileInteractor through the ProfileInputBoundary.
 */
public class ProfileController {
    private final ProfileInputBoundary INPUT;

    /**
     * The constructor for the ProfileController which sets the given input boundary to the controller object.
     * @param input The ProfileInputBoundary for calling the profile interactor.
     */
    public ProfileController(ProfileInputBoundary input) {
        this.INPUT = input;
    }

    /**
     * This creates the request for the profile page, instantiated by the user with username.
     * @param username The ProfileResponseModel created by the profile interactor.
     * @return The ProfileResponseModel for passing the output of the profile interactor to the presenter.
     */
    public ProfileResponseModel createRequest(String username) {
        ProfileRequestModel requestModel = new ProfileRequestModel(username);
        return INPUT.create(requestModel);
    }
}

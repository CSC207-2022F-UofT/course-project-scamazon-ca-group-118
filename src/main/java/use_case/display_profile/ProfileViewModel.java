package use_case.display_profile;

public class ProfileViewModel {
    private ProfileResponseModel output;

    /**
     * The constructor for the ProfileViewModel which sets the given response model to the view model object.
     *
     * @param output The ProfileResponseModel created by the profile interactor.
     */
    public ProfileViewModel(ProfileResponseModel output) {
        this.output = output;
    }

}

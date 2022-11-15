package useCase.displayProfile;

public class ProfileViewModel {
    private ProfileResponseModel output;

    /**
     * The constructor for the ProfileViewModel which sets the given response model to the view model object.
     * @param output The ProfileResponseModel created by the profile interactor.
     */
    public ProfileViewModel(ProfileResponseModel output) {
        this.output = output;
    }

    // TODO This class will combine all of the GUIs for a profile page and pass it to the view
    // I have yet to create the GUIs for profile page but, there will be one for displaying a list of reviews
    // (and listings if we want) and a GUI for the small data like username and rating, then I will combine them here
    // for the final profile page, using a builder style design pattern.
}

package use_case.display_profile;

// This interface is in the interface adapters layer of clean architecture.

/**
 * This is the presenter used for the display profile page use case which is used to pass the response model to the
 * view model/GUI for the profile page or raise an error if the response model wasn't able to be created.
 */
public class ProfilePresenter implements ProfileOutputBoundary {

    /**
     * Empty ProfilePresenter constructor.
     */
    public ProfilePresenter() {

    }

    /**
     * This simply returns the parameter and uses the output boundary to pass output data between layers.
     * This method serves as a check that we should prepare the profile page view.
     *
     * @param user The user who instantiated the use case.
     * @return The ProfileResponseModel that corresponds to the user that instantiated the use case.
     */
    @Override
    public ProfileResponseModel displaySuccess(ProfileResponseModel user) {
        return user;
    }

    /**
     * This returns the error message that caused the displaying of profile page to fail.
     *
     * @param error The error message to display.
     * @return The error message..
     */
    @Override
    public ProfileResponseModel displayFail(String error) {
        throw new NoSuchUser(error);
    }
}

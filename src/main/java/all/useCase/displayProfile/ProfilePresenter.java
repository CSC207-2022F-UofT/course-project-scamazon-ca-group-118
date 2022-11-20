package all.useCase.displayProfile;

// This interface is in the interface adapters layer of clean architecture.

public class ProfilePresenter implements ProfileOutputBoundary {

    /**
     * Empty ProfilePresenter constructor.
     */
    public ProfilePresenter() {

    }

    /**
     * This simply returns the parameter and uses the output boundary to pass output data between layers.
     * This methods serves as a check that we should prepare the profile page view.
     * @param user The user who instantiated the use case.
     * @return The ProfileResponseModel that corresponds to the user that instantiated the use case.
     */
    @Override
    public ProfileResponseModel displaySuccess(ProfileResponseModel user) {
        return user;
    }

    /**
     * This throws the exception that caused the displaying of profile page to fail.
     * @param error The error message to display.
     * @return The exception that caused the use case to fail.
     */
    @Override
    public ProfileResponseModel displayFail(String error) {
        throw new NoSuchUser(error);
    }

}

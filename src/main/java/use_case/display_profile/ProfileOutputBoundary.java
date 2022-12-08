package use_case.display_profile;

// This interface is in the application business rules layer of clean architecture.

/**
 * This is the output boundary used for the display profile page use case which allows for abstraction between the
 * ProfileInteractor and ProfilePresenter.
 */
public interface ProfileOutputBoundary {

    /**
     * This method is called when the ProfileResponseModel is successfully created.
     * @param responseModel The response model received from the gateway in the profile interactor.
     * @return The response model to display.
     */
    ProfileResponseModel displaySuccess(ProfileResponseModel responseModel);

    /**
     * This method is called when there is an error in creating the ProfileResponseModel.
     * @param error The error message to be displayed.
     * @return The response model to display
     */
    ProfileResponseModel displayFail(String error);
}

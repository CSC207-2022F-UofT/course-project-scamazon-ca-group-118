package all.useCase.displayProfile;

// This interface is in the application business rules layer of clean architecture.

public interface ProfileOutputBoundary {

    ProfileResponseModel displaySuccess(ProfileResponseModel user);

    ProfileResponseModel displayFail(String error);
}

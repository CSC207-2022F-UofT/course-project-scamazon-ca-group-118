package all.useCase.displayProfile;

// This interface is in the application business rules layer of clean architecture.

public interface ProfileInputBoundary {

    public ProfileResponseModel create(ProfileRequestModel requestModel);
}

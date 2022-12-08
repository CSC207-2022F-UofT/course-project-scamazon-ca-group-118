package use_case.create_listing;

import java.io.IOException;

/**
 * Represents a ListingResponseModel object that stores data retrieved from the ReviewInteractor that must
 * be passed to the ListingPresenter
 */
public class ListingResponseModel {
    private String message;

    /**
     * The constructor for ReviewResponseModel
     *
     * @param requestModel the ReviewRequestModel that will be used as an argument for the ReviewInteractor
     *                     that this ReviewResponseModel retrieves data from
     */
    public ListingResponseModel(ListingRequestModel requestModel) throws IOException {
        ListingInteractor interactor = new ListingInteractor(requestModel);
        this.message = interactor.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

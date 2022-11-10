package writeReview;

/**
 * Represents a ReviewResponseModel object that stores data retrieved from the ReviewInteractor that must
 * be passed to the ReviewPresenter
 */
public class ReviewResponseModel {
    private String message;

    /**
     * The constructor for ReviewResponseModel
     *
     * @param requestModel the ReviewRequestModel that will be used as an argument for the ReviewInteractor
     *                     that this ReviewResponseModel retrieves data from
     */
    public ReviewResponseModel(ReviewRequestModel requestModel) {
        ReviewInteractor interactor = new ReviewInteractor(requestModel);
        this.message = interactor.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

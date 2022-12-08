package useCase.writeReview;


/**
 * Represents a ReviewResponseModel object that stores data retrieved from the ReviewInteractor that must
 * be passed to the ReviewPresenter
 */
public class ReviewResponseModel {
    private String message;
    private final ReviewInteractor INTERACTOR;

    /**
     * The constructor for ReviewResponseModel
     *
     * @param requestModel the ReviewRequestModel that will be used as an argument for the ReviewInteractor
     *                     that this ReviewResponseModel retrieves data from
     */
    public ReviewResponseModel(ReviewRequestModel requestModel){
        this.INTERACTOR = new ReviewInteractor(requestModel);
    }

    public String getMessage() {
        this.message = INTERACTOR.getMessage();
        return message;
    }

    public ReviewInteractor getInteractor(){
        return INTERACTOR;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

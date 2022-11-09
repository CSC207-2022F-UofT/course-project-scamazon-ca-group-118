package writeReview;
import forms.ReviewForm;

/**
 * The presenter class for writing a review
 */
public class ReviewPresenter {
    private String message;

    /**
     * The constructor for the ReviewPresenter
     * @param form the form from which this presenter retrieves a message
     */
    public ReviewPresenter(ReviewForm form){
        this.message  = form.getMessage();
    }

    /**
     * @return a message that communicates whether the review was successfully created
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

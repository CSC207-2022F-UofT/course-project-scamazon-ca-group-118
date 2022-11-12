package createListing;

import forms.CreateListingForm;

/**
 * The presenter class for creating a listing
 */
public class ListingPresenter {
    private String message;
    /**
     * The constructor for the ReviewPresenter
     *
     * @param form the form from which this presenter retrieves a message
     */
    public ListingPresenter(CreateListingForm form){
        this.message = form.getMessage();
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

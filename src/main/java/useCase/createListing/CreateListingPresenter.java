package useCase.createListing;

import forms.CreateListingForm;

import java.io.IOException;

/**
 * The presenter class for creating a listing
 */
public class CreateListingPresenter {
    private String message;
    /**
     * The constructor for the ListingPresenter
     *
     * @param form the form from which this presenter retrieves a message
     */
    public CreateListingPresenter(CreateListingForm form) throws IOException {
        this.message = form.getMessage();
    }

    /**
     * @return a message that communicates whether the listing was successfully created
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

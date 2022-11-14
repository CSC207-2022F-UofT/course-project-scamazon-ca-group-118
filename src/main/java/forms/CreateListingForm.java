package forms;

import useCase.createListing.ListingRequestModel;
import useCase.createListing.ListingResponseModel;
import database.GetUser;
import database.UserExists;


import java.util.List;

public class CreateListingForm extends Form {


    private final String SELLER_USERNAME;
    private final String LISTING_TITLE;
    private final float PRICE;
    private final String DESCRIPTION;
    private final List<String> IMAGES;
    private final int ID;
    ListingResponseModel responseModel;


    public CreateListingForm(String listingTitle
            , float price, String seller, String description, List<String> images, int id) {
        super("Create a listing");
        LISTING_TITLE = listingTitle;
        PRICE = price;
        SELLER_USERNAME = seller;
        DESCRIPTION = description;
        IMAGES = images;
        ID = id;
    }


    @Override
    protected boolean validateForm() {
        /*
        We will have a description limit of 1000 characters
         */
        if (PRICE >= 0 && DESCRIPTION.length() < 1000 && new UserExists(new GetUser().getUserWithUsername(SELLER_USERNAME)).checkExists()) {
            return true;
        } else {
            return false;
        }


    }

    @Override
    protected void submitForm() {
        if (this.validateForm()) {
            ListingRequestModel requestModel = new ListingRequestModel(SELLER_USERNAME, LISTING_TITLE, PRICE, DESCRIPTION, IMAGES);
            responseModel = new ListingResponseModel(requestModel);
        }
    }

    public String getMessage() {
        this.submitForm();
        if (this.validateForm()) {
            return responseModel.getMessage();
        } else {
            return "Please ensure all details are correct before submitting the form";
        }

    }
}

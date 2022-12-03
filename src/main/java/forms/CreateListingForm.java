package forms;

import entities.User;
import useCase.createListing.ListingRequestModel;
import useCase.createListing.ListingResponseModel;
import database.GetUser;
import database.UserExists;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class CreateListingForm extends Form {


    private final User SELLER_USER;
    private final String LISTING_TITLE;
    private final String PRICE;
    private final String DESCRIPTION;
    private final String IMAGE;
    private  float FLOAT_PRICE;
    //private final int ID;
    ListingResponseModel responseModel;


    public CreateListingForm(String listingTitle
            , String price, User seller, String description, String images) {
        super("Create a listing");
        LISTING_TITLE = listingTitle;
        PRICE = price;
        SELLER_USER = seller;
        DESCRIPTION = description;
        IMAGE = images;
        //ID = id
    }


    @Override
    protected boolean validateForm() throws IOException {
        /*
        We will have a description limit of 1000 characters
         */
        try{
            DecimalFormat df = new DecimalFormat("0.00");
            float listingPrice = Float.parseFloat(df.format(Float.parseFloat(PRICE)));
            FLOAT_PRICE = listingPrice;
        }
        catch(Exception e){
            return false;
        }
        if (FLOAT_PRICE >= 0.0 && DESCRIPTION.length() < 1000 && LISTING_TITLE.length() > 0) {
            return true;
        } else {
            return false;
        }


    }

    @Override
    protected void submitForm() throws IOException {
        if (this.validateForm()) {
            ListingRequestModel requestModel = new ListingRequestModel(SELLER_USER, LISTING_TITLE, FLOAT_PRICE, DESCRIPTION, IMAGE);
            responseModel = new ListingResponseModel(requestModel);
        }
    }

    public String getMessage() throws IOException {
        this.submitForm();
        if (this.validateForm()) {
            return responseModel.getMessage();
        } else {
            return "Please ensure all details are correct before submitting the form";
        }

    }
}

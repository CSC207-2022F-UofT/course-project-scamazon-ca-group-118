package forms;

import entities.User;
import use_case.create_listing.ListingRequestModel;
import use_case.create_listing.ListingResponseModel;


import java.io.IOException;
import java.text.DecimalFormat;

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
            FLOAT_PRICE = Float.parseFloat(df.format(Float.parseFloat(PRICE)));
        }
        catch(Exception e){
            return false;
        }
        return FLOAT_PRICE >= 0.0 && DESCRIPTION.length() < 1000 && LISTING_TITLE.length() > 0;


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

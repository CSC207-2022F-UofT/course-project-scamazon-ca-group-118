package forms;

import entities.User;
import features.Listing;


import java.util.List;

public class CreateListingForm extends Form {


    private final User seller;
    private float price;
    private String description;
    private List<String> images;
    private int id;


    public CreateListingForm(String title
            , float price, User seller, String description, List<String> images, int id) {
        super(title);
        this.price = price;
        this.seller = seller;
        this.description = description;
        this.images = images;
        this.id = id;
    }


    public void submitListing() {
        // TODO: This function will be called on the "submit" button click
        // This will add a listing to the database using the database interactor method.
        String title = this.getTitle();
        Listing L = new Listing(title, id, price, seller, description, images);

        // addListing(L);
    }

    @Override
    protected boolean validateForm() {
        /*
        We will have a description limit of 1000 characters
         */
        if (price >= 0 && description.length() < 1000) {// TODO: Check that the seller exists in the database
            return true;
        } else {
            return false;
        }


    }

    @Override
    protected void submitForm() {
    }
}

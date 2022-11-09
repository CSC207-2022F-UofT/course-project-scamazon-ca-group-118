package forms;

import entities.User;
import features.Listing;

public class CreateListingForm extends Form {


    private final User seller;
    private float price;
    private String description;
    private String[] images;

    private static int id = 0;

    public CreateListingForm(String title, float price, User seller, String description, String[] images) {
        super(title);
        this.price = price;
        this.seller = seller;
        this.description = description;
        this.images = images;
    }


    public void submitListing() {
        // This function will be called on the "submit" button click
        // This will add a listing to the databse using the database interactor method.
        String title = this.getTitle();
        Listing L = new Listing(title, id, price, seller, description, images);
        id++;

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

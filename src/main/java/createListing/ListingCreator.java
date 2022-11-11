package createListing;

import entities.User;
import java.util.List;
import features.Listing;


/**
 * Represents a listingCreator object, to create listings
 */
public class ListingCreator {
    /**
     * constructor for listingCreator class
     */
    public ListingCreator(){
    }
    /**
     * createListing takes in the seller, title, price, description, and a list of images to create a listing object
     * with the specified values, then adds the listing to the sellers list of listings
     *
     * @param seller the seller of the listing
     * @param title the title of the listing
     * @param price the price of the listing
     * @param description the description of the listing
     * @param images a list of all the file paths to the images
     */
    public void createListing(User seller, String title, float price, String description, List<String> images){
        Listing listing = new Listing(seller, title, price, description, images);
        seller.addListing(listing);

    }

}

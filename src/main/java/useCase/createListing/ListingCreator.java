package useCase.createListing;

import entities.User;

import java.util.List;

import entities.Listing;
import database.DatabaseController;

import javax.xml.crypto.Data;
import java.time.LocalDate;
/**
 * Represents a listingCreator object, to create listings
 */
public class ListingCreator {
    /**
     * constructor for listingCreator class
     */
    public ListingCreator() {
    }

    /**
     * createListing takes in the seller, title, price, description, and a list of images to create a listing object
     * with the specified values, then adds the listing to the sellers list of listings
     *
     * @param seller       the seller of the listing
     * @param listingTitle the title of the listing
     * @param price        the price of the listing
     * @param description  the description of the listing
     * @param image        the file path to the listing image
     */
    public void createListing(User seller, String listingTitle, float price, String description, String image) {
        Listing listing = new Listing(seller, listingTitle, price, description, image);
        seller.addListing(listing);
        DatabaseController db = new DatabaseController();
        db.createListing(seller.getUsername(), listingTitle, price, LocalDate.now(), description, image);

    }

}

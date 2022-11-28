package useCase.createListing;

import entities.User;

import database.GetUser;
import database.UserExists;

import java.io.IOException;
import java.util.List;

public class ListingInteractor {
    private User seller;
    private String listingTitle;
    private float price;
    private String description;
    private String image;



    private boolean userExists(User user) {
        return new UserExists(user).checkExists();
    }

    /**
     * The constructor for the ListingInteractor class
     *
     * @param requestModel the request model that's data will be manipulated
     */
    public ListingInteractor(ListingRequestModel requestModel) throws IOException {
        this.seller = requestModel.getSeller();
        this.listingTitle = requestModel.getTitle();
        this.price = requestModel.getPrice();
        this.description = requestModel.getDescription();
        this.image = requestModel.getImage();

    }

    private void createListing() {
        this.seller.createListing(listingTitle, price, description, image);
    }

    /**
     * Determines whether a listing was created
     *
     * @return a string indicating whether the listing was created
     */
    public String getMessage() {
        if (userExists(seller)) {
            this.createListing();
            return "Listing created";
        } else {
            return "Unable to create listing";
        }
    }

    public User getSeller() {
        return seller;
    }

    public String getTitle() {
        return listingTitle;
    }

    public float getPrice() {
        return this.price;
    }

    public String getDescription() {
        return description;
    }

    public String getImages() {
        return image;
    }

    public void setTitle(String listingTitle) {
        this.listingTitle = listingTitle;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

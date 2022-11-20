package useCase.createListing;

import entities.User;

import database.GetUser;
import database.UserExists;

import java.util.List;

public class ListingInteractor {
    private User seller;
    private String listingTitle;
    private float price;
    private String description;
    private List<String> images;


    /**
     * Returns the user from the database with the given username
     *
     * @param username the username being searched for
     * @return the user with the given username
     */
    private User getUserWithUsername(String username) {
        return new GetUser().getUserWithUsername(username);
    }

    private boolean userExists(User user) {
        return new UserExists(user).checkExists();
    }

    /**
     * The constructor for the ListingInteractor class
     *
     * @param requestModel the request model that's entities.data will be manipulated
     */
    public ListingInteractor(ListingRequestModel requestModel) {
        this.seller = getUserWithUsername(requestModel.getSellerUsername());
        this.listingTitle = requestModel.getTitle();
        this.price = requestModel.getPrice();
        this.description = requestModel.getDescription();
        this.images = requestModel.getImages();

    }

    private void createListing() {
        this.seller.createListing(listingTitle, price, description, images);
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

    public List<String> getImages() {
        return images;
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

    public void setImages(List<String> images) {
        this.images = images;
    }
}

package useCase.createListing;

import entities.User;

import java.util.List;

/**
 * Represents a ListingRequestModel object that holds all entities.data to be passed to the ListingInteractor
 */
public class ListingRequestModel {
    private String sellerUsername;
    private String listingTitle;
    private float price;
    private String description;
    private List<String> images;

    /**
     * Constructor for a ListingRequestModel
     *
     * @param sellerUsername the seller username of the listing
     * @param listingTitle   the title of the listing
     * @param price          the price of the listing
     * @param description    the description of the listing
     * @param images         a list of all the file paths to the images
     */
    public ListingRequestModel(String sellerUsername, String listingTitle, float price, String description, List<String> images) {
        this.sellerUsername = sellerUsername;
        this.listingTitle = listingTitle;
        this.price = price;
        this.description = description;
        this.images = images;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public String getTitle() {
        return listingTitle;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
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
    // TODO: Implement set images
}

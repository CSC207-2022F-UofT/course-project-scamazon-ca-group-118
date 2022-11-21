package useCase.createListing;

import entities.User;

import java.util.List;

/**
 * Represents a ListingRequestModel object that holds all data to be passed to the ListingInteractor
 */
public class ListingRequestModel {
    private String sellerUsername;
    private String listingTitle;
    private float price;
    private String description;
    private String image;

    /**
     * Constructor for a ListingRequestModel
     *
     * @param sellerUsername the seller username of the listing
     * @param listingTitle   the title of the listing
     * @param price          the price of the listing
     * @param description    the description of the listing
     * @param image          the file path to the listing image
     */
    public ListingRequestModel(String sellerUsername, String listingTitle, float price, String description, String image) {
        this.sellerUsername = sellerUsername;
        this.listingTitle = listingTitle;
        this.price = price;
        this.description = description;
        this.image = image;
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

    public String getImage() {
        return image;
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
}

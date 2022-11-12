package createListing;

import entities.User;

import java.util.List;

/**
 * Represents a ListingRequestModel object that holds all data to be passed to the ListingInteractor
 */
public class ListingRequestModel {
    private String sellerUsername;
    private String title;
    private float price;
    private String description;
    private List<String> images;

    /**
     * Constructor for a ListingRequestModel
     *
     * @param sellerUsername      the seller username of the listing
     * @param title       the title of the listing
     * @param price       the price of the listing
     * @param description the description of the listing
     * @param images      a list of all the file paths to the images
     */
    public ListingRequestModel(String sellerUsername, String title, float price, String description, List<String> images) {
        this.sellerUsername = sellerUsername;
        this.title = title;
        this.price = price;
        this.description = description;
        this.images = images;
    }

    public String getSellerUsername(){
        return sellerUsername;
    }
    public String getTitle(){
        return title;
    }

    public float getPrice() {
        return price;
    }
    public String getDescription(){
        return description;
    }

    public List<String> getImages(){
        return images;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // TODO: Implement set images
}

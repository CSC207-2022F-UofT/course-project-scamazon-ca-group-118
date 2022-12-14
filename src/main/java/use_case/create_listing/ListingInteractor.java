package use_case.create_listing;

import entities.User;

import java.io.IOException;

public class ListingInteractor {
    private final User SELLER;
    private String listingTitle;
    private float price;
    private String description;
    private String image;


    /**
     * The constructor for the ListingInteractor class
     *
     * @param requestModel the request model that's data will be manipulated
     */
    public ListingInteractor(ListingRequestModel requestModel){
        this.SELLER = requestModel.getSeller();
        this.listingTitle = requestModel.getTitle();
        this.price = requestModel.getPrice();
        this.description = requestModel.getDescription();
        this.image = requestModel.getImage();

    }

    private void createListing() throws IOException {
        this.SELLER.createListing(listingTitle, price, description, image);
    }

    /**
     * Determines whether a listing was created
     *
     * @return a string indicating whether the listing was created
     */
    public String getMessage() {
        try {
            this.createListing();
            return "Listing Created!";
        } catch (Exception e) {

            return "There was an error (are you logged in?)";
        }

    }

    public User getSeller() {
        return SELLER;
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

package createListing;

import entities.User;

import database.GetUser;
import database.UserExists;
import java.util.List;

public class ListingInteractor {
    private User seller;
    private String title;
    private float price;
    private String description;
    private List<String> images;


    /**
     * Returns the user from the database with the given username
     *
     * @param username the username being searched for
     * @return the user with the given username
     */
    private User getUserWithUsername(String username){
        return new GetUser().getUserWithUsername(username);
    }

    private boolean userExists(User user){
        return new UserExists(user).checkExists();
    }
    /**
     * The constructor for the ListingInteractor class
     *
     * @param requestModel the request model that's data will be manipulated
     */
    public ListingInteractor(ListingRequestModel requestModel) {
        this.seller = getUserWithUsername(requestModel.getSellerUsername());
        this.title = requestModel.getTitle();
        this.price = requestModel.getPrice();
        this.description = requestModel.getDescription();
        this.images = requestModel.getImages();

    }
    private void createListing(){
        this.seller.createListing(title, price, description, images);
    }

    public String getMessage(){
        return null;
    }
}

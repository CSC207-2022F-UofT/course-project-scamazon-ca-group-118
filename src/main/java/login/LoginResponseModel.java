package login;
import features.Cart;
import features.Listing;
import features.Review;

import java.util.List;

/**
 * The loginResponseModel holds data retrieved from the interactor (in this case, LoginInteractor). Namely, a
 * username, password, id, email, reviews, listings, and cart
 */
public class LoginResponseModel {
    private String username;
    private String password;
    private int id;
    private String email;
    private List<Review> reviews;
    private List<Listing> listings;
    private Cart cart;

    /**
     * A constructor for the LoginResponseModel
     * @param requestModel the request model to be fed into the LoginInteractor that will generate the
     *                     necessary data for this LoginResponseModel
     */
    public LoginResponseModel(LoginRequestModel requestModel){
        LoginInteractor interactor =
                new LoginInteractor(requestModel.getUsername(), requestModel.getEnteredPassword());
        if(interactor.shouldLogin()) {
            this.username = interactor.getUser().getUsername();
            this.password = interactor.getUser().getPassword();
            this.id = interactor.getUser().getID();
            this.email = interactor.getUser().getEmail();
            this.reviews = interactor.getUser().getReviews();
            this.listings = interactor.getUser().getListings();
            this.cart = interactor.getUser().getCart();
        }
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public Cart getCart() {
        return cart;
    }


    public void setUsername(String username){
        this.username = username;
    }

}


package useCase.checkout;

/**
 * Represents a CheckoutRequestModel object that holds all data to be passed to the CheckoutInteractor
 */
public class CheckoutRequestModel {
    private String username;

    /**
     * Constructor for a CheckoutRequestModel
     *
     * @param username the username of the buyer User
     */
    public CheckoutRequestModel(String username) {
        this.username = username;
    }


    public String getUsername() {return username;}

}

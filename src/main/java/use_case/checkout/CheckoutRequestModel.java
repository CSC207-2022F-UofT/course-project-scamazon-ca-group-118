package use_case.checkout;

/**
 * Represents a CheckoutRequestModel object that holds all data to be passed to the CheckoutInteractor
 */
public class CheckoutRequestModel {
    private final String USERNAME;

    /**
     * Constructor for a CheckoutRequestModel
     *
     * @param username the username of the buyer User
     */
    public CheckoutRequestModel(String username) {
        this.USERNAME = username;
    }


    public String getUsername() { return USERNAME; }

}

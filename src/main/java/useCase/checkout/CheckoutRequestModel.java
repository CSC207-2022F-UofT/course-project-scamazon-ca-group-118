package useCase.checkout;

/**
 * Represents a CheckoutRequestModel object that holds all data to be passed to the CheckoutInteractor
 */
public class CheckoutRequestModel {
    private final String buyerUsername;

    /**
     * Constructor for a CheckoutRequestModel
     *
     * @param buyerUsername the username of the buyer User
     */
    public CheckoutRequestModel(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }
}

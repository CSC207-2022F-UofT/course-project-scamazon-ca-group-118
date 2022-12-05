package useCase.checkout;

import entities.User;

/**
 * Represents a CheckoutRequestModel object that holds all data to be passed to the CheckoutInteractor
 */
public class CheckoutRequestModel {
    private User buyer;

    /**
     * Constructor for a CheckoutRequestModel
     *
     * @param buyer User who is checking out items
     */
    public CheckoutRequestModel(User buyer) {
        this.buyer = buyer;
    }

    public User getBuyer() {
        return buyer;
    }
}

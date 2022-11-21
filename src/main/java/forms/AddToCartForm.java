package forms;

import useCase.addToCart.AddToCartRequestModel;
import useCase.addToCart.AddToCartResponseModel;
import database.GetUser;
import entities.Listing;

import java.io.IOException;

public class AddToCartForm extends Form {
    private final String BUYER_USERNAME;

    private final Listing LISTING;

    private final boolean CAN_ADD_TO_CART;

    private AddToCartResponseModel responseModel;

    public AddToCartForm(String buyerUsername, Listing listing) throws IOException {
        super("Add to cart");
        this.BUYER_USERNAME = buyerUsername;
        this.LISTING = listing;
        // does the listing exist in the buyer's cart already
        this.CAN_ADD_TO_CART = !(new GetUser().getUserWithUsername(buyerUsername).getCart().getItems().contains(listing));// TODO is listing in current user's cart already?
    }


    @Override
    protected boolean validateForm() {
        return this.CAN_ADD_TO_CART;
    }

    @Override
    protected void submitForm() throws IOException {
        if (this.validateForm()) {
            AddToCartRequestModel requestModel = new AddToCartRequestModel(this.BUYER_USERNAME, this.LISTING, this.CAN_ADD_TO_CART);
            this.responseModel = new AddToCartResponseModel(requestModel);
            // TODO: how do we now navigate back to the ListingListPage?
        }
    }

    public String getMessage() throws IOException {
        this.submitForm();
        if (this.validateForm()) {
            return responseModel.getMessage();
        } else {
            return "This item is already in your cart";
        }
    }

    public boolean getCanAddToCart() {
        return this.CAN_ADD_TO_CART;
    }
}

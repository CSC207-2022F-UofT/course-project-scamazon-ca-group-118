package pages;

import features.Cart;
import features.Listing;

import java.util.ArrayList;

public class CartPage extends Page {

    private Cart cart;

    /**
     * Constructor for the Cart
     *
     * @param title    The title of the page.
     * @param itemCart A cart of Listing items.
     */
    public CartPage(String title, Cart itemCart) {
        super(title);
        //TODO Make title be "(User)'s Cart"
        this.cart = itemCart;
    }

    /**
     * Get a list of all the items in the cart.
     *
     * @return An ArrayList of Listings in the cart.
     */
    public ArrayList<Listing> getCartItems() {
        return this.cart.getItems();
    }

    /**
     * Get the price of all the items in the cart.
     *
     * @return An int representing the total price of all items in cart.
     */
    public int getCartPrice() {
        return this.cart.getPrice();
    }

    /**
     * Set the cart to be a different instance of Cart.
     *
     * @param newCart An instance of Cart.
     */
    public void setCart(Cart newCart) {
        this.cart = newCart;
    }

    //Hard to implement the logic here without the GUI and other people's logic being
    //complete so consider this a rough draft until further notice.
    public Page goToCheckout() {
        //NOT IMPLEMENTED, WARRANTS FURTHER DISCUSSION
        //TODO Implement with the GUI after figuring out View thing
    }
}

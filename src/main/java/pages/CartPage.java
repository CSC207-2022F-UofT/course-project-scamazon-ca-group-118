package pages;

import features.Cart;
import features.Listing;

import java.util.ArrayList;

public class CartPage extends Page{

    //Hard to implement the logic here without the GUI and other people's logic being
    //complete so consider this a rough draft until further notice.
    private Cart cart;

    public CartPage(String title, Cart itemCart){
        super(title);
        this.cart = itemCart;
    }

    //returns a list of the listings in the cart
    public ArrayList<Listing> getCartItems(){
        return this.cart.getItems();
    }

    //get the total price of all items in the cart
    public int getCartPrice(){
        return this.cart.getPrice();
    }

    //set the cart to a new cart
    public void setCart(Cart newCart){
        this.cart = newCart;
    }

    public Page goToCheckout(){
        //NOT IMPLEMENTED, WARRANTS FURTHER DISCUSSION
    }
}

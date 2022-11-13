package pages;

import features.Cart;
import features.Listing;

import javax.swing.*;
import java.awt.*;
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

    public void render() {
        JFrame frame = new JFrame(this.getTitle());
        frame.setLayout(new FlowLayout());
        frame.setSize(1280, 720);
        JButton b = new JButton("Checkout");
        JTable t = new JTable(cart.countItems(), 2);
        t.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane j = new JScrollPane(t);
        j.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        t.getColumnModel().getColumn(0).setHeaderValue("Items");
        t.getColumnModel().getColumn(1).setHeaderValue("Price");
        ArrayList<Listing> items = this.getCartItems();
        for (int i = 0; i < cart.countItems(); i++) {
            t.setValueAt(items.get(i).getTitle(), i, 0);
            t.setValueAt(items.get(i).getPrice(), i, 1);
        }
        t.setBounds(190, 100, 900, cart.countItems() * 75);
        frame.add(j);
        frame.add(b);
        frame.setVisible(true);

    }

    //OVERLOADED
    public void render(JFrame frame) {
        frame.removeAll();
        frame.setLayout(new FlowLayout());
        frame.setSize(1280, 720);
        JButton b = new JButton("Checkout");
        JTable t = new JTable(cart.countItems(), 2);
        t.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane j = new JScrollPane(t);
        j.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        t.getColumnModel().getColumn(0).setHeaderValue("Items");
        t.getColumnModel().getColumn(1).setHeaderValue("Price");
        ArrayList<Listing> items = this.getCartItems();
        for (int i = 0; i < cart.countItems(); i++) {
            t.setValueAt(items.get(i).getTitle(), i, 0);
            t.setValueAt(items.get(i).getPrice(), i, 1);
        }
        t.setBounds(190, 100, 900, cart.countItems() * 75);
        frame.add(j);
        frame.add(b);
        frame.setVisible(true);
    }

    //Hard to implement the logic here without the GUI and other people's logic being
    //complete so consider this a rough draft until further notice.
    public Page goToCheckout() {
        //NOT IMPLEMENTED, WARRANTS FURTHER DISCUSSION
        //TODO Implement with the GUI after figuring out View thing
    }
}

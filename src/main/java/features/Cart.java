package features;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private ArrayList<Listing> listings;

    /** A Constructor for Cart that sets contents of the cart to be itemListings.
     *
     * @param itemListings an ArrayList of unique Listings.
     */
    public Cart(ArrayList<Listing> itemListings){
        this.listings = itemListings;
    }

    //OVERLOADED
    /** A Constructor for Cart that takes no arguments*/
    public Cart(){
        this.listings = new ArrayList<Listing>();
    }


    /** Get all Listings in the cart.
     *
     * @return A shallow copy of the list of Listings within Cart (An ArrayList of Listings).
     */
    public ArrayList<Listing> getItems(){
        ArrayList<Listing> copied_listings = new ArrayList<Listing>();
        for (Listing listing : this.listings){
            copied_listings.add(listing);
        }
        return copied_listings;
    }

    /** Adds item to the cart if item isn't in it already.
     *
     * @param item A Listing to be added to this.listings.
     * @return A boolean indicating whether item was successfully added to the cart.
     */
    public boolean addItem(Listing item){
        if (this.listings.contains(item)){
            return false;
        } else {
            this.listings.add(item);
            return true;
        }
    }

    /** We assume item appears only once in the cart and we remove it.
     *
     * @param item A Listing that appears in this.listings once and only once
     */
    public void removeItem(Listing item){
        this.listings.remove(item);
    }

    //OVERLOADED
    /** We remove the Listing at index num in the cart.
     *
     * @param num An int, representing an index that exists in this.listings.
     */
    public void removeItem(int num){
        this.listings.remove(num);
    }

    /** The contents of the cart are reassigned to be list.
     *
     * @param list An ArrayList made up of Listings.
     */
    public void setItems(ArrayList<Listing> list){
        this.listings = list;
    }

    /** We return the price of all items in the cart combined.
     *
     * @return The total price of all items in the cart.
     */
    public int getPrice(){
        int total_price = 0;
        for (Listing item : this.listings){
            total_price += item.getPrice();
        }
        return total_price;
    }

}

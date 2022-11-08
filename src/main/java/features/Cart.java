package features;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private ArrayList<Listing> listings;

    public Cart(ArrayList<Listing> item_listings){
        this.listings = item_listings;
    }

    //Overloading since I don't know how it will be used.
    public Cart(){
        this.listings = new ArrayList<Listing>();
    }

    //We make a shallow copy of listings.
    public ArrayList<Listing> getItems(){
        ArrayList<Listing> copied_listings = new ArrayList<Listing>();
        for (Listing listing : this.listings){
            copied_listings.add(listing);
        }
        return copied_listings;
    }

    //If the item is already in the cart we return false.
    // else if listing is not in cart and has been added properly we return "true".
    public boolean addItem(Listing item){
        if (this.listings.contains(item)){
            return false;
        } else {
            this.listings.add(item);
            return true;
        }
    }

    //precondition: there is 1 and only 1 instance of item in this.listings
    public void removeItem(Listing item){
        this.listings.remove(item);
    }

    //Overloading the method for indexes.
    public void removeItem(int num){
        this.listings.remove(num);
    }

    //No clue how this would be used but implemented it anyway just in case
    public void setItems(ArrayList<Listing> list){
        this.listings = list;
    }

    public int getPrice(){
        int total_price = 0;
        for (Listing item : this.listings){
            total_price += item.getPrice();
        }
        return total_price;
    }

}

package features;
import entities.User;

import java.time.*; // to store date
public class Listing {
    String title;
    int id; // String or int -> dependent on database implementation
    LocalDate ld; // Represents a date (year, month, day (yyyy-MM-dd))
    float price;
    User seller;
    String description;
    String [] images;
    public Listing(String title, int id, float price, User seller, String description, String[] images){
        this.title = title;
        this.id = id;
        this.ld = LocalDate.now(); // assigns the localDate with the current time
        this.price = price;
        this.seller = seller;
        this.description = description;
        this.images = images;
    }
    public float getPrice() {
        return price;
    }
    public String getDescription(){
        return description;
    }
    public String getImage(int imageIndex){
        if (imageIndex >= images.length){
            return "No Image";
        }
        else{
            return images[imageIndex];
        }
    }





}

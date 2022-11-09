package features;

import entities.User;

import java.time.*; // to store date

public class Listing {
    private String title;
    private static int id; // String or int -> dependent on database implementation
    private LocalDate dateAdded; // Represents a date (year, month, day (yyyy-MM-dd))
    private float price;
    private User seller;
    private String description;
    private String[] images; // Will store the directory path of the images

    public Listing(String title, int id, float price, User seller, String description, String[] images) {
        this.title = title;
        this.id = id;
        this.dateAdded = LocalDate.now(); // assigns the localDate with the current time
        this.price = price;
        this.seller = seller;
        this.description = description;
        this.images = images;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage(int imageIndex) {
        if (imageIndex >= images.length) {
            return "No Image";
        } else {
            return images[imageIndex];
        }
    }


}

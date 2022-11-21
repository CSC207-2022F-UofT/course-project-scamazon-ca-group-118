package entities;

import entities.User;

import java.io.File;
import java.time.*; // to store date
import java.util.List;

public class Listing {
    private String listingTitle;
    private int id; // String or int -> dependent on database implementation
    private static int nextId = 0;
    private LocalDate dateAdded; // Represents a date (year, month, day (yyyy-MM-dd))
    private float price;
    private String sellerUsername;
    private static int nextID = 0;
    private String description;
    private String imagePath; // Will store the directory path of the images

    public Listing(User seller, String listingTitle, float price, String description, String imagePath) {
        this.sellerUsername = seller.getUsername();
        this.listingTitle = listingTitle;
        this.id = nextId++;
        this.dateAdded = LocalDate.now(); // assigns the localDate with the current time
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }
    public Listing(int id, String sellerUsername, String listingTitle, LocalDate dateAdded,
                   float price, String description, String imagePath) {
        this.id = id;
        this.sellerUsername = sellerUsername;
        this.listingTitle = listingTitle;
        this.id = nextId++;
        this.dateAdded = LocalDate.now(); // assigns the localDate with the current time
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }

    public static int getNextID() {
        return nextID++;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return listingTitle;
    }

    public LocalDate getDate() {
        return dateAdded;
    }


    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public boolean setPrice(float price) {
        if (price > 0) {
            this.price = price;
            return true;
        } else {
            return false;
        }
    }

    public boolean setDescription(String description) {
        if (description.length() < 1000) {
            this.description = description;
            return true;
        } else {
            return false;
        }

    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

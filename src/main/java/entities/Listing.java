package entities;

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

    // TODO deprecated, remove this constructor
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
        this.dateAdded = dateAdded;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }

    // for when this isn't a "new" listing, aka it already exists in the db
    // and we just want to represent it in an object.
    public Listing(String sellerUsername, String listingTitle, LocalDate dateAdded,
                   float price, String description, String imagePath) {
        this.id = nextId++;
        this.sellerUsername = sellerUsername;
        this.listingTitle = listingTitle;
        this.dateAdded = dateAdded;
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

    public boolean equals(Listing listing) {
        if (this.getId() == listing.getId() &&
                this.getSellerUsername().equals(listing.getSellerUsername()) &&
                this.getPrice() == listing.getPrice() &&
                this.getDate().equals(listing.getDate()) &&
                this.getDescription().equals(listing.getDescription()) &&
                this.getImagePath().equals(listing.getImagePath()) &&
                this.getTitle().equals(listing.getTitle())) {
            return true;
        }
        return false;
    }
}

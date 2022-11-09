package features;

import entities.User;

import java.io.File;
import java.time.*; // to store date
import java.util.List;

public class Listing {
    private String title;
    private int id; // String or int -> dependent on database implementation
    private static int nextId = 0;
    private LocalDate dateAdded; // Represents a date (year, month, day (yyyy-MM-dd))
    private float price;
    private User seller;
    private String description;
    private List<String> images; // Will store the directory path of the images

    public Listing(String title, float price, User seller, String description, List<String> images) {
        this.title = title;
        this.id = nextId++;
        this.dateAdded = LocalDate.now(); // assigns the localDate with the current time
        this.price = price;
        this.seller = seller;
        this.description = description;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return dateAdded;
    }


    public float getPrice() {
        return price;
    }

    public User getSeller() {
        return seller;
    }

    public String getDescription() {
        return description;
    }

    public String getImage(int imageIndex) {
        if (imageIndex >= images.size()) {
            return "No Image";
        } else {
            return images.get(imageIndex);
        }
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

    public boolean addImage(String imgPath) {
        File img = new File(imgPath);
        if (img.exists()) {
            images.add(imgPath);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeImages(String imgPath) {
        File img = new File(imgPath);
        if (img.exists()) {
            images.remove(imgPath);
            return true;
        } else {
            return false;
        }
    }
}

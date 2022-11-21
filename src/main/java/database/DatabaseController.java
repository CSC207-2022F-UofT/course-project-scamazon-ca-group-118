package database;

//import com.opencsv.CSVWriter;
import entities.Cart;
import entities.Listing;
import entities.User;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * For all methods, we assume validation was passed
 */
public class DatabaseController<T> implements CreateListingDatabaseGateway, ReviewDatabaseGateway,
        ListingDatabaseGateway {
    String table = null;

    public DatabaseController() {
    }


    boolean checkUserWithUsername(String username) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../entities.data/User.csv"));
            String currLine;
            while ((currLine = reader.readLine()) != null) {
                User userObject = createUserObject(currLine);
                if (username.equals(userObject.getUsername())) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new IOException(e);
        }

    }


    /**
     * Helper method that gets all listings for a certain user
     *
     * @param username user that we want to create listings for
     * @return list of listings
     */
    public ArrayList<Listing> getListingsByUser(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../entities/data/Listings.csv"));
            String currLine;
            ArrayList<Listing> listings = new ArrayList<>();
            while ((currLine = reader.readLine()) != null) {
                Listing listingObject = createListingObject(currLine);
                if (Objects.equals(listingObject.getSellerUsername(), username)) {
                    listings.add(listingObject);
                }
            }
            return listings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    /**
     * Retrieves a user given a username
     *
     * @param username the username that will be searched for in the database
     * @return a user
     */
    @Override
    public User getUserWithUsername(String username) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../entities/data/Users.csv"));
            String currLine;
            while ((currLine = reader.readLine()) != null) {
                String[] user = currLine.split(";");
                if (user[1].equals(username)) {
                    return createUserObject(currLine);
                }
            }
            reader.close();

            // didn't find the user
            System.out.printf("Unable to find user %s%n", username);
            return null;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }


    /**
     * Creates a user given username, password, and email from registration form
     *
     * //@param username username that is inputted
     * //@param password password that is inputted
     * //@param email    email that is inputted

    public void createUser(String username, String password, String email) {
        try {
            FileWriter outputFile = new FileWriter("../entities/Users.csv");
            CSVWriter writer = new CSVWriter(outputFile);

            String[] newUser = {String.valueOf(User.getNextID()), username, password, email, "[]", "[]", "[]"};
            writer.writeNext(newUser);

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     *
     * Creates a listing given sellerUsername, listingTitle, price, dateAdded,
     * description, and imagePath from createListingForm after a listing is submitted
     *
     * //@param sellerUsername username of seller
     * //@param listingTitle title of listing
     * //@param price price of listing
     * //@param dateAdded date added of listing
     * //@param description description of item
     * //@param imagePath image of item
     *
     *
     /*

    public void createListing(String sellerUsername, String listingTitle, int price, LocalDate dateAdded, String description, String imagePath) {
        try {
            FileWriter outputFile = new FileWriter("../entities/Listings.csv");
            CSVWriter writer = new CSVWriter(outputFile);

            String[] newListing = {String.valueOf(Listing.getNextID()), sellerUsername, listingTitle,
                    String.valueOf(price), convertLocalDateToStringDate(dateAdded),
                    description, imagePath};

            writer.writeNext(newListing);

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
     */


    @Override
    public ArrayList<Listing> getListingWithSearch(String keyword) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../entities.data/Listings.csv"));
            String currLine;
            ArrayList<Listing> listings = new ArrayList<>();
            while ((currLine = reader.readLine()) != null) {
                Listing listingObject = createListingObject(currLine);
                if (listingObject.getTitle().contains(keyword)) {
                    listings.add(listingObject);
                }
            }
            reader.close();
            return listings;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    // get all listings in the database
    @Override
    public ArrayList<Listing> getAllListings() throws IOException {
        ArrayList<Listing> listings = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../entities.data/Listings.csv"));
            String currLine;
            while ((currLine = reader.readLine()) != null) {
                Listing listing = createListingObject(currLine);
                listings.add(listing);
            }
            return listings;
        } catch (IOException e) {
            throw new IOException(e);
        }

    }

    /**
     * Serializer that a listing object based on a String row
     * @param row String row from our csv file
     * @return returns a listing object
     */
    private Listing createListingObject(String row) {
        String[] listingString = row.split(";");
        int listingID = Integer.parseInt(listingString[0]);
        String sellerUsername = listingString[1];
        String listingTitle = listingString[2];
        float price = Float.parseFloat(listingString[3]);
        LocalDate dateAdded = convertStringDateToLocalDate(listingString[4]);

        String description = listingString[6];
        String image = listingString[7];
        return new Listing(listingID, sellerUsername, listingTitle, dateAdded, price, description, image);
    }
    /**
     * Serializer that creates the String representation of a listing object
     * @param listing Listing object of a listing
     * @return returns a string version of our listing
     */
    private String createListingString(Listing listing) {
        String id = String.valueOf(listing.getId());
        String username = listing.getSellerUsername();
        String title = listing.getTitle();
        String price = String.format("%.2f", listing.getPrice());
        String dateAdded = convertLocalDateToStringDate(listing.getDate());
        String description = listing.getDescription();
        String imagePath = listing.getImagePath();
        return id + username + title + price + dateAdded + description + imagePath;
    }

    /**
     * Serializer that creates a user object based on a line in csv file
     * @param row a row in our csv file
     * @return a User based on a row in our csv file
     */
    private User createUserObject(String row) {
        String[] userString = row.split(";");
        int userID = Integer.parseInt(userString[0]);
        String username = userString[1];
        String password = userString[2];
        String email = userString[3];
        String[] reviews_cleaned = userString[4].substring(1, userString[4].length() - 1).split(",");
        ArrayList<Integer> reviews = new ArrayList<>();
        for (String review : reviews_cleaned) {
            reviews.add(Integer.parseInt(review));
        }

        ArrayList<Listing> listings = getListingsByUser(username);
        // TODO: get listings in user's cart
        Cart cart = new Cart(listings);

        return new User(userID, username, password, email, reviews, listings, cart);

    }

    /**
     * Creates the string version of a User object
     * @param user Object of a user
     * @return a string representation of the User
     */
    private String createUserString(User user) {
        String id = String.valueOf(user.getID());
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        // convert reviews
        ArrayList<Integer> rawReviews = user.getReviews();
        String reviews = "[";
        for (int i = 0; i < rawReviews.size(); i++) {
            // don't add comma on last iteration
            if (i == rawReviews.size() - 1) {
                reviews += String.valueOf(rawReviews.get(i));
            }
            else {
                reviews += String.valueOf(rawReviews.get(i));
                reviews += ",";
            }
        }
        reviews += "]";
        // convert listings
        ArrayList<Listing> rawListings = user.getListings();
        String listings = "[";
        for (int i = 0; i < rawListings.size(); i++) {
            // don't add comma on last iteration
            if (i == rawReviews.size() - 1) {
                listings += String.valueOf(rawListings.get(i));
            }
            else {
                listings += String.valueOf(rawListings.get(i));
                listings += ",";
            }
        }
        listings += "]";
        // convert cart
        ArrayList<Listing> rawCart = user.getCart().getItems();
        String cart = "[";
        for (int i = 0; i < rawCart.size(); i++) {
            // don't add comma on last iteration
            if (i == rawCart.size() - 1) {
                cart += String.valueOf(rawCart.get(i));
            }
            else {
                cart += String.valueOf(rawCart.get(i));
                cart += ",";
            }
        }
        cart += "]";

        return id + username + password + email + reviews + listings + cart;
    }

    /**
     * assume ISO-8601 format
     * @param date date we want to convert
     * @return LocalDate version of date
     */
    private LocalDate convertStringDateToLocalDate(String date) {
        return LocalDate.parse(date);
    }

    /**
     * assume ISO-8601 format
     * @param date date we want to convert
     * @return String version of date
     */

    private String convertLocalDateToStringDate(LocalDate date) {
        return date.toString();
    }
}



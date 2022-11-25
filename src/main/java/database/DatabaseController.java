package database;

import com.opencsv.CSVWriter;

import entities.Cart;
import entities.Listing;
import entities.User;
import Main.Main;

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
        ListingDatabaseGateway, DetailDatabaseGateway, CartDatabaseGateway, CheckoutDatabaseGateway {
    String table = null;

    public DatabaseController() {
    }

    /**
     * Returns whether there is a duplicate of the given username already in database
     *
     * @param username username to check
     * @return true/false value if there is a duplicate
     * @throws IOException in case of IOException
     */

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
     * Given an ID, return a listing object corresponding to that ID
     *
     * @param ID id of the listing
     * @return listing object corresponding to the ID
     * @throws IOException thrown in case of exception
     */
    public Listing getListingByID(int ID) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../entities/data/Listings.csv"));
            String currLine;

            while ((currLine = reader.readLine()) != null) {
                String[] listing = currLine.split(";");
                int listingID = Integer.parseInt(listing[0]);
                if (listingID == ID) {
                    return createListingObject(currLine);
                }
            }
            reader.close();

            // didn't find the listing
            System.out.printf("Unable to find listing %s%n", ID);
            return null;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }


    /**
     * Creates a user given username, password, and email from registration form
     *
     * @param username username that is inputted
     * @param password password that is inputted
     * @param email    email that is inputted
     **/
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
     * removes listing from csv file based on id given
     * writes to a new file without listing to be removed, and then renamed
     * helper for checkoutRemoveListings
     * also used for when user wants to take down a listing
     *
     * @param ID id passed to removeListing
     */
    public void removeListing(int ID) throws IOException {
        try {
            FileWriter listingsWriter = new FileWriter("../entities/Listings.csv");
            File listings = new File("../entities/Listings.csv");
            File temp = File.createTempFile("temp", ".csv", new File("../entities/"));

            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(listingsWriter)));
            CSVWriter writer = new CSVWriter(new FileWriter(temp));

            String currLine;

            while ((currLine = reader.readLine()) != null) {
                String[] listing = currLine.split(";");
                int listingID = Integer.parseInt(listing[0]);
                if (listingID == ID) {
                    continue;
                }
                writer.writeNext(listing);
            }
            reader.close();
            writer.close();
            boolean successful = temp.renameTo(listings);

            if (!successful) {
                // didn't find the listing
                System.out.printf("Unable to remove listing %s%n", ID);
            }

        } catch (IOException e) {
            throw new IOException(e);
        }
    }


    /**
     * Creates a listing given sellerUsername, listingTitle, price, dateAdded,
     * description, and imagePath from createListingForm after a listing is submitted
     *
     * @param sellerUsername username of seller
     * @param listingTitle   title of listing
     * @param price          price of listing
     * @param dateAdded      date added of listing
     * @param description    description of item
     * @param imagePath      image of item
     **/
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


    /**
     * gets a list of listings that match the keyword in the search bar
     * @param keyword search phrase
     * @return a list of listings
     * @throws IOException thrown in case of exception
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

    /**
     * Method that gets listings with no keyword, the default listings
     *
     * @return returns a list of listings
     * @throws IOException throws an exception in case of an IOException
     */
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
     * adds a listing to the cart based on the ID of the listing passed
     *
     * @param ID pass the id of the listing to be removed
     */
    @Override
    public void addToCart(int ID) throws IOException {
        User currUser = Main.getCurrentUser();
        Cart currCart = currUser.getCart();
        Listing listing = getListingByID(ID);
        currCart.addItem(listing);
    }

    /**
     * removes a listing from the cart based on the ID of the listing passed
     *
     * @param ID pass the id of the listing to be removed
     */
    @Override
    public void removeFromCart(int ID) throws IOException {
        User currUser = Main.getCurrentUser();
        Cart currCart = currUser.getCart();
        Listing listing = getListingByID(ID);
        currCart.removeItem(listing);
    }

    /**
     * adds a review rating to a given user
     *
     * @param reviewed user being reviewed
     * @param rating number given by the reviewer
     */
    @Override
    public void addReview(User reviewed, int rating) throws IOException {
        User reviewedUser = getUserWithUsername(reviewed.getUsername());
        ArrayList<Integer> reviewedUserRatings = reviewedUser.getReviews();
        reviewedUserRatings.add(rating);
    }

    /**
     * removes all listings from csv files after checkout
     */
    @Override
    public void checkoutRemoveListings() throws IOException {
        User currUser = Main.getCurrentUser();
        Cart currCart = currUser.getCart();
        for (Listing listing : currCart.getItems()) {
            removeListing(listing.getId());
            currCart.removeItem(listing);
        }
    }

    /**
     * logout method
     * sets curr user to null after logout
     */
    public void logout() {
        User currUser = Main.getCurrentUser();
        currUser.setCurrentUser(null);
    }

    /**
     * Serializer that creates a Listing object based on a String row
     *
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
     * Serializer that creates the String representation of a Listing object
     *
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
     * Serializer that creates a User object based on a line in csv file
     *
     * @param row a row in our csv file
     * @return a User based on a row in our csv file
     */
    private User createUserObject(String row) throws IOException {
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

        String[] cart_cleaned = userString[4].substring(1, userString[4].length() - 1).split(",");
        Cart cart = new Cart(listings);
        for (String listing : cart_cleaned) {
            int id = Integer.parseInt(listing);
            Listing listingObject = getListingByID(id);
            cart.addItem(listingObject);
        }


        return new User(userID, username, password, email, reviews, listings, cart);

    }

    /**
     * Creates the string version of a User object
     *
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
            } else {
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
            } else {
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
            } else {
                cart += String.valueOf(rawCart.get(i));
                cart += ",";
            }
        }
        cart += "]";

        return id + username + password + email + reviews + listings + cart;
    }

    /**
     * assume ISO-8601 format
     *
     * @param date date we want to convert
     * @return LocalDate version of date
     */
    private LocalDate convertStringDateToLocalDate(String date) {
        return LocalDate.parse(date);
    }

    /**
     * assume ISO-8601 format
     *
     * @param date date we want to convert
     * @return String version of date
     */

    private String convertLocalDateToStringDate(LocalDate date) {
        return date.toString();
    }


}



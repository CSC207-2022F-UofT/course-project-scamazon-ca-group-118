package database;

import com.opencsv.CSVWriter;

import entities.Cart;
import entities.Listing;
import entities.User;
import Main.Main;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * For all methods, we assume validation was passed
 */
public class DatabaseController implements CreateListingDatabaseGateway, ReviewDatabaseGateway,
        ListingDatabaseGateway, ListingDetailDatabaseGateway, CartDatabaseGateway, CheckoutDatabaseGateway {

    private String USER_TABLE_PATH = "src/main/java/entities/data/Users.csv";
    private String LISTING_TABLE_PATH = "src/main/java/entities/data/Listings.csv";

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
            BufferedReader reader = new BufferedReader(new FileReader(USER_TABLE_PATH));
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
            BufferedReader reader = new BufferedReader(new FileReader(LISTING_TABLE_PATH));
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
            BufferedReader reader = new BufferedReader(new FileReader(USER_TABLE_PATH));
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
            BufferedReader reader = new BufferedReader(new FileReader(LISTING_TABLE_PATH));
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
            FileWriter outputFile = new FileWriter(USER_TABLE_PATH, true);
            CSVWriter writer = new CSVWriter(outputFile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

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
    // TODO: test
    public void removeListing(int ID) throws IOException {
        try {
            File listings = new File(LISTING_TABLE_PATH);
            File temp = File.createTempFile("temp", ".csv", new File("src/test/java/database/"));
            FileWriter tempCSV = new FileWriter(temp);

            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(listings)));
            CSVWriter writer = new CSVWriter(tempCSV, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

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
            String path = listings.getAbsolutePath();
            listings.delete();
            boolean successful = temp.renameTo(new File(path));

            if (!successful) {
                // didn't find the listing
                System.out.printf("Unable to remove listing %s%n", ID);
            }

        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    // TODO test
    private void removeListingFromAllCarts(int listingID) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(USER_TABLE_PATH));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                User user = createUserObject(line);
                ArrayList<Listing> listingsInCart = user.getCart().getItems();
                // true if removed, false if not there
                boolean foundListing = user.removeFromCartByID(listingID);
                if (foundListing) {
                    String newUserString = createUserString(user);
                    inputBuffer.append(newUserString);
                    inputBuffer.append("/n");
                } else {
                    inputBuffer.append(line);
                    inputBuffer.append("/n");
                }
            }
            file.close();

            FileOutputStream fileOut = new FileOutputStream(USER_TABLE_PATH);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        // if we find the id in their cart, remove it, replace with updated cart
        // save file

    }


    //TODO test

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
    public void createListing(String sellerUsername, String listingTitle, float price, LocalDate dateAdded, String description, String imagePath) {
        try {
            FileWriter outputFile = new FileWriter(LISTING_TABLE_PATH, true);
            CSVWriter writer = new CSVWriter(outputFile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            ;

            Listing listing = new Listing(sellerUsername, listingTitle, dateAdded, price, description, imagePath);
            String[] listingStringArray = createListingString(listing).split(";");
            writer.writeNext(listingStringArray);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * gets a list of listings that match the keyword in the search bar
     *
     * @param keyword search phrase
     * @return a list of listings
     * @throws IOException thrown in case of exception
     */
    @Override
    public ArrayList<Listing> getListingWithSearch(String keyword) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(LISTING_TABLE_PATH));
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
            BufferedReader reader = new BufferedReader(new FileReader(LISTING_TABLE_PATH));
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
     * method called when a user deletes a listing from their cart
     *
     * @param listingID pass the id of the listing to be removed
     */
    // TODO: test
    @Override
    public void removeFromCartByID(int listingID) throws IOException {
        User currUser = Main.getCurrentUser();
        try {
            File users = new File(USER_TABLE_PATH);
            File temp = File.createTempFile("temp", ".csv", new File("src/test/java/database/"));

            BufferedReader userReader = new BufferedReader(new FileReader(USER_TABLE_PATH));
            BufferedReader listingReader = new BufferedReader(new FileReader(LISTING_TABLE_PATH));
            CSVWriter writer = new CSVWriter(new FileWriter(temp));

            String currLine;
            while ((currLine = userReader.readLine()) != null) {
                User userObject = createUserObject(currLine);
                if (userObject.getID() == currUser.getID()) {
                    String currLine2;
                    while ((currLine2 = listingReader.readLine()) != null) {
                        Listing listingObject = createListingObject(currLine2);
                        if (listingObject.getId() == listingID) {
                            userObject.removeFromCart(listingObject);
                            break;
                        }
                    }
                    String userString = createUserString(userObject);
                    writer.writeNext(userString.split(";"));
                    continue;
                }
                writer.writeNext(currLine.split(";"));
            }
            userReader.close();
            listingReader.close();
            writer.close();
            String path = users.getAbsolutePath();
            users.delete();
            boolean successful = temp.renameTo(new File(path));

            if (!successful) {
                System.out.printf("Unable to remove listing %s%n from cart", listingID);
            }
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }

    }

    /**
     * adds a review rating to a given user
     *
     * @param reviewed user being reviewed
     * @param rating   number given by the reviewer
     */
    // TODO: test
    @Override
    public void addReview(User reviewed, int rating) throws IOException {
        User reviewedUser = getUserWithUsername(reviewed.getUsername());
        ArrayList<Integer> reviewedUserRatings = reviewedUser.getReviews();
        reviewedUserRatings.add(rating);
        try {
            File users = new File(USER_TABLE_PATH);
            File temp = File.createTempFile("temp", ".csv", new File("src/test/java/database/"));

            BufferedReader reader = new BufferedReader(new FileReader(USER_TABLE_PATH));
            CSVWriter writer = new CSVWriter(new FileWriter(temp));

            String currLine;
            while ((currLine = reader.readLine()) != null) {
                User userObject = createUserObject(currLine);
                if (userObject == reviewed) {
                    // userObject.addReview(rating); // TODO need to fix reviews
                    String userString = createUserString(userObject);
                    writer.writeNext(userString.split(";"));
                    continue;
                }
                writer.writeNext(currLine.split(";"));
            }

            reader.close();
            writer.close();
            String path = users.getAbsolutePath();
            users.delete();
            boolean successful = temp.renameTo(new File(path));

            if (!successful) {
                System.out.print("Unable to add review");
            }

        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }

    /**
     * removes all listings from user's cart
     * calls removeListing to also remove each listing from csv file
     * also calls removeListingFromAllCarts to remove all listings that were
     * checked out from everyone else's cart
     */
    // TODO: test
    @Override
    public void checkoutRemoveListings() throws IOException {

        User currUser = Main.getCurrentUser();
        for (Listing listing : currUser.getCart().getItems()) {
            removeListing(listing.getId());
            currUser.getCart().removeItem(listing);
            removeListingFromAllCarts(listing.getId());
        }
    }

    /**
     * logout method
     * sets curr user to null after logout
     */
    public void logout() {
        Main.setCurrentUser(null);
    }

    /**
     * Serializer that creates a Listing object based on a String row
     *
     * @param row String row from our csv file
     * @return returns a listing object
     */
    protected Listing createListingObject(String row) {
        String[] listingString = row.split(";");
        int listingID = Integer.parseInt(listingString[0]);
        String sellerUsername = listingString[1];
        String listingTitle = listingString[2];
        float price = Float.parseFloat(listingString[3]);
        LocalDate dateAdded = convertStringDateToLocalDate(listingString[4]);

        String description = listingString[5];
        String image = listingString[6];
        return new Listing(listingID, sellerUsername, listingTitle, dateAdded, price, description, image);
    }

    /**
     * Serializer that creates the String representation of a Listing object
     *
     * @param listing Listing object of a listing
     * @return returns a string version of our listing
     */
    protected String createListingString(Listing listing) {
        String id = String.valueOf(listing.getId());
        String username = listing.getSellerUsername();
        String title = listing.getTitle();
        String price = String.format("%.2f", listing.getPrice());
        String dateAdded = convertLocalDateToStringDate(listing.getDate());
        String description = listing.getDescription();
        String imagePath = listing.getImagePath();
        return id + ";" + username + ";" + title + ";" + price + ";" + dateAdded + ";" + description + ";" + imagePath;
    }

    /**
     * Serializer that creates a User object based on a line in csv file
     *
     * @param row a row in our csv file
     * @return a User based on a row in our csv file
     */
    protected User createUserObject(String row) throws IOException {
        String[] userString = row.split(";");
        int userID = Integer.parseInt(userString[0]);
        String username = userString[1];
        String password = userString[2];
        String email = userString[3];
        String[] reviews_cleaned = userString[4].substring(1, userString[4].length() - 1).split(",");
        ArrayList<Integer> reviews = new ArrayList<>();
        // if there is at least one review, add to reviews arraylist
        if (!reviews_cleaned[0].equals("")) {
            for (String review : reviews_cleaned) {
                reviews.add(Integer.parseInt(review));
            }
        }
        ArrayList<Listing> listings = getListingsByUser(username);

        String[] cart_cleaned = userString[6].substring(1, userString[6].length() - 1).split(",");
        Cart cart = new Cart();
        // if there is at least one listing, add to cart
        if (!cart_cleaned[0].equals(""))
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
    protected String createUserString(User user) {
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
            if (i == rawListings.size() - 1) {
                listings += String.valueOf(rawListings.get(i).getId());
            } else {
                listings += String.valueOf(rawListings.get(i).getId());
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
                cart += String.valueOf(rawCart.get(i).getId());
            } else {
                cart += String.valueOf(rawCart.get(i).getId());
                cart += ",";
            }
        }
        cart += "]";

        return id + ";" + username + ";" + password + ";" + email + ";" + reviews + ";" + listings + ";" + cart;
    }

    /**
     * assume ISO-8601 format
     *
     * @param date date we want to convert
     * @return LocalDate version of date
     */
    protected LocalDate convertStringDateToLocalDate(String date) {
        return LocalDate.parse(date);
    }

    /**
     * assume ISO-8601 format
     *
     * @param date date we want to convert
     * @return String version of date
     */

    protected String convertLocalDateToStringDate(LocalDate date) {
        return date.toString();
    }

    /**
     * @param currentUser the current user
     * @param listing     the listing we want to check for
     * @return true if currentUser already has the listing in their cart, false otherwise
     */

    // TODO implement
    @Override
    public boolean currentUserHasListingInCart(User currentUser, Listing listing) throws IOException {
        ArrayList<Listing> currCart = currentUser.getCart().getItems();
        for (Listing listingInCurrCart : currCart) {
            if (listingInCurrCart == listing) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param currentUser the current user
     * @param listing     the listing we want to add to their cart
     * @throws IOException
     */
    @Override
    public void addListingToUserCart(User currentUser, Listing listing) throws IOException {
        // TODO implement
    }

    // we need these methods for testing
    protected void setUserTablePath(String path) {
        this.USER_TABLE_PATH = path;
    }

    protected String getUserTablePath() {
        return this.USER_TABLE_PATH;
    }

    protected void setListingTablePath(String path) {
        this.LISTING_TABLE_PATH = path;
    }

    protected String getListingTablePath() {
        return this.LISTING_TABLE_PATH;
    }
}



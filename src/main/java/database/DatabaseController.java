package database;

import com.opencsv.CSVWriter;
import entities.Cart;
import entities.Listing;
import entities.User;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * For all methods, we assume validation was passed
* */
public class DatabaseController<T> implements CreateListingDatabaseGateway, ReviewDatabaseGateway,
        ListingDatabaseGateway{
    String table = null;

    public DatabaseController() {
    }


    boolean checkUserWithUsername(String username) {
        return false;
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

    /**
     * Helper method that gets all listings for a certain user
     * @param username user that we want to create listings for
     * @return list of listings
     */
    public ArrayList<Listing> getListingsByUser(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../entities.data/Listings.csv"));
            String currLine;
            ArrayList<Listing> listings = new ArrayList<>();
            while ((currLine = reader.readLine()) != null) {
                String[] listing = currLine.split(";");
                if (Objects.equals(listing[1], username)) {
                    int listingID = Integer.parseInt(listing[0]);
                    String sellerUsername = listing[1];
                    String listingTitle = listing[2];
                    int sellerRating = Integer.parseInt(listing[3]);
                    LocalDate dateAdded = convertStringDateToLocalDate(listing[3]);
                    float price = Float.parseFloat(listing[4]);
                    String description = listing[5];
                    String image = listing[6];
                    listings.add(new Listing(listingID, sellerUsername, listingTitle, sellerRating,
                            dateAdded, price, description, image));
                }
            }
            return listings;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Retrieves a user given a username
     *
     * @param username the username that will be searched for in the database
     * @return a user
     *
     */
    @Override
    public User getUserWithUsername(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../entities.data/Users.csv"));
            String currLine;
            String[] user = null;
            boolean foundUser = false;
            while ((currLine = reader.readLine()) != null) {
                user = currLine.split(";");
                if (user[1].equals(username)) {
                    foundUser = true;
                    break;
                }
            }
            if (foundUser) {
                int id = Integer.parseInt(user[0]);
                String name = user[1];
                String password = user[2];
                String email = user[3];

                // convert string representation of array in csv file into actual array
                String[] reviews_cleaned = user[4].substring(1, user[4].length() - 1).split(",");
                ArrayList<Integer> reviews = new ArrayList<>();
                for (String review : reviews_cleaned) {
                    reviews.add(Integer.parseInt(review));
                }

                ArrayList<Listing> listings = getListingsByUser(name);

                Cart cart = new Cart(listings);


                User userObject = new User(id, name, password, email, reviews, listings, cart);
                return userObject;
            } else {
                // didn't find the user
                System.out.println(String.format("Unable to find user %s", username));
                return null;
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**
     * Creates a user given username, password, and email from registration form
     *
     * @param username username that is inputted
     * @param password password that is inputted
     * @param email email that is inputted
     *
     */
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
     * Creates a listing given sellerUsername, listingTitle, price, dateAdded, sellerRating,
     * description, and imagePath from createListingForm after a listing is submitted
     *
     * @param sellerUsername username of seller
     * @param listingTitle title of listing
     * @param price price of listing
     * @param dateAdded date added of listing
     * @param sellerRating rating of seller
     * @param description description of item
     * @param imagePath image of item
     *
     *
     */
    public void createListing(String sellerUsername, String listingTitle, int price, LocalDate dateAdded,
                              int sellerRating, String description, String imagePath) {
        try {
            FileWriter outputFile = new FileWriter("../entities/Listings.csv");
            CSVWriter writer = new CSVWriter(outputFile);

            String[] newListing = {String.valueOf(Listing.getNextID()), sellerUsername, listingTitle,
                    String.valueOf(price), convertLocalDateToStringDate(dateAdded),
                    String.valueOf(sellerRating), description, imagePath};

            writer.writeNext(newListing);

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Listing> getListingWithSearch(String keyword) {
        return null;
    }

    @Override
    public List<Listing> getListingDefault() {
        return null;
    }
}

package database;

import entities.User;

import java.io.IOException;

public class AddReview implements ReviewDatabaseGateway{
    private DatabaseController db = new DatabaseController();
    /**
     * Given the username, the method returns the user object associated with it
     * @param username the username that will be searched for in the database
     * @return returns the user object
     * @throws IOException throws if there is an IOException
     */
    @Override
    public User getUserWithUsername(String username) throws IOException {
        return db.getUserWithUsername(username);
    }
    /**
     * adds a review through the database controller
     * @param reviewed user being reviewed
     * @param rating number given by the reviewer
     */

    @Override
    public void addReview(User reviewed, int rating){
        db.addReview(reviewed, rating);
    }

    public void setDb(DatabaseController db) {
        this.db = db;
    }
}

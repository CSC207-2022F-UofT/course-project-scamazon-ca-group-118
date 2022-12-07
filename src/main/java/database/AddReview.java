package database;

import entities.User;

import java.io.IOException;

public class AddReview implements ReviewDatabaseGateway{
    @Override
    public User getUserWithUsername(String username) throws IOException {
        return new DatabaseController().getUserWithUsername(username);
    }

    @Override
    public void addReview(User reviewed, int rating) throws IOException {
        DatabaseController databaseController = new DatabaseController();
        databaseController.addReview(reviewed, rating);
    }
}

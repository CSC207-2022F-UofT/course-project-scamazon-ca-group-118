package database;

import entities.User;

import java.io.IOException;

public class AddReview implements ReviewDatabaseGateway{
    private DatabaseController db = new DatabaseController();
    @Override
    public User getUserWithUsername(String username) throws IOException {
        return db.getUserWithUsername(username);
    }

    @Override
    public void addReview(User reviewed, int rating){
        db.addReview(reviewed, rating);
    }

    public void setDb(DatabaseController db) {
        this.db = db;
    }
}

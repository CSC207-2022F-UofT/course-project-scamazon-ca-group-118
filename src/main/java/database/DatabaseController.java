package database;


import entities.User;

public class DatabaseController<T> implements CreateListingDatabaseGateway, ReviewDatabaseGateway{
    String table = null;

    public DatabaseController() {
    }

    @Override
    public User getUserWithUsername(String username) {
        return null;
    }
}

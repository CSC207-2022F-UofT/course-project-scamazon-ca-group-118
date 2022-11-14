package database;

import entities.User;

public class DatabaseController<T> implements CreateListingDatabaseGateway, ReviewDatabaseGateway, LoginDatabaseGateway{
    String table = null;

    @Override
    public User getUserWithUsername(String username) {
        return null;
    }
}

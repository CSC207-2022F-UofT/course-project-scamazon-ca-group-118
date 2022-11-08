package login;

public interface LoginDatabaseGateway {
    User getUserWithUsername(String username);
}

//maybe don't need this. might have databaseController interface with all the methods and i'll just override this
// one in loginDatabaseController?

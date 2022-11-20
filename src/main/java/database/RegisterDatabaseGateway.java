package database;

public interface RegisterDatabaseGateway {
    boolean checkUserWithEmail(String email);
    // TODO: Checks database to see if email exists
    boolean checkUserWithUsername(String username);
    // TODO: Checks database to see if username exists
}

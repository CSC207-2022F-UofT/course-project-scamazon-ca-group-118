package database;

import java.io.IOException;

public interface RegisterDatabaseGateway {
    boolean checkUserWithUsername(String username) throws IOException;
    boolean checkUserWithEmail(String email) throws IOException;
    // TODO: Checks database to see if email exists
    void createUser(String username, String email, String password);
}

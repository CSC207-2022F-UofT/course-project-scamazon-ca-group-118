package database;

import java.io.IOException;

public class RegisterGatewayImplementation implements RegisterDatabaseGateway{

    public RegisterGatewayImplementation(){
    }

    /**
     * checks to see if email entered is unique
     * @param email email inputted
     * @return true if it is a duplicate, false otherwise
     * @throws IOException throws in case of IOException
     */
    @Override
    public boolean checkUserWithEmail(String email) throws IOException {
        DatabaseController check = new DatabaseController();
        return check.checkUserWithEmail(email);
    }

    /**
     * checks to see if there is already a user in the database
     * @param username username inputted
     * @return true if is it a duplicate, false if unique
     * @throws IOException throws in case of IOException
     */
    @Override
    public boolean checkUserWithUsername(String username) throws IOException {
        DatabaseController check = new DatabaseController();
        return check.checkUserWithUsername(username);
    }

    /**
     * creates a user given a username, email, and password
     * @param username username to be set for the account
     * @param email email to be used for the account
     * @param password password for the account
     */
    @Override
    public void createUser(String username, String email, String password) {
        DatabaseController check = new DatabaseController();
        check.createUser(username, password, email);
    }
}

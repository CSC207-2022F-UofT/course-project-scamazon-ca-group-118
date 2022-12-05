package database;

import java.io.IOException;

public class RegisterGatewayImplementation implements RegisterDatabaseGateway{

    private String email;
    private String username;
    private String password;

    public RegisterGatewayImplementation(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean checkUserWithEmail(String email) throws IOException {
        DatabaseController check = new DatabaseController();
        return check.checkUserWithEmail(email);
    }

    //Get checkUserWithUsername From DatabaseController Class
    @Override
    public boolean checkUserWithUsername(String username) throws IOException {
        DatabaseController check = new DatabaseController();
        return check.checkUserWithUsername(username);
    }

    @Override
    public void createUser(String username, String email, String password) {
        DatabaseController check = new DatabaseController();
        check.createUser(username, password, email);
    }
}

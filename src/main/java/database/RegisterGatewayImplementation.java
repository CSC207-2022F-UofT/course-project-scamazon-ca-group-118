package database;

public class RegisterGatewayImplementation implements RegisterDatabaseGateway {

    private String email;
    private String username;
    private String password;

    public RegisterGatewayImplementation(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean checkUserWithEmail(String email) {
        //TODO: implement this
        return true;
    }

    @Override
    public boolean checkUserWithUsername(String username) {
        //TODO: implement this
        return false;
    }

}

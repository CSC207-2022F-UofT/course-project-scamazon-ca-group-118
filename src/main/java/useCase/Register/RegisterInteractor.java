package useCase.Register;

import database.RegisterGatewayImplementation;
import entities.User;

import java.io.IOException;

public class RegisterInteractor {

    private String password;
    private String email;
    private String username;
    private boolean emailExists;
    private boolean usernameExists;
    private RegisterGatewayImplementation implementation;
    private User user;

    public RegisterInteractor(String password, String email, String username) throws IOException {
        this.email = email;
        this.password = password;
        this.username = username;
        this.implementation = new RegisterGatewayImplementation(email, username, password);
        this.emailExists = implementation.checkUserWithEmail(email);
        System.out.println(emailExists);
        this.usernameExists = implementation.checkUserWithUsername(username);
    }

    public boolean shouldRegister(){
        if (!emailExists && !usernameExists){
            return true;
        }else{
            if (emailExists){
                throw new RegisterFailed("Email is already taken");
            }else if (usernameExists){
                throw new RegisterFailed("Username is already taken");
            }else{
                throw new RegisterFailed("Username and Email are already taken");
            }
        }
    }

    public void createUser(String username, String email, String password){
        implementation.createUser(username, email, password);
    }

}

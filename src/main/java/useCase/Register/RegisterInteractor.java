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

    public RegisterInteractor(String password, String email, String username) throws IOException {
        this.email = email;
        this.password = password;
        this.username = username;
        this.implementation = new RegisterGatewayImplementation(email, username, password);
        this.emailExists = implementation.checkUserWithEmail(email);
        this.usernameExists = implementation.checkUserWithUsername(username);
    }

    public boolean shouldRegister(){
        //check if username is empty
        System.out.println(username);
        if (username.equals("")){
            throw new RegisterFailed("Please enter a username");
        }
        if (!emailExists && !usernameExists){
            return true;
        }else{
            if (emailExists && usernameExists){
                throw new RegisterFailed("Username and Email are already taken");
            }else if (usernameExists){
                throw new RegisterFailed("Username is already taken");
            }else{
                throw new RegisterFailed("Email is already taken");
            }
        }
    }

    public void createUser(String username, String email, String password){
        implementation.createUser(username, email, password);
    }

    public String getPassword(){
        return this.password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public boolean getEmailExists(){
        return this.emailExists;
    }

    public boolean getUsernameExists(){
        return this.usernameExists;
    }

}

package use_case.register;

import database.RegisterGatewayImplementation;

import java.io.IOException;

public class RegisterInteractor {

    private final String PASSWORD;
    private final String EMAIL;
    private final String USERNAME;
    private final boolean EMAIL_EXISTS;
    private final boolean USERNAME_EXISTS;
    private final RegisterGatewayImplementation IMPLEMENTATION;

    public RegisterInteractor(String password, String email, String username) throws IOException {
        this.EMAIL = email;
        this.PASSWORD = password;
        this.USERNAME = username;
        this.IMPLEMENTATION = new RegisterGatewayImplementation();
        this.EMAIL_EXISTS = IMPLEMENTATION.checkUserWithEmail(email);
        this.USERNAME_EXISTS = IMPLEMENTATION.checkUserWithUsername(username);
    }

    public boolean shouldRegister(){
        //check if username is empty
        System.out.println(USERNAME);
        if (USERNAME.equals("")){
            throw new RegisterFailed("Please enter a username");
        }
        if (!EMAIL_EXISTS && !USERNAME_EXISTS){
            return true;
        }else{
            if (EMAIL_EXISTS && USERNAME_EXISTS){
                throw new RegisterFailed("Username and Email are already taken");
            }else if (USERNAME_EXISTS){
                throw new RegisterFailed("Username is already taken");
            }else{
                throw new RegisterFailed("Email is already taken");
            }
        }
    }

    public void createUser(){
        IMPLEMENTATION.createUser(USERNAME, EMAIL, PASSWORD);
    }
}

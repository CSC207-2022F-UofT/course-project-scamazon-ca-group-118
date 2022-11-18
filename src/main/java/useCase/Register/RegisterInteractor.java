package useCase.Register;

import database.RegisterGatewayImplementation;
import entities.User;

public class RegisterInteractor {

    private String password;
    private String email;
    private String username;
    private boolean emailExists;
    private boolean usernameExists;
    private RegisterGatewayImplementation implementation;
    private User user;

    public RegisterInteractor(String password, String email, String username){
        this.email = email;
        this.password = password;
        this.username = username;
        this.implementation = new RegisterGatewayImplementation(email, username, password);
        this.emailExists = implementation.checkUserWithEmail(email);
        this.usernameExists = implementation.checkUserWithUsername(username);
    }

    public boolean shouldRegister(String username, String email){
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

    public User createUser(String username, String email, String password){ return null; }

}

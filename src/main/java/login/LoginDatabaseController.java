package login;
import forms.LoginForm;

public class LoginDatabaseController implements LoginDatabaseGateway{
    User user;

    public LoginDatabaseController(String username){
        this.user = getUserWithUsername(username);
    }

    @Override
    public User getUserWithUsername(String username){
        //find user in database. if they don't exist, return empty user;
        return new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

//assuming getUser returns empty user if the user doesn't exist
//could also throw exception but this seems easier. for me lol
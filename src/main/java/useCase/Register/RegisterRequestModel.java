package useCase.Register;

public class RegisterRequestModel {

    private String username;
    private String email;
    private String password;

    public RegisterRequestModel(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getUserEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }


    public void setPassword(String password){
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }
}

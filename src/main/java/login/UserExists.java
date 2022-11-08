package login;

public class UserExists {
    public String password;
    public UserExists(String password){
        this.password = password;
    }
    public boolean checkExists(){
        if(this.password == "")
            return false;
        else{
            return true;
        }
    }
}

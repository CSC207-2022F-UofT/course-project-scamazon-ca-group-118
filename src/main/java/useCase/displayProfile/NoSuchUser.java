package useCase.displayProfile;

public class NoSuchUser extends RuntimeException {

    /**
     * The constructor for NoSuchUser exception which is thrown when searching for a user that does not exist.
     * @param message The error message to be displayed when this exception is thrown.
     */
    public NoSuchUser(String message) {
        super(message);
    }
}

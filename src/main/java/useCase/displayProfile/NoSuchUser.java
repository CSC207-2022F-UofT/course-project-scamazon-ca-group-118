package useCase.displayProfile;

/**
 * This exception is thrown when searching for a user that does not exist.
 */
public class NoSuchUser extends RuntimeException {

    /**
     * The constructor for NoSuchUser exception.
     * @param message The error message to be displayed when this exception is thrown.
     */
    public NoSuchUser(String message) {
        super(message);
    }
}

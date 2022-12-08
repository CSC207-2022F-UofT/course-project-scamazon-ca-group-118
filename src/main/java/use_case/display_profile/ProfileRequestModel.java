package use_case.display_profile;

// This class is in the application business rules layer of clean architecture.

public class ProfileRequestModel {
    private String username;

    /**
     * The ProfileRequestModel constructor that assigns the given username to the profile request.
     * @param username The username of the user who instantiated the profile request.
     */
    public ProfileRequestModel(String username) {
        this.username = username;
    }

    /**
     * Getter for ProfileRequestModel's attribute username.
     * @return The username of the user instantiating ProfileRequestModel.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Setter for ProfileRequestModel's attribute username.
     * @param newUsername The string that the username will be updated to.
     */
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

}

package useCase.createListing;
import entities.Cart;
import entities.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class ListingResponseModelUnitTests {
    User user = new User(0, "currUser", "password", "curr@user.com", new ArrayList<>(), new ArrayList<>(), new Cart());

    @Test
    void validCreateListing() throws IOException {

        ListingResponseModel lrm = new ListingResponseModel(new ListingRequestModel(user, "title", (float) 2.12, "desc", "img/path"));
        assert(lrm.getMessage().equals("Listing Created!"));
    }
    @Test
    void notLoggedIn() throws IOException {
        ListingResponseModel lrm = new ListingResponseModel(new ListingRequestModel(null, "title", (float) 2.12, "desc", "img/path"));
        assert(lrm.getMessage().equals("There was an error (are you logged in?)"));
    }
    @Test
    void setMessage() throws IOException {
        ListingResponseModel lrm = new ListingResponseModel(new ListingRequestModel(null, "title", (float) 2.12, "desc", "img/path"));
        lrm.setMessage("new message");
        assert(lrm.getMessage().equals("new message"));
    }
}

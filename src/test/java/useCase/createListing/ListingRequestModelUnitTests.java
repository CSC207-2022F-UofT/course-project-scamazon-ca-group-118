package useCase.createListing;

import entities.Cart;
import entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ListingRequestModelUnitTests {
    User user = new User(0, "currUser", "password", "curr@user.com", new ArrayList<>(), new ArrayList<>(), new Cart());
    User newuser = new User(1, "currUser2", "password2", "curr2@user.com", new ArrayList<>(), new ArrayList<>(), new Cart());
    @Test
    void testListingRequestModel(){
        ListingRequestModel lrm = new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path");
        assert (lrm.getSeller().equals(user));
        assert (lrm.getTitle().equals("title"));
        assert (lrm.getPrice() == (float)2.12);
        assert (lrm.getDescription().equals("desc"));
        assert (lrm.getImage().equals("img/path"));
    }
    @Test
    void testSetMethods(){
        ListingRequestModel lrm = new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path");
        lrm.setTitle("new title");
        lrm.setPrice((float) 1.23);
        lrm.setDescription("new desc");
        lrm.setSeller(newuser);
        lrm.setImage("img/newpath");
        assert (lrm.getSeller().equals(newuser));
        assert (lrm.getTitle().equals("new title"));
        assert (lrm.getPrice() == (float)1.23);
        assert (lrm.getDescription().equals("new desc"));
        assert (lrm.getImage().equals("img/newpath"));
    }
}

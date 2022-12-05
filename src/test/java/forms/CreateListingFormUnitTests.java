package forms;

import entities.Cart;
import entities.Listing;
import entities.User;


import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CreateListingFormUnitTests {
    User exampleUser = new User(1, "admin", "password", "e@mail.com",  new ArrayList<Integer>(1), new ArrayList<Listing>(), new Cart());
    User notLoggedIn = null;
    @Test
    public void validForm() throws IOException {
        CreateListingForm form = new CreateListingForm("Title", "1.23", exampleUser, "Description", "/example/image/path");
        assertTrue(form.validateForm());
    }
    @Test
    public void noTitle() throws IOException {
        CreateListingForm form = new CreateListingForm("", "1.23", exampleUser, "Description", "/example/image/path");
        assertFalse(form.validateForm());
    }
    @Test
    public void noDescription() throws IOException {
        CreateListingForm form = new CreateListingForm("Title", "1.23", exampleUser, "Description", "/example/image/path");
        assertTrue(form.validateForm());
    }
    @Test
    public void invalidPrice() throws IOException {
        CreateListingForm form = new CreateListingForm("Title", "invalid price", exampleUser, "Description", "/example/image/path");
        assertFalse(form.validateForm());
    }

    @Test
    public void noImage() throws IOException {
        CreateListingForm form = new CreateListingForm("Title", "1.23", exampleUser, "Description", "No Image Selected");
        assertTrue(form.validateForm());
    }
    @Test
    public void userNotLoggedIn() throws IOException {
        CreateListingForm form = new CreateListingForm("Title", "1.23", notLoggedIn, "Description", "/example/image/path");
        assertTrue(form.validateForm());
        String response = form.getMessage();
        assertEquals(response, "There was an error (are you logged in?)");
    }
    @Test
    public void noPrice() throws IOException {
        CreateListingForm form = new CreateListingForm("Title", "", exampleUser, "Description", "/example/image/path");
        assertFalse(form.validateForm());
    }


}

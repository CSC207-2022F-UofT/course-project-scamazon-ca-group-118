package use_case.create_listing;

import entities.Cart;
import entities.User;
import forms.CreateListingForm;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class CreateListingPresenterUnitTests {
    User exampleUser = new User(1, "admin", "password", "e@mail.com",
            new ArrayList<>(1), new ArrayList<>(), new Cart());
    CreateListingForm form = new CreateListingForm("Title", "1.23", exampleUser, "Description",
            "/example/image/path");
    @Test
    void testGetMessage() throws IOException {
        CreateListingPresenter pres = new CreateListingPresenter(form);
        assert(pres.getMessage().equals("Listing Created!"));
    }
    @Test
    void testSetMessage() throws IOException {
        CreateListingPresenter pres = new CreateListingPresenter(form);
        pres.setMessage("new message");

        assert(pres.getMessage().equals("new message"));
    }
}

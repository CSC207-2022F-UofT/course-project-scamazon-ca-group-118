package useCase.createListing;

import entities.Cart;
import entities.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class ListingInteractorUnitTests {
    User user = new User(0, "currUser", "password", "curr@user.com", new ArrayList<>(), new ArrayList<>(), new Cart());
    @Test
    void testCreateListing() throws IOException {
        ListingInteractor li = new ListingInteractor(new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path"));
        assert(li.getMessage().equals("Listing Created!"));
    }
    @Test
    void testGetSeller() throws IOException {
        ListingInteractor li = new ListingInteractor(new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path"));
        assert(li.getSeller().equals(user));
    }
    @Test
    void testGetTitle() throws IOException {
        ListingInteractor li = new ListingInteractor(new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path"));
        assert(li.getTitle().equals("title"));
    }
    @Test
    void testGetDescription() throws IOException {
        ListingInteractor li = new ListingInteractor(new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path"));
        assert(li.getDescription().equals("desc"));
    }
    @Test
    void testGetPrice() throws IOException {
        ListingInteractor li = new ListingInteractor(new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path"));
        assert(li.getPrice() == (float)2.12);
    }
    @Test
    void testGetImage() throws IOException {
        ListingInteractor li = new ListingInteractor(new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path"));
        assert(li.getImages().equals("img/path"));
    }
    @Test
    void testSetTitle() throws IOException {
        ListingInteractor li = new ListingInteractor(new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path"));
        li.setTitle("new title");
        assert(li.getTitle().equals("new title"));
    }
    @Test
    void testSetPrice() throws IOException {
        ListingInteractor li = new ListingInteractor(new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path"));
        li.setPrice((float) 1.23);
        assert(li.getPrice() == (float)1.23);
    }
    @Test
    void testSetDescription() throws IOException {
        ListingInteractor li = new ListingInteractor(new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path"));
        li.setDescription("new desc");
        assert(li.getDescription().equals("new desc"));
    }
    @Test
    void testSetImage() throws IOException {
        ListingInteractor li = new ListingInteractor(new ListingRequestModel(user, "title", (float)2.12, "desc", "img/path"));
        li.setImage("new/path");
        assert(li.getImages().equals("new/path"));
    }

}

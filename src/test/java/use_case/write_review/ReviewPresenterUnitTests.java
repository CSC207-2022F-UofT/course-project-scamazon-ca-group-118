package use_case.write_review;

import database.GetUser;
import database.RegisterGatewayImplementation;
import entities.Cart;
import entities.User;
import forms.ReviewForm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ReviewPresenterUnitTests {
    private static User user = new User(
            1, "user",
            "12345",
            "user@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    private static User reviewer = new User(
            2, "reviewer",
            "password",
            "reviewer@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    @BeforeAll
    public static void setUp() throws IOException {
        if(Objects.isNull(new GetUser().getUserWithUsername(user.getUsername()))){
            new RegisterGatewayImplementation().createUser(user.getUsername(), user.getEmail(), user.getPassword());
        }
        if(Objects.isNull(new GetUser().getUserWithUsername(reviewer.getUsername()))){
            new RegisterGatewayImplementation().createUser(reviewer.getUsername(),
                    reviewer.getEmail(), reviewer.getPassword());
        }
    }
    @Test
    void testReviewPresenterUserExists(){
        assert new ReviewPresenter(new ReviewForm(reviewer.getUsername(), user.getUsername(), 5)).
                getMessage().equals("Review Successful");
    }
    @Test
    void testReviewPresenterUserDoesntExist(){
        assert new ReviewPresenter(new ReviewForm(reviewer.getUsername(),
                "UserThatShouldNeverExistDoNotRegister", 5)).getMessage().equals(
                        "Review Unsuccessful: No User exists with this username");
    }
    @Test
    void testReviewPresenterNoUserEntered(){
        assert new ReviewPresenter(new ReviewForm(reviewer.getUsername(), "", 4)).getMessage()
                .equals("Please enter the username of the User you wish to review");
    }
    @Test
    void testReviewPresenterNoReviewSelected(){
        assert new ReviewPresenter(new ReviewForm(reviewer.getUsername(), user.getUsername(), 0)).getMessage()
                .equals("You must enter a rating");
    }
}

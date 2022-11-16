package database;
import entities.User;
import useCase.displayProfile.NoSuchUser;

// This interface is in the application business rules layer of clean architecture.
// TODO This is essentially the same as Clare's LoginDatabase Gateway. We can use one or the other!

public interface ProfileGateway {

    User retrieveUser(String username) throws NoSuchUser;
        /* TODO Go into the database and look for user with username and return that user,
            if no user with username exists throw a NoSuchUser Exception which is yet to be defined. */

}

import UI.CreateListingPage;
import database.DatabaseController;
import entities.User;

public class Main {
    public static void main(String[] args) {
        DatabaseController databaseInteractor = new DatabaseController();
        User currentUser = null;

        CreateListingPage createListingPage = new CreateListingPage("create listing");

    }
}
package Main;

import UI.LoginPage;
import UI.Page;
import UI.CreateListingPage;
import database.DatabaseController;
import entities.User;
import entities.View;

import javax.swing.*;

public class Main {
    /**
     * The current Page that should be open in the View
     */
    static Page currentPage;

    public static void main(String[] args) {
        DatabaseController databaseInteractor = new DatabaseController();
        User currentUser = null;


        CreateListingPage createListingPage = new CreateListingPage("create listing");

    }

    /**
     * Sets the current Page to newCurrentPage
     *
     * @param newCurrentPage the Page that should be the new current Page
     */
    public static void setCurrentPage(Page newCurrentPage) {
        currentPage = newCurrentPage;

    }

    /**
     * Returns the current Page the View is showing
     *
     * @return the value of the currentPage static variable
     */
    public static Page getCurrentPage() {
        return currentPage;
    }
}
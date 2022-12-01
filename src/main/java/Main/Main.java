package Main;

import UI.LoginPage;
import UI.Page;
import UI.RegisterPage;
import database.DatabaseController;
import entities.User;
import entities.View;

import javax.swing.*;

public class Main {
    /**
     * The current logged in to the application (set to null if not User is logged in)
     */
    static User currentUser = null;
    /**
     * The current Page that should be open in the View
     */
    static Page currentPage;
    /**
     * The application's view that will display all pages
     */
    static View view;

    public static void main(String[] args) {
        DatabaseController databaseInteractor = new DatabaseController();

        //Open the application with the Login Page
        currentPage = new LoginPage();
        view = new View();
        setCurrentPage(new RegisterPage("register"));

    }

    /**
     * Sets the current User logged in to newCurrentUser
     *
     * @param newCurrentUser the User that should be the currentUser
     */
    public static void setCurrentUser(User newCurrentUser) {
        currentUser = newCurrentUser;
    }

    /**
     * Returns the current User logged in
     *
     * @return the User stored within the currentUser static variable
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current Page to newCurrentPage
     *
     * @param newCurrentPage the Page that should be the new current Page
     */
    public static void setCurrentPage(Page newCurrentPage) {
        currentPage = newCurrentPage;
        view.update();
        view.invalidate();
        view.repaint();

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
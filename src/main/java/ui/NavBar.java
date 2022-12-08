package ui;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

/**
 * The NavBar class sets up the GUI for the NavBar (i.e. navigation bar) that will be displayed at the top of
 * each View Frame
 */
public class NavBar extends JPanel implements ActionListener {
    private JButton profileButton;
    private JButton listingsButton;
    private JButton cartButton;
    private JButton logButton;
    private JButton logoButton;
    private JButton createListingButton;
    private final SpringLayout LAYOUT;

    /**
     * The constructor class for the NavBar
     */
    public NavBar() {
        this.LAYOUT = new SpringLayout();
        this.setBackground(new Color(185, 235, 255));

        setUpPanel();
        setUpLayout();
    }

    /**
     * Set up and add all components to this NavBar
     */
    private void setUpPanel() {
        this.setLayout(LAYOUT);
        this.setPreferredSize(new Dimension(1280, 100));

        setUpButtonsAndListeners();

        this.add(profileButton);
        this.add(listingsButton);
        this.add(cartButton);
        this.add(createListingButton);
        this.add(logButton);
        this.add(logoButton);
    }

    /**
     * Sets up the JButtons in this navBar, along with their actionListeners
     */
    public void setUpButtonsAndListeners() {
        profileButton = new JButton("Profile");
        listingsButton = new JButton("Listings");
        cartButton = new JButton("Cart");
        logButton = new JButton("Register");
        createListingButton = new JButton("Create Listing");
        ImageIcon logo = new ImageIcon("images/scamazonLogo.png");
        logoButton = new JButton(logo);


        profileButton.addActionListener(this);
        listingsButton.addActionListener(this);
        cartButton.addActionListener(this);
        logButton.addActionListener(this);
        createListingButton.addActionListener(this);
        logoButton.addActionListener(this);
    }

    /**
     * Fix the layout for the components in this NavBar
     */
    protected void setUpLayout() {
        //vertical alignment
        LAYOUT.putConstraint(SpringLayout.NORTH, logButton, 40, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, cartButton, 0, SpringLayout.NORTH, logButton);
        LAYOUT.putConstraint(SpringLayout.NORTH, listingsButton, 0, SpringLayout.NORTH, logButton);
        LAYOUT.putConstraint(SpringLayout.NORTH, createListingButton, 0, SpringLayout.NORTH, logButton);
        LAYOUT.putConstraint(SpringLayout.NORTH, profileButton, 0, SpringLayout.NORTH, logButton);
        LAYOUT.putConstraint(SpringLayout.NORTH, logoButton, 5, SpringLayout.NORTH, this);

        //horizontal alignment
        LAYOUT.putConstraint(SpringLayout.EAST, logButton, -50, SpringLayout.EAST, this);
        LAYOUT.putConstraint(SpringLayout.EAST, cartButton, -100, SpringLayout.WEST, logButton);
        LAYOUT.putConstraint(SpringLayout.EAST, listingsButton, -100, SpringLayout.WEST, cartButton);
        LAYOUT.putConstraint(SpringLayout.EAST, createListingButton, -100, SpringLayout.WEST, listingsButton);
        LAYOUT.putConstraint(SpringLayout.EAST, profileButton, -100, SpringLayout.WEST, createListingButton);
        LAYOUT.putConstraint(SpringLayout.WEST, logoButton, 25, SpringLayout.WEST, this);
    }

    /**
     * Update this NavBar to display the correct type of Log Button (Log In vs Log Out vs Register)
     */
    public void updateNavBar() {
        if (Main.getCurrentPage() instanceof LoginPage) {
            logButton.setText("Register");
        } else if (Main.getCurrentPage() instanceof RegisterPage) {
            logButton.setText("Log In");
        } else {
            if (!logButton.getText().equals("Log Out")) {
                logButton.setText("Log Out");
            }
        }
    }

    /**
     * The actionListener for the buttons in this NavBar
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == profileButton ||
                e.getSource() == listingsButton ||
                e.getSource() == cartButton ||
                e.getSource() == createListingButton ||
                e.getSource() == logoButton) {
            try {
                notLogButtonAction((JButton) e.getSource());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == logButton) {
            logButtonAction();
        }
    }

    /**
     * The action that should run when a button other than the logButton is clicked
     *
     * @param source the button that was clicked to invoke the action listener
     */
    private void notLogButtonAction(JButton source) throws IOException {
        if (Objects.isNull(Main.getCurrentUser())) {
            userIsNullAction();
        } else {
            switchScreenAction(source);
        }
    }

    /**
     * The action that should run if the logButton is clicked
     */
    private void logButtonAction() {
        if (logButton.getText().equals("Log In")) {
            Main.setCurrentPage(new LoginPage());
        } else if (logButton.getText().equals("Register")) {
            Main.setCurrentPage(new RegisterPage("Register"));
        } else if (logButton.getText().equals("Log Out")) {
            Main.setCurrentUser(null);
            Main.setCurrentPage(new LoginPage());
        }
        updateNavBar();
    }

    /**
     * If no User is logged in (i.e. currentUser is Null) the current page is set to the Login Page with a
     * relevant error message displayed
     */
    private void userIsNullAction() {
        LoginPage newLoginPage = new LoginPage();
        newLoginPage.setErrorMessage("Please log in or register");
        Main.setCurrentPage(newLoginPage);
    }

    /**
     * Switches the currentPage in Main to a different Page, to be displayed in View
     *
     * @param source the button that was clicked to invoke the action listener
     */
    private void switchScreenAction(JButton source) throws IOException {
        if (source == profileButton) {
            Main.setCurrentPage(new ProfilePage("Profile Page"));
        } else if (source == listingsButton || source == logoButton) {
            Main.setCurrentPage(new ListingListPage());
        } else if (source == cartButton) {
            Main.setCurrentPage(new CartPage());
        } else if (source == createListingButton) {
            Main.setCurrentPage(new CreateListingPage());
        }
    }
}

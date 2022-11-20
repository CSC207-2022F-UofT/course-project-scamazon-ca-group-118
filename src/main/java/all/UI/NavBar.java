package all.UI;

import all.Main;
import all.forms.RegisterForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class NavBar extends JPanel implements ActionListener {
    private JButton profileButton;
    private JButton listingsButton;
    private JButton cartButton;
    private JButton logButton;
    private final SpringLayout LAYOUT;

    public NavBar() {
        this.LAYOUT = new SpringLayout();
        this.setBackground(new Color(185, 235, 255));

        setUpPanel();
        setUpLayout();
    }

    private void setUpPanel() {
        this.setLayout(LAYOUT);
        this.setPreferredSize(new Dimension(1280, 100));
        profileButton = new JButton("Profile");
        listingsButton = new JButton("Listings");
        cartButton = new JButton("Cart");
        logButton = new JButton("Register");

        profileButton.addActionListener(this);
        listingsButton.addActionListener(this);
        cartButton.addActionListener(this);
        logButton.addActionListener(this);

        this.add(profileButton);
        this.add(listingsButton);
        this.add(cartButton);
        this.add(logButton);
    }

    protected void setUpLayout() {
        //vertical alignment
        LAYOUT.putConstraint(SpringLayout.NORTH, logButton, 40, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, cartButton, 0, SpringLayout.NORTH, logButton);
        LAYOUT.putConstraint(SpringLayout.NORTH, listingsButton, 0, SpringLayout.NORTH, logButton);
        LAYOUT.putConstraint(SpringLayout.NORTH, profileButton, 0, SpringLayout.NORTH, logButton);

        //horizontal alignment
        LAYOUT.putConstraint(SpringLayout.EAST, logButton, -60, SpringLayout.EAST, this);
        LAYOUT.putConstraint(SpringLayout.EAST, cartButton, -120, SpringLayout.WEST, logButton);
        LAYOUT.putConstraint(SpringLayout.EAST, listingsButton, -120, SpringLayout.WEST, cartButton);
        LAYOUT.putConstraint(SpringLayout.EAST, profileButton, -120, SpringLayout.WEST, listingsButton);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO refactor this method - it smells. so bad.
        if (e.getSource() == profileButton ||
                e.getSource() == listingsButton ||
                e.getSource() == cartButton) {
            if (Objects.isNull(Main.getCurrentUser())) {
                LoginPage newLoginPage = new LoginPage();
                newLoginPage.setErrorMessage("Please log in or register");
                Main.setCurrentPage(newLoginPage);
            } else {
                if (e.getSource() == profileButton) {
                    Main.setCurrentPage(new ProfilePage("Profile Page"));
                    System.out.println(Main.getCurrentPage().getTitle());
                } else if (e.getSource() == listingsButton) {
                    Main.setCurrentPage(new ListingListPage(Main.getCurrentUser().getListings()));
                } else if (e.getSource() == cartButton) {
                    Main.setCurrentPage(new CartPage("Cart Page", Main.getCurrentUser().getCart()));
                }
            }

        } else if (e.getSource() == logButton) {
            if (logButton.getText().equals("Log In")) {
                logButton.setText("Register");
                Main.setCurrentPage(new LoginPage());
            } else if (logButton.getText().equals("Register")) {
                logButton.setText("Log In");
                //TODO fix this, I don't think having title this many times is necessary
                Main.setCurrentPage(new RegisterPage("Register", new RegisterForm("Register")));
            } else if (logButton.getText().equals("Log Out")) {
                logButton.setText("Log In");
                Main.setCurrentUser(null);
                Main.setCurrentPage(new LoginPage());
            }
        }
    }
}

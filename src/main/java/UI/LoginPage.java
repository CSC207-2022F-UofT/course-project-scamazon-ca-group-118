package UI;

import Main.Main;
import forms.LoginForm;
import forms.RegisterForm;
import useCase.login.LoginFailed;
import useCase.login.LoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The LoginPage class sets up the GUI for each Login Page to be displayed in the View class
 */
public class LoginPage extends Page implements ActionListener {
    private final JButton LOGIN = new JButton("Log in");
    private final JButton REGISTER = new JButton("Register");
    private JLabel errorMessage = new JLabel("");
    private final SpringLayout LAYOUT;
    private JTextField username = new JTextField(15);
    private JPasswordField password = new JPasswordField(15);
    private LabelTextPanel usernameInfo;
    private LabelTextPanel passwordInfo;
    private LabelButtonPanel ifNoAccount;
    private JLabel titleLabel;

    /**
     * The constructor for the LoginPage class
     */
    public LoginPage() {
        super("Scamazon.ca");
        this.LAYOUT = new SpringLayout();

        setUpPanel();
        setUpLayout();
    }

    /**
     * Sets up and adds all components to be displayed on this LoginPage
     */
    private void setUpPanel() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(LAYOUT);

        //Add listeners to buttons
        LOGIN.addActionListener(this);
        REGISTER.addActionListener(this);


        //JLabels
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel ifNoAccountLabel = new JLabel("Don't have an account? ");
        titleLabel = new JLabel("Log In");

        //LabelTextPanels and LabelButtonPanels
        usernameInfo = new LabelTextPanel(usernameLabel, username);
        passwordInfo = new LabelTextPanel(passwordLabel, password);
        ifNoAccount = new LabelButtonPanel(ifNoAccountLabel, REGISTER);

        //add all elements to the panel
        this.add(errorMessage);
        this.add(ifNoAccount);
        this.add(titleLabel);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(LOGIN);
    }

    /**
     * Fixes the layout of each LoginPage
     */
    private void setUpLayout() {
        //Align everything near the middle
        LAYOUT.putConstraint(SpringLayout.WEST, titleLabel, 500, SpringLayout.WEST, this);
        LAYOUT.putConstraint(SpringLayout.WEST, usernameInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.WEST, passwordInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.EAST, LOGIN, 0, SpringLayout.EAST, passwordInfo);
        LAYOUT.putConstraint(SpringLayout.WEST, errorMessage, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.WEST, ifNoAccount, 0, SpringLayout.WEST, titleLabel);

        //Align everything vertically
        LAYOUT.putConstraint(SpringLayout.NORTH, titleLabel, 150, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, usernameInfo, 30, SpringLayout.SOUTH, titleLabel);
        LAYOUT.putConstraint(SpringLayout.NORTH, passwordInfo, 30, SpringLayout.SOUTH, usernameInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, LOGIN, 30, SpringLayout.SOUTH, passwordInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, errorMessage, 30, SpringLayout.SOUTH, LOGIN);
        LAYOUT.putConstraint(SpringLayout.NORTH, ifNoAccount, 90, SpringLayout.SOUTH, LOGIN);
    }

    /**
     * The actionListener for this LoginPage
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LOGIN) {
            try {
                LoginForm form = new LoginForm(username.getText(),
                        String.valueOf(password.getPassword()));
                LoginResponseModel responseModel = form.getResponseModel();
                Main.setCurrentUser(responseModel.getUser());
                Main.setCurrentPage(new ProfilePage("Profile Page"));
            } catch (LoginFailed error) {
                errorMessage.setText(error.getMessage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == REGISTER) {
            Main.setCurrentPage(new RegisterPage("Register", new RegisterForm("Register")));
        }
    }

    /**
     * Sets the error message for this LoginPage
     * @param error the String error message that should be displayed on this LoginPage
     */
    public void setErrorMessage(String error) {
        this.errorMessage.setText(error);
        this.errorMessage.updateUI();
    }
}


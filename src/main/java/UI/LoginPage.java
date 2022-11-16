package UI;

import forms.LoginForm;
import pages.LabelButtonPanel;
import pages.LabelTextPanel;
import useCase.login.LoginFailed;
import useCase.login.LoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends Page implements ActionListener {
    final int WIDTH = 800;
    final int HEIGHT = 600;
    private final JButton LOGIN;
    private final JButton REGISTER;
    private JLabel errorMessage = new JLabel("");

    /**
     * The username entered by the user
     */
    JTextField username = new JTextField(15);
    /**
     * The password entered by the user
     */
    JPasswordField password = new JPasswordField(15);

    public LoginPage() {
        super("Login");
        //TODO change this if we change Page title access
        String title = "Login";

        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setTitle("Scamazon.ca");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel ifNoAccountLabel = new JLabel("Don't have an account? ");

        LOGIN = new JButton("Log in");
        LOGIN.addActionListener(this);
        REGISTER = new JButton("Register");
        REGISTER.addActionListener(this);

        JPanel loginPanel = new JPanel();
        loginPanel.add(LOGIN);
        loginPanel.setBounds(300, 180, 75, 30);
        loginPanel.setAlignmentX(500);
        loginPanel.setAlignmentY(500);

        JPanel registerPanel = new JPanel();
        registerPanel.add(REGISTER);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(106, 60, 50, 25);

        errorMessage.setBounds(100, 220, 275, 25);

        LabelTextPanel usernameInfo = new LabelTextPanel(usernameLabel, username);
        usernameInfo.setBounds(100, 100, 275, 25);
        LabelTextPanel passwordInfo = new LabelTextPanel(passwordLabel, password);
        passwordInfo.setBounds(100, 140, 275, 25);
        LabelButtonPanel ifNoAccount = new LabelButtonPanel(ifNoAccountLabel, registerPanel);
        ifNoAccount.setBounds(100, 250, 275, 50);


        this.add(errorMessage);
        this.add(ifNoAccount);
        this.add(titleLabel);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(loginPanel);
        this.add(new JLabel("")); //It won't let me specify the bounds for the last item added??? this
        //empty JLabel is just a workaround

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LOGIN) {
            try {
                LoginForm form = new LoginForm(username.getText(),
                        String.valueOf(password.getPassword()));
                //TODO implement this part once UserPresenter is running
                LoginResponseModel responseModel = form.getResponseModel();
            } catch (LoginFailed error) {
                errorMessage.setText(error.getMessage());
            }
        } else if (e.getSource() == REGISTER) {
            //TODO implement this, open the registerPage
            System.out.println("Register");
        }
    }

    /**TODO:
     * implement try block to actionPerformed that
     * tries:
     * to create a UserPresenter using the controller then open a UserDetailPage with this presenter.
     */
}


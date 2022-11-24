package UI;

import Main.Main;
import forms.CheckoutForm;
import useCase.checkout.CheckoutResponseModel;
import useCase.login.LoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class CheckoutPage extends Page implements ActionListener {
    private final JButton SUBMIT = new JButton("Submit");
    private JTextField jtUsername = new JTextField(16);
    private JTextField jtName = new JTextField(16);
    private JTextField jtCardNumber = new JTextField(16);
    private JTextField jtCVV = new JTextField(16);
    private JTextField jtExpiration = new JTextField(16);
    private JTextField jtAddress = new JTextField(15);
    private final SpringLayout LAYOUT;
    private LabelTextPanel usernameInfo;
    private LabelTextPanel nameInfo;
    private LabelTextPanel cardNumberInfo;
    private LabelTextPanel cvvInfo;
    private LabelTextPanel expirationInfo;
    private LabelTextPanel addressInfo;
    private JLabel titleLabel;

    public CheckoutPage() {
        super("Scamazon.ca");
        this.LAYOUT = new SpringLayout();

        setUpPanel();
        setUpLayout();
    }

    private void setUpPanel() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(LAYOUT);

        SUBMIT.addActionListener(this);

        JLabel usernameLabel = new JLabel("Username: ");
        JLabel nameLabel = new JLabel("Name: ");
        JLabel cardNumberLabel = new JLabel("Card Number: ");
        JLabel cvvLabel = new JLabel("CVV: ");
        JLabel expirationLabel = new JLabel("Card Expiration: ");
        JLabel addressLabel = new JLabel("Address: ");
        titleLabel = new JLabel("Checkout");

        usernameInfo = new LabelTextPanel(usernameLabel, jtUsername);
        nameInfo = new LabelTextPanel(nameLabel, jtName);
        cardNumberInfo = new LabelTextPanel(cardNumberLabel, jtCardNumber);
        cvvInfo = new LabelTextPanel(cvvLabel, jtUsername);
        expirationInfo = new LabelTextPanel(expirationLabel, jtExpiration);
        addressInfo = new LabelTextPanel(addressLabel, jtAddress);

        this.add(titleLabel);
        this.add(usernameInfo);
        this.add(nameInfo);
        this.add(cardNumberInfo);
        this.add(cvvInfo);
        this.add(expirationInfo);
        this.add(addressInfo);
        this.add(SUBMIT);
    }

    private void setUpLayout() {
        LAYOUT.putConstraint(SpringLayout.WEST, titleLabel, 500, SpringLayout.WEST, this);
        LAYOUT.putConstraint(SpringLayout.WEST, usernameInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.WEST, nameInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.WEST, cardNumberInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.WEST, cvvInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.WEST, expirationInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.WEST, addressInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.EAST, SUBMIT, 0, SpringLayout.EAST, addressInfo);

        //align everything vertically
        LAYOUT.putConstraint(SpringLayout.NORTH, titleLabel, 150, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, usernameInfo, 30, SpringLayout.SOUTH, titleLabel);
        LAYOUT.putConstraint(SpringLayout.NORTH, nameInfo, 30, SpringLayout.SOUTH, usernameInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, cardNumberInfo, 30, SpringLayout.SOUTH, nameInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, cvvInfo, 30, SpringLayout.SOUTH, cardNumberInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, expirationInfo, 30, SpringLayout.SOUTH, cvvInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, addressInfo, 30, SpringLayout.SOUTH, expirationInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, SUBMIT, 30, SpringLayout.SOUTH, addressInfo);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SUBMIT) {
            try {
                CheckoutForm form = new CheckoutForm(jtUsername.getText(), jtName.getText(),
                        jtCardNumber.getText(), jtCVV.getText(),
                        LocalDate.parse(jtExpiration.getText()), jtAddress.getText());
                form.getResponseModel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

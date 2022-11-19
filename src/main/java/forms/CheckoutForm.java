package forms;

import useCase.checkout.CheckoutRequestModel;
import useCase.checkout.CheckoutResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CheckoutForm extends Form implements ActionListener {
    private final String USERNAME;
    private final String NAME;
    private final String CARD_NUMBER;
    private final String CVV;
    private final LocalDate EXPIRATION; //Represents a date (year, month, day (yyyy-MM-dd))
    private final String ADDRESS;
    private CheckoutResponseModel responseModel;
    private JLabel username;
    private JLabel name;
    private JLabel cardNumber;
    private JLabel cvv;
    private JLabel expiration;
    private JLabel address;
    private final JButton SUBMIT;


    public CheckoutForm() {
        super("Checkout");

        this.username = new JLabel("Username");
        JTextField jtUsername = new JTextField();
        this.name = new JLabel("Name");
        JTextField jtName = new JTextField();
        this.cardNumber = new JLabel("Card Number");
        JTextField jtCardNumber = new JTextField();
        this.cvv = new JLabel("CVV");
        JTextField jtCVV = new JTextField();
        this.expiration = new JLabel("Expiration Date");
        JTextField jtExpiration = new JTextField();
        this.address = new JLabel("Address");
        JTextField jtAddress = new JTextField();
        this.SUBMIT = new JButton("Submit");

        JPanel jpForm = new JPanel();
        jpForm.setLayout(new GridLayout(5, 2, 10, 5));
        jpForm.add(this.username);
        jpForm.add(this.name);
        jpForm.add(this.cardNumber);
        jpForm.add(this.cvv);
        jpForm.add(this.expiration);
        jpForm.add(this.address);
        jpForm.add(this.SUBMIT);

        JPanel jpMainPanel = new JPanel();
        jpMainPanel.setLayout(new BorderLayout(10, 10));
        jpMainPanel.add(jpForm, BorderLayout.NORTH);
        jpMainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.SUBMIT.addActionListener(this);

        this.USERNAME = jtUsername.getText();
        this.NAME = jtName.getText();
        this.CARD_NUMBER = jtCardNumber.getText();
        this.CVV = jtCVV.getText();
        this.EXPIRATION = LocalDate.parse(jtExpiration.getText());
        this.ADDRESS = jtAddress.getText();
    }

    @Override
    protected boolean validateForm() {
        //current day
        LocalDate today = LocalDate.now();

        //check number length is equal to 16
        if (!(this.CARD_NUMBER.length() == 16)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid card number.");
            return false;
        }
        //check cvv length is equal to 3
        else if (!(this.CVV.length() == 3)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid CVV.");
            return false;
        }
        //check expiration is after current date
        else if (!(this.EXPIRATION.isAfter(today))) {
            JOptionPane.showMessageDialog(this, "Card is expired.");
            return false;
        }
        //checks to make sure all fields are filled out
        else if (this.USERNAME.isEmpty() || this.NAME.isEmpty() || this.ADDRESS.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return false;
        }
        return true;
    }

    @Override
    protected void submitForm() {
        if (this.validateForm()) {
            CheckoutRequestModel requestModel = new CheckoutRequestModel(USERNAME);
            responseModel = new CheckoutResponseModel(requestModel);
            //redirects User back to ListingListPage
            // return new ListingListPage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.SUBMIT) {
            this.submitForm();
        }
    }
}

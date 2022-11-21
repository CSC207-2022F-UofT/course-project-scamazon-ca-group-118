package forms;

import useCase.checkout.CheckoutRequestModel;
import useCase.checkout.CheckoutResponseModel;

import java.io.IOException;
import java.time.*;
import javax.swing.*;
import java.time.LocalDate;

public class CheckoutForm extends Form {
    private final String USERNAME;
    private final String NAME;
    private final String CARD_NUMBER;
    private final String CVV;
    private final LocalDate EXPIRATION; //Represents a date (year, month, day (yyyy-MM-dd))
    private final String ADDRESS;
    private CheckoutResponseModel responseModel;
    public CheckoutForm(String username, String name, String card_number, String cvv, LocalDate expiration, String address) {
        super("Checkout");
        this.USERNAME = username;
        this.NAME = name;
        this.CARD_NUMBER = card_number;
        this.CVV = cvv;
        this.EXPIRATION = expiration;
        this.ADDRESS = address;
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
    protected void submitForm() throws IOException {
        if (this.validateForm()) {
            CheckoutRequestModel requestModel = new CheckoutRequestModel(USERNAME);
            responseModel = new CheckoutResponseModel(requestModel);
            //redirects User back to ListingListPage
            // return new ListingListPage();
        }
    }

    public CheckoutResponseModel getResponseModel() throws IOException {
        this.submitForm();
        return responseModel;
    }
}

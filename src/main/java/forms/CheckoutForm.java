package forms;

import Main.Main;
import UI.ListingListPage;
import entities.User;
import useCase.checkout.CheckoutRequestModel;
import useCase.checkout.CheckoutResponseModel;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;

public class CheckoutForm extends Form {
    private final User BUYER;
    private final String NAME;
    private final String CARD_NUMBER;
    private final String CVV;
    private final LocalDate EXPIRATION; //Represents a date (year, month, day (yyyy-MM-dd))
    private final String ADDRESS;
    private CheckoutResponseModel responseModel;
    public CheckoutForm(User buyer, String name, String card_number, String cvv, LocalDate expiration, String address) {
        super("Checkout");
        this.BUYER = buyer;
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
            JOptionPane.showMessageDialog(this, "This card is expired.");
            return false;
        }
        //checks to make sure all fields are filled out
        else if (this.NAME.isEmpty() || this.ADDRESS.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.");
            return false;
        }
        return true;
    }

    @Override
    protected void submitForm() throws IOException {
        if (this.validateForm()) {
            CheckoutRequestModel requestModel = new CheckoutRequestModel(BUYER);
            responseModel = new CheckoutResponseModel(requestModel);
            //redirects User back to ListingListPage
            Main.setCurrentPage(new ListingListPage());
        }
    }

    public CheckoutResponseModel getResponseModel() throws IOException {
        this.submitForm();
        return responseModel;
    }
}

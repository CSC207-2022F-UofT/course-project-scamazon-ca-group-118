package forms;

import useCase.checkout.CheckoutRequestModel;
import useCase.checkout.CheckoutResponseModel;

import java.time.*;

public class CheckoutForm extends Form {
    private final String USERNAME;
    private final String CARD_NUMBER;
    private final String CVV;
    private final LocalDate EXPIRATION; //Represents a date (year, month, day (yyyy-MM-dd))
    private final String ADDRESS;
    private CheckoutResponseModel responseModel;



    public CheckoutForm(String name, String number, String cvv, LocalDate expiration, String address) {
        super("Checkout");
        this.USERNAME = name;
        this.CARD_NUMBER = number;
        this.CVV = cvv;
        this.EXPIRATION = expiration;
        this.ADDRESS = address;
    }

    @Override
    protected boolean validateForm() {
        //check number length is equal to 16
        if (!(this.CARD_NUMBER.length() == 16)) {
            return false;
            //alert UI
        }
        //check cvv length is equal to 3
        if (!(this.CVV.length() == 3)) {
            return false;
            //alert UI
        }
        //check expiration is after today's date
        LocalDate today = LocalDate.now();
        if (!(this.EXPIRATION.isAfter(today))) {
            return false;
            //alert UI
        }
        return true;
    }

    @Override
    protected void submitForm() {
        if (this.validateForm()) {
            CheckoutRequestModel requestModel = new CheckoutRequestModel(USERNAME);
            responseModel = new CheckoutResponseModel(requestModel);
            //redirect to "home" page
            //call function to remove items in cart from database
        }
    }
}

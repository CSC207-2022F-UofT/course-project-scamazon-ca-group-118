package forms;

import main.Main;
import ui.ListingListPage;
import com.opencsv.exceptions.CsvException;
import entities.User;
import use_case.checkout.CheckoutRequestModel;
import use_case.checkout.CheckoutResponseModel;

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
    private String message;
    public CheckoutForm(User buyer, String name, String card_number, String cvv, LocalDate expiration, String address) {
        super("Checkout");
        this.BUYER = buyer;
        this.NAME = name;
        this.CARD_NUMBER = card_number;
        this.CVV = cvv;
        this.EXPIRATION = expiration;
        this.ADDRESS = address;
        this.message = "";
    }

    @Override
    protected boolean validateForm() {
        //current day
        LocalDate today = LocalDate.now();

        //check number length is equal to 16
        if (!(this.CARD_NUMBER.length() == 16)) {
            this.message = "Please enter a valid card number.";
            return false;
        }
        //check cvv length is equal to 3
        else if (!(this.CVV.length() == 3)) {
            this.message = "Please enter a valid CVV.";
            return false;
        }
        //check expiration is after current date
        else if (!(this.EXPIRATION.isAfter(today))) {
            this.message = "This card is expired.";
            return false;
        }
        //checks to make sure all fields are filled out
        else if (this.NAME.isEmpty() || this.ADDRESS.isEmpty()) {
            this.message = "Please fill out all fields.";
            return false;
        }
        return true;
    }

    @Override
    protected void submitForm() throws IOException, CsvException {
        if (this.validateForm()) {
            CheckoutRequestModel requestModel = new CheckoutRequestModel(BUYER);
            responseModel = new CheckoutResponseModel(requestModel);
            //redirects User back to ListingListPage
            Main.setCurrentPage(new ListingListPage());
        }
    }

    public CheckoutResponseModel getResponseModel() throws IOException, CsvException {
        this.submitForm();
        return responseModel;
    }

    public String getMessage() {
        return message;
    }
}

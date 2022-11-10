package forms;
import java.time.*;

public class CheckoutForm extends Form {
    private final String name;
    private String number;
    private String cvv;
    private LocalDate expiration; //Represents a date (year, month, day (yyyy-MM-dd))
    private String address;

    public CheckoutForm(String title, String name, String number, String cvv, LocalDate expiration, String address) {
        super(title);
        this.name = name;
        this.number = number;
        this.cvv = cvv;
        this.expiration = expiration;
        this.address = address;
    }

    @Override
    boolean validateForm() {
        //check number length is equal to 16
        if (!(getNumber().length() == 16)) {
            return false;
            //alert UI
        }
        //check cvv length is equal to 3
        if (!(getCvv().length() == 3)) {
            return false;
            //alert UI
        }
        //check expiration is after today's date
        LocalDate today = LocalDate.now();
        if (!(getExpiration().isAfter(today))) {
            return false;
            //alert UI
        }
        return true;
    }

    @Override
    void submitForm() {
        if (validateForm()) {
            //redirect to "home" page
            //call function to remove item from database
        }
    }

    public String getNumber() {
        return number;
    }

    public String getCvv() {
        return cvv;
    }

    public LocalDate getExpiration() {
        return expiration;
    }
}

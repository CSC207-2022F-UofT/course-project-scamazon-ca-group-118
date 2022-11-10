package forms;

public class SearchForm extends Form {
    //TODO Make after database interactor is there

    private String query;


    @Override
    boolean validateForm() {
        return true;
    }

    @Override
    void submitForm() {
        if (this.validateForm()) {

        }

    }
}

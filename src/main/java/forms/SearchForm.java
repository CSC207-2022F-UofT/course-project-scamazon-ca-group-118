package forms;

import database.ListingDatabaseGateway;
import use_case.search.SearchRequestModel;
import use_case.search.SearchResponseModel;

import java.io.IOException;

public class SearchForm extends Form {

    public String query;

    private final ListingDatabaseGateway DATABASE_GATEWAY;

    private SearchResponseModel responseModel;

    public SearchForm(String query, ListingDatabaseGateway databaseGateway) {
        super("Search");
        this.query = query;
        this.DATABASE_GATEWAY = databaseGateway;
    }


    @Override
    protected boolean validateForm() {
        return true;
    }

    @Override
    protected void submitForm() throws IOException {
        if (this.validateForm()) {
            SearchRequestModel requestModel = new SearchRequestModel(query, DATABASE_GATEWAY);
            this.responseModel = new SearchResponseModel(requestModel);

        }

    }

    public SearchResponseModel getResponseModel() throws IOException {
        this.submitForm();
        return responseModel;
    }
}

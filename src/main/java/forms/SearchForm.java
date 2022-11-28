package forms;

import database.ListingDatabaseGateway;
import useCase.Search.SearchRequestModel;
import useCase.Search.SearchResponseModel;

import java.io.IOException;

public class SearchForm extends Form {
    //TODO Make after database interactor is there

    public String query;

    private ListingDatabaseGateway databaseGateway;

    private SearchResponseModel responseModel;

    public SearchForm(String query, ListingDatabaseGateway databaseGateway) {
        super("Search");
        this.query = query;
        this.databaseGateway = databaseGateway;
    }


    @Override
    protected boolean validateForm() {
        return true;
    }

    @Override
    protected void submitForm() throws IOException {
        if (this.validateForm()) {
            SearchRequestModel requestModel = new SearchRequestModel(query, databaseGateway);
            this.responseModel = new SearchResponseModel(requestModel);

        }

    }

    public SearchResponseModel getResponseModel() throws IOException {
        this.submitForm();
        return responseModel;
    }
}

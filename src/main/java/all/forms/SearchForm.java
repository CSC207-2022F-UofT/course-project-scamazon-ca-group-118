package all.forms;

import all.database.SearchDatabaseGateway;
import all.useCase.Search.SearchRequestModel;
import all.useCase.Search.SearchResponseModel;

public class SearchForm extends Form {
    //TODO Make after database interactor is there

    public String query;

    private SearchDatabaseGateway databaseGateway;

    private SearchResponseModel responseModel;

    public SearchForm(String query, SearchDatabaseGateway databaseGateway) {
        super("Search");
        this.query = query;
        this.databaseGateway = databaseGateway;
    }


    @Override
    protected boolean validateForm() {
        return true;
    }

    @Override
    protected void submitForm() {
        if (this.validateForm()) {
            SearchRequestModel requestModel = new SearchRequestModel(query, databaseGateway);
            this.responseModel = new SearchResponseModel(requestModel);

        }

    }

    public SearchResponseModel getResponseModel() {
        this.submitForm();
        return responseModel;
    }
}

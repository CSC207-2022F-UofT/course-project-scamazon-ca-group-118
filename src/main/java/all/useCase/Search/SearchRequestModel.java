package all.useCase.Search;

import all.database.SearchDatabaseGateway;

public class SearchRequestModel {
    private String query;

    private SearchDatabaseGateway databaseGateway;

    public SearchRequestModel(String query, SearchDatabaseGateway databaseGateway) {
        this.query = query;
        this.databaseGateway = databaseGateway;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public SearchDatabaseGateway getDatabaseGateway() {
        return databaseGateway;
    }

    public void setDatabaseGateway(SearchDatabaseGateway databaseGateway) {
        this.databaseGateway = databaseGateway;
    }
}

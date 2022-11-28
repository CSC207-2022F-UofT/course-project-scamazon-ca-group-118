package useCase.Search;

import database.ListingDatabaseGateway;

public class SearchRequestModel {
    private String query;

    private ListingDatabaseGateway databaseGateway;

    public SearchRequestModel(String query, ListingDatabaseGateway databaseGateway) {
        this.query = query;
        this.databaseGateway = databaseGateway;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ListingDatabaseGateway getDatabaseGateway() {
        return databaseGateway;
    }

    public void setDatabaseGateway(ListingDatabaseGateway databaseGateway) {
        this.databaseGateway = databaseGateway;
    }
}

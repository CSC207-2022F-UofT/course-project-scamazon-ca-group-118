package use_case.search;

import entities.Listing;

import java.io.IOException;
import java.util.ArrayList;

public class SearchResponseModel {

    private final ArrayList<Listing> LISTING;

    public SearchResponseModel(SearchRequestModel requestModel) throws IOException {
        SearchInteractor results = new SearchInteractor(requestModel.getQuery(), requestModel.getDatabaseGateway());
        this.LISTING = results.getListings();
    }

    public ArrayList<Listing> getListings() {
        return LISTING;
    }
}

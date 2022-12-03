package useCase.Search;

import database.ListingDatabaseGateway;
import entities.Listing;

import java.io.IOException;
import java.util.ArrayList;

public class SearchResponseModel {

    private ArrayList<Listing> listings;

    public SearchResponseModel(SearchRequestModel requestModel) throws IOException {
        SearchInteractor results = new SearchInteractor(requestModel.getQuery(), requestModel.getDatabaseGateway());
        this.listings = results.getListings();
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }
}

package useCase.Search;

import entities.Listing;

import java.util.ArrayList;

public class SearchResponseModel {

    private ArrayList<Listing> listings;

    public SearchResponseModel(SearchRequestModel requestModel) {
        SearchInteractor results = new SearchInteractor(requestModel.getQuery(), requestModel.getDatabaseGateway());
        this.listings = results.getListings();
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }
}

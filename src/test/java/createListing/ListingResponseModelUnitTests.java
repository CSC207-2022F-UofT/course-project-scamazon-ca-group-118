package createListing;
import org.junit.jupiter.api.Test;
import useCase.createListing.ListingRequestModel;
import useCase.createListing.ListingResponseModel;

import static org.junit.jupiter.api.Assertions.*;
public class ListingResponseModelUnitTests {
    @Test
    void validCreateListing(){
        ListingResponseModel lrm = new ListingResponseModel(new ListingRequestModel())
    }
}

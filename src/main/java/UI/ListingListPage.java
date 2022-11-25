package UI;
import entities.Listing;
import database.DatabaseController;
import forms.SearchForm;
import useCase.Search.SearchResponseModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ListingListPage extends Page implements ActionListener {
    private JLabel titleLabel;
    private final SpringLayout LAYOUT;
    private SearchBarPanel SearchBar;
    private JTextField jtSearch = new JTextField(20);
    private final JButton SEARCH = new JButton("Search");
    private DatabaseController controller = new DatabaseController();

    public ListingListPage() throws IOException {
        super("Scamazon.ca");
        this.LAYOUT = new SpringLayout();

        ArrayList<Listing> allListings = controller.getAllListings();

        //displays all listings from database before User has searched anything
        setUpPanel(allListings);
    }

    private void setUpPanel(List<Listing> listings) {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(LAYOUT);

        //SearchBar with TextField and SEARCH Button
        SEARCH.addActionListener(this);
        SearchBar = new SearchBarPanel(jtSearch, SEARCH);
        this.add(SearchBar);

        //title label
        titleLabel = new JLabel("Listings");
        this.add(titleLabel);


        //LAYOUT FOR PANEL
        //align SearchBar and titleLabel near the middle
        LAYOUT.putConstraint(SpringLayout.WEST, SearchBar, 500, SpringLayout.WEST, this);
        LAYOUT.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.WEST, SearchBar);

        //align SearchBar and titleLabel vertically
        LAYOUT.putConstraint(SpringLayout.NORTH, SearchBar, 150, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, titleLabel, 30, SpringLayout.SOUTH, SearchBar);

        //keeps track of previous listing for layout
        ListingPanel previousListing = null;

        //looping through listings
        for (Listing listing: listings) {
            //info needed to create a ListingPanel for each listing
            JButton listingDetails = new JButton(listing.getTitle());
            JLabel resultPrice = new JLabel("Price: " + listing.getPrice());
            JLabel resultImage = new JLabel(listing.getImagePath());
            JLabel resultDescription = new JLabel(listing.getDescription());
            JLabel resultDate = new JLabel(listing.getDate().toString());

            //creating individual ListingPanel element
            ListingPanel listingInfo = new ListingPanel(listingDetails, resultPrice, resultImage, resultDescription,
                    resultDate);

            this.add(listingInfo);

            //align listing in the middle
            LAYOUT.putConstraint(SpringLayout.WEST, listingInfo, 0, SpringLayout.WEST, SearchBar);

            //align listing vertically
            if (previousListing == null) {
                //is the first listing --> put under titleLabel
                LAYOUT.putConstraint(SpringLayout.NORTH, listingInfo, 30, SpringLayout.SOUTH, titleLabel);
                previousListing = listingInfo; //set previousListing to the one we just created
            }
            else {
                //not the first listing --> put under previousListing
                LAYOUT.putConstraint(SpringLayout.NORTH, listingInfo, 30, SpringLayout.SOUTH, previousListing);
                previousListing = listingInfo; //reset previousListing to the one we just created
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SEARCH) {
            SearchForm form = new SearchForm(jtSearch.getText(), controller);
            SearchResponseModel responseModel = form.getResponseModel();
            ArrayList<Listing> searchListings = responseModel.getListings();
            setUpPanel(searchListings);
        }
    }
}

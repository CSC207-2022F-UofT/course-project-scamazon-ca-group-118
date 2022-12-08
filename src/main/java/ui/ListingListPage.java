package ui;

import main.Main;
import entities.Listing;
import database.DatabaseController;
import forms.SearchForm;
import use_case.search.SearchResponseModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

public class ListingListPage extends Page implements ActionListener {
    private final SpringLayout LAYOUT;
    private final JTextField JT_SEARCH = new JTextField(20);
    private final JButton SEARCH = new JButton("Search");
    private final DatabaseController CONTROLLER = new DatabaseController();
    private final ArrayList<JButton> BUTTONS = new ArrayList<>();
    private List<Listing> displayedListings;

    public ListingListPage() throws IOException {
        super("Scamazon.ca");
        this.LAYOUT = new SpringLayout();

        ArrayList<Listing> allListings = CONTROLLER.getAllListings();

        //displays all listings from database before User has searched anything
        setUpPanel(allListings);
    }

    private void setUpPanel(List<Listing> listings) {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(LAYOUT);
        this.displayedListings = listings;

        //SearchBar with TextField and SEARCH Button
        SEARCH.addActionListener(this);
        SearchBarPanel searchBar = new SearchBarPanel(JT_SEARCH, SEARCH);
        this.add(searchBar);

        //title label
        JLabel titleLabel = new JLabel("Listings");
        this.add(titleLabel);


        //LAYOUT FOR PANEL
        //align SearchBar and titleLabel near the middle
        LAYOUT.putConstraint(SpringLayout.WEST, searchBar, 500, SpringLayout.WEST, this);
        LAYOUT.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.WEST, searchBar);

        //align SearchBar and titleLabel vertically
        LAYOUT.putConstraint(SpringLayout.NORTH, searchBar, 150, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, titleLabel, 30, SpringLayout.SOUTH, searchBar);

        //keeps track of previous listing for layout
        ListingPanel previousListing = null;

        //looping through listings
        for (Listing listing : listings) {
            //info needed to create a ListingPanel for each listing
            JButton listingDetails = new JButton(listing.getTitle());
            listingDetails.addActionListener(this);
            BUTTONS.add(listingDetails);
            JLabel resultPrice = new JLabel("Price: " + listing.getPrice());
            JLabel resultImage = new JLabel(listing.getImagePath());
            JLabel resultDescription = new JLabel(listing.getDescription());
            JLabel resultDate = new JLabel(listing.getDate().toString());

            //creating individual ListingPanel element
            ListingPanel listingInfo = new ListingPanel(listingDetails, resultPrice, resultImage, resultDescription,
                    resultDate);

            this.add(listingInfo);

            //align listing in the middle
            LAYOUT.putConstraint(SpringLayout.WEST, listingInfo, 0, SpringLayout.WEST, searchBar);

            //align listing vertically
            if (previousListing == null) {
                //is the first listing --> put under titleLabel
                LAYOUT.putConstraint(SpringLayout.NORTH, listingInfo, 30, SpringLayout.SOUTH, titleLabel);
                previousListing = listingInfo; //set previousListing to the one we just created
            } else {
                //not the first listing --> put under previousListing
                LAYOUT.putConstraint(SpringLayout.NORTH, listingInfo, 30, SpringLayout.SOUTH, previousListing);
                previousListing = listingInfo; //reset previousListing to the one we just created
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SEARCH) {
            SearchForm form = new SearchForm(JT_SEARCH.getText(), CONTROLLER);

            SearchResponseModel responseModel;
            try {
                responseModel = form.getResponseModel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            ArrayList<Listing> searchListings = responseModel.getListings();
            setUpPanel(searchListings);


        } else if (BUTTONS.contains(e.getSource())) {
            String title = "";
            for (JButton button : BUTTONS) {
                if (button == e.getSource()) {
                    title = button.getText();
                    break;
                }
            }
            for (Listing listing : displayedListings) {
                if (Objects.equals(listing.getTitle(), title)) {

                    try {
                        Main.setCurrentPage(new ListingDetailPage(listing));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    break;
                }
            }
        }
    }
}

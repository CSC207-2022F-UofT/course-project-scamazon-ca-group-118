package UI;

import Main.Main;
import entities.Listing;
import database.DatabaseController;
import forms.SearchForm;
import useCase.Search.SearchResponseModel;
import useCase.login.LoginResponseModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ListingListPage extends Page implements ActionListener {
    private JLabel titleLabel;
    private final SpringLayout LAYOUT;
    private SearchBarPanel SearchBar;
    private JTextField jtSearch = new JTextField(20);
    private final JButton SEARCH = new JButton("Search");
    private DatabaseController controller = new DatabaseController();
    private ArrayList<JButton> buttons = new ArrayList<>();
    private List<Listing> displayedListings;

    public ListingListPage() throws IOException {
        super("Scamazon.ca");
        this.LAYOUT = new SpringLayout();

        ArrayList<Listing> allListings = controller.getAllListings();

        //displays all listings from database before User has searched anything
        setUpPanel(allListings);
    }

    private void setUpPanel(List<Listing> listings) throws IOException {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(LAYOUT);
        this.displayedListings = listings;

        //SearchBar with TextField and SEARCH Button
        SEARCH.addActionListener(this);
        SearchBar = new SearchBarPanel(jtSearch, SEARCH);
        this.add(SearchBar);

        //title label
        titleLabel = new JLabel("Listings");
        this.add(titleLabel);


        //LAYOUT FOR PANEL
        //align SearchBar and titleLabel near the middle
        LAYOUT.putConstraint(SpringLayout.WEST, SearchBar, 350, SpringLayout.WEST, this);
        LAYOUT.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.WEST, SearchBar);

        //align SearchBar and titleLabel vertically
        LAYOUT.putConstraint(SpringLayout.NORTH, SearchBar, 150, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, titleLabel, 30, SpringLayout.SOUTH, SearchBar);

        //keeps track of previous listing for layout
        ListingPanel previousListing = null;
        JPanel panelOfListings = new JPanel();
        panelOfListings.setLayout(new GridLayout(4, 1));
        JScrollPane scroll = new JScrollPane(panelOfListings);
        scroll.setPreferredSize(new Dimension(800, 400));
        LAYOUT.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, SearchBar);
        LAYOUT.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.SOUTH, SearchBar);
        this.add(scroll);

        //looping through listings
        for (Listing listing : listings) {
            //info needed to create a ListingPanel for each listing
            JButton listingDetails = new JButton(listing.getTitle());
            listingDetails.addActionListener(this);
            buttons.add(listingDetails);
            JLabel resultPrice = new JLabel("Price: " + listing.getPrice());
            String filepath = listing.getImagePath();
            BufferedImage rawImage = ImageIO.read(new File(filepath));
            Image scaled_image = rawImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel resultImage = new JLabel(new ImageIcon(scaled_image));
            JLabel resultDescription = new JLabel("<html>" + listing.getDescription() + "</html>");
            JLabel resultDate = new JLabel(listing.getDate().toString());

            //creating individual ListingPanel element
            ListingPanel listingInfo = new ListingPanel(listingDetails, resultPrice, resultImage, resultDescription,
                    resultDate);

            panelOfListings.add(listingInfo);

            //align listing in the middle


            //align listing vertically
/*
            if (previousListing == null) {
                //is the first listing --> put under titleLabel
                layout.putConstraint(SpringLayout.NORTH, listingInfo, 30, SpringLayout.SOUTH, panelOfListings);
                previousListing = listingInfo; //set previousListing to the one we just created
            } else {
                //not the first listing --> put under previousListing
                layout.putConstraint(SpringLayout.NORTH, listingInfo, 30, SpringLayout.SOUTH, previousListing);
                previousListing = listingInfo; //reset previousListing to the one we just created
            }

 */

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SEARCH) {
            try {
                this.buttons = new ArrayList<>();
                SearchForm form = new SearchForm(jtSearch.getText(), controller);
                SearchResponseModel responseModel = form.getResponseModel();
                ArrayList<Listing> searchListings = responseModel.getListings();
                setUpPanel(searchListings);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (buttons.contains(e.getSource())) {
            String title = "";
            for (JButton button : buttons) {
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

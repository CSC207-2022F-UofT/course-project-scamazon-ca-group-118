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
    private final SpringLayout LAYOUT;
    private final JTextField JT_SEARCH = new JTextField(20);
    private final JButton SEARCH = new JButton("Search");
    private final DatabaseController CONTROLLER = new DatabaseController();
    private ArrayList<JButton> BUTTONS = new ArrayList<>();
    private List<Listing> displayedListings;

    public ListingListPage(ArrayList<Listing> listings) throws IOException {
        super("Scamazon.ca");
        this.LAYOUT = new SpringLayout();

        //displays all listings from database before User has searched anything
        setUpPanel(listings);
    }
    public ListingListPage() throws IOException {
        super("Scamazon.ca");
        this.LAYOUT = new SpringLayout();

        //displays all listings from database before User has searched anything
        DatabaseController db = new DatabaseController();
        setUpPanel(db.getAllListings());
    }

    private void setUpPanel(List<Listing> listings) throws IOException {
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
        LAYOUT.putConstraint(SpringLayout.WEST, searchBar, 200, SpringLayout.WEST, this);
        LAYOUT.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.WEST, searchBar);
        
        //align SearchBar and titleLabel vertically
        LAYOUT.putConstraint(SpringLayout.NORTH, searchBar, 150, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, titleLabel, 30, SpringLayout.SOUTH, searchBar);

        JPanel panelOfListings = new JPanel();
        panelOfListings.setLayout(new GridLayout(4, 1));
        JScrollPane scroll = new JScrollPane(panelOfListings);
        scroll.setPreferredSize(new Dimension(1000, 400));
        LAYOUT.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, searchBar);
        LAYOUT.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.SOUTH, searchBar);
        this.add(scroll);

        //looping through listings
        for (Listing listing : listings) {
            //info needed to create a ListingPanel for each listing
            JButton listingDetails = new JButton(listing.getTitle());
            listingDetails.addActionListener(this);
            BUTTONS.add(listingDetails);
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
            LAYOUT.putConstraint(SpringLayout.WEST, listingInfo, 0, SpringLayout.WEST, searchBar);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SEARCH) {
            try {
                SearchForm form = new SearchForm(JT_SEARCH.getText(), CONTROLLER);
                SearchResponseModel responseModel = form.getResponseModel();
                ArrayList<Listing> searchListings = responseModel.getListings();
                Main.setCurrentPage(new ListingListPage(searchListings));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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

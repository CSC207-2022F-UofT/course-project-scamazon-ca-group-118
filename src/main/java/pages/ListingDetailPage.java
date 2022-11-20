package pages;

import features.Listing;
import forms.AddToCartForm;
import useCase.addToCart.AddToCartResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListingDetailPage extends Page implements ActionListener {
    final int WIDTH = 1280;
    final int HEIGHT = 570;

    private Listing listing;
    //TODO this is hardcoded
    private boolean canAddToCart = true;
    private AddToCartForm addToCartForm;
    private JLabel message;
    public JButton addToCartButton;

    public ListingDetailPage(String title, Listing listing) {
        super(title);
        this.listing = listing;

        JPanel listingDetailPanel = new JPanel();
        listingDetailPanel.setLayout(null);

        JLabel listingTitle = new JLabel(listing.getTitle());
        listingTitle.setSize(500, 60);
        listingTitle.setLocation(500, 20);
        listingTitle.setFont(new Font("Arial", Font.PLAIN, 50));
        listingDetailPanel.add(listingTitle);

        JLabel listingDescription = new JLabel(listing.getDescription());
        listingDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        listingDescription.setSize(300, 300);
        listingDescription.setLocation(650, 20);
        listingDetailPanel.add(listingDescription);

        JLabel listingPrice = new JLabel(String.format("Price: %.2f", listing.getPrice()));
        listingPrice.setFont(new Font("Arial", Font.PLAIN, 20));
        listingPrice.setLocation(500, 320);
        listingPrice.setSize(500, 25);
        listingDetailPanel.add(listingPrice);

        JLabel listingSellerAndDate = new JLabel(String.format("Listed by %s on %s", listing.getSeller().getUsername(),
                String.valueOf(listing.getDate())));
        listingSellerAndDate.setFont(new Font("Arial", Font.PLAIN, 20));
        listingSellerAndDate.setLocation(500, 360);
        listingSellerAndDate.setSize(500, 25);
        listingDetailPanel.add(listingSellerAndDate);

        JLabel listingSellerRating = new JLabel(
                String.format("%s has a %o star rating", listing.getSeller().getUsername(), listing.getSeller().calculateRating()));
        listingSellerRating.setFont(new Font("Arial", Font.PLAIN, 20));
        listingSellerRating.setLocation(500, 400);
        listingSellerRating.setSize(500, 25);
        listingDetailPanel.add(listingSellerRating);

        addToCartButton = new JButton("Add to cart");
        addToCartButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addToCartButton.setLocation(500, 440);
        addToCartButton.setSize(150, 50);
        addToCartButton.addActionListener(this);
        listingDetailPanel.add(addToCartButton);

        message = new JLabel("");
        message.setFont(new Font("Arial", Font.PLAIN, 20));
        message.setLocation(500, 480);
        message.setSize(500, 50);
        listingDetailPanel.add(message);

        try {
            this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
            this.setTitle("Scamazon.ca");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);

//            if (!this.canAddToCart) {
//                ADD_TO_CART.setEnabled(false);
//            }
//            ADD_TO_CART.addActionListener(this);

            this.add(listingDetailPanel);
            listingDetailPanel.setVisible(true);
            this.pack();
            this.setVisible(true);
        } catch (Error e) {
            System.out.println(e);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addToCartButton) {
            // TODO: add it to cart and handle potential errors
            message.setText("Item added to cart!");
            System.out.println("Item added to cart!");
        }
    }

}

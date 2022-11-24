package UI;

import Main.Main;
import database.DatabaseController;
import entities.Listing;
import entities.User;
import forms.AddToCartForm;
import useCase.addToCart.AddToCartResponseModel;
import database.DatabaseController.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ListingDetailPage extends Page implements ActionListener {
    final int WIDTH = 1280;
    final int HEIGHT = 570;

    private final DatabaseController db = new DatabaseController<>();
    private final Listing listing;

    private JLabel message;
    private JButton addToCartButton;

    public ListingDetailPage(String title, Listing listing) {
        super(title);
        this.listing = listing;
        boolean canAddToCart = canAddToCart();
        this.setPreferredSize(new Dimension(1280, 720));

        User listingSeller = getListingSeller();
        if (listingSeller == null) {
            message.setLocation(500, 300);
            message.setVisible(true);
            this.add(message);
            this.setVisible(true);
            return;
        }

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

        JLabel listingSellerAndDate = new JLabel(String.format("Listed by %s on %s", listingSeller.getUsername(),
                String.valueOf(listing.getDate())));
        listingSellerAndDate.setFont(new Font("Arial", Font.PLAIN, 20));
        listingSellerAndDate.setLocation(500, 360);
        listingSellerAndDate.setSize(500, 25);
        listingDetailPanel.add(listingSellerAndDate);

        JLabel listingSellerRating = new JLabel(
                String.format("%s has a %o star rating", listingSeller.getUsername(), listingSeller.calculateRating()));
        listingSellerRating.setFont(new Font("Arial", Font.PLAIN, 20));
        listingSellerRating.setLocation(500, 400);
        listingSellerRating.setSize(500, 25);
        listingDetailPanel.add(listingSellerRating);

        if (canAddToCart) {
            addToCartButton = new JButton("Add to cart");
            addToCartButton.setFont(new Font("Arial", Font.PLAIN, 20));
            addToCartButton.setLocation(500, 440);
            addToCartButton.setSize(150, 50);
            addToCartButton.addActionListener(this);
            listingDetailPanel.add(addToCartButton);

            message.setFont(new Font("Arial", Font.PLAIN, 20));
            message.setLocation(500, 480);
            message.setSize(500, 50);
            listingDetailPanel.add(message);
        } else {
            message.setFont(new Font("Arial", Font.PLAIN, 20));
            message.setLocation(500, 440);
            message.setSize(500, 50);
            listingDetailPanel.add(message);
        }


        try {
            this.setTitle("Scamazon.ca");
            this.add(listingDetailPanel);
            listingDetailPanel.setVisible(true);
            this.setVisible(true);
        } catch (Error e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addToCartButton) {
            // hide add to cart button, move message to where button was
            addToCartButton.setVisible(false);
            message.setLocation(500, 440);
            try {
                // update message on success
                db.addListingToUserCart(Main.getCurrentUser(), listing);
                message.setText("Item added to cart!");
            } catch (IOException error) {
                // update message on success, log error
                System.out.println(error.getMessage());
                message.setText("Error adding item to cart");
            }
        }
    }

    private boolean canAddToCart() {

        if (db.currentUserHasListingInCart(Main.getCurrentUser(), listing)) {
            message = new JLabel("This item is already in your cart");
            return false;
        } else if (Main.getCurrentUser().getCart().getItems().contains(listing)) {
            message = new JLabel("This is your listing");
            return false;
        } else {
            message = new JLabel("");
            return true;
        }
    }

    private User getListingSeller() {
        try {
            return db.getUserWithUsername(listing.getSellerUsername());
        } catch (IOException error) {
            System.out.println(error.getMessage());
            message.setText("Error getting the seller username");
            return null;
        }
    }
}

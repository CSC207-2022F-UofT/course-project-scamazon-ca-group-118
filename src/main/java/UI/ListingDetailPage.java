package UI;

import Main.Main;
import database.DatabaseController;
import entities.Listing;
import entities.User;
import forms.AddToCartForm;
import useCase.addToCart.AddToCartResponseModel;
import database.DatabaseController.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ListingDetailPage extends Page implements ActionListener {
    final int WIDTH = 1280;
    final int HEIGHT = 570;

    private final DatabaseController db = new DatabaseController();
    private final Listing listing;

    private JLabel message;
    private JButton addToCartButton;


    public ListingDetailPage(Listing listing) throws IOException {
        super(listing.getTitle());
        this.listing = listing;
        this.setLayout(null);

        boolean canAddToCart = canAddToCart();
        this.setPreferredSize(new Dimension(1280, 720));

        User listingSeller = getListingSeller();
        if (listingSeller == null) {
            message.setLocation(500, 400);
            message.setVisible(true);
            this.add(message);
            this.setVisible(true);
            return;
        }


        JLabel listingTitle = new JLabel(listing.getTitle());
        listingTitle.setSize(500, 60);
        listingTitle.setLocation(500, 120);
        listingTitle.setFont(new Font("Arial", Font.PLAIN, 50));
        this.add(listingTitle);

        try {
            String filepath = listing.getImagePath();
            BufferedImage rawImage = ImageIO.read(new File(filepath));
            Image scaled_image = rawImage.getScaledInstance(300, 220, Image.SCALE_SMOOTH);
            JLabel img = new JLabel(new ImageIcon(scaled_image));

            img.setSize(300, 220);
            img.setLocation(350, 190);
            this.add(img);
        } catch (IOException e) {
            JLabel error = new JLabel("Unable to get listing image");
            error.setLocation(350, 260);
            error.setSize(200, 60);
            error.setFont(new Font("Arial", Font.PLAIN, 16));
            System.out.println(e.getMessage());
        }

        JTextArea listingDescription = new JTextArea(listing.getDescription());
        listingDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        listingDescription.setSize(300, 220);
        listingDescription.setEditable(false);
        listingDescription.setBackground(this.getBackground());
        listingDescription.setLineWrap(true);
        listingDescription.setWrapStyleWord(true);
        listingDescription.setLocation(660, 190);
        this.add(listingDescription);

        JLabel listingPrice = new JLabel(String.format("Price: %.2f", listing.getPrice()));
        listingPrice.setFont(new Font("Arial", Font.PLAIN, 20));
        listingPrice.setLocation(500, 420);
        listingPrice.setSize(500, 25);
        this.add(listingPrice);

        JLabel listingSellerAndDate = new JLabel(String.format("Listed by %s on %s", listingSeller.getUsername(),
                String.valueOf(listing.getDate())));
        listingSellerAndDate.setFont(new Font("Arial", Font.PLAIN, 20));
        listingSellerAndDate.setLocation(500, 460);
        listingSellerAndDate.setSize(500, 25);
        this.add(listingSellerAndDate);

        JLabel listingSellerRating = new JLabel(
                String.format("%s has a %1.1f star rating", listingSeller.getUsername(), listingSeller.calculateRating()));
        listingSellerRating.setFont(new Font("Arial", Font.PLAIN, 20));
        listingSellerRating.setLocation(500, 500);
        listingSellerRating.setSize(500, 25);
        this.add(listingSellerRating);

        if (canAddToCart) {
            addToCartButton = new JButton("Add to cart");
            addToCartButton.setFont(new Font("Arial", Font.PLAIN, 20));
            addToCartButton.setLocation(500, 540);
            addToCartButton.setSize(150, 50);
            addToCartButton.addActionListener(this);
            this.add(addToCartButton);

            message.setFont(new Font("Arial", Font.PLAIN, 20));
            message.setLocation(500, 580);
            message.setSize(500, 50);
            this.add(message);
        } else {
            message.setFont(new Font("Arial", Font.PLAIN, 20));
            message.setLocation(500, 540);
            message.setSize(500, 50);
            this.add(message);
        }


        try {
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
            message.setLocation(500, 540);
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

    private boolean canAddToCart() throws IOException {

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

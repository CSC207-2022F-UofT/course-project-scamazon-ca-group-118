package UI;
import forms.CreateListingForm;
import useCase.createListing.ListingInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;
public class CreateListingPage extends Page{

        public CreateListingPage(String title) {
            super(title);

            JPanel createListingPanel = new JPanel();
            createListingPanel.setLayout(null);

            JLabel add_your_listing = new JLabel("Add your listing!");
            add_your_listing.setFont(new Font("Arial", Font.PLAIN, 30));
            add_your_listing.setSize(300, 30);
            add_your_listing.setLocation(550, 150);
            createListingPanel.add(add_your_listing);

            JLabel listing_title_label = new JLabel("Listing Title:");
            listing_title_label.setSize(250, 30);
            listing_title_label.setLocation(350, 200);
            createListingPanel.add(listing_title_label);

            JTextField listing_text = new JTextField();
            listing_text.setSize(250, 30);
            listing_text.setLocation(550, 200);
            createListingPanel.add(listing_text);


            JLabel price_label = new JLabel("Listing Price:");
            price_label.setSize(250, 30);
            price_label.setLocation(350, 250);
            createListingPanel.add(price_label);

            JTextField price_text = new JTextField();
            price_text.setSize(250, 30);
            price_text.setLocation(550, 250);
            createListingPanel.add(price_text);

            JLabel desc_label = new JLabel("Listing Description:");
            desc_label.setSize(250, 30);
            desc_label.setLocation(350, 300);
            createListingPanel.add(desc_label);

            JTextField desc_text = new JTextField();
            desc_text.setSize(250, 90);
            desc_text.setLocation(550, 300);
            createListingPanel.add(desc_text);

            JLabel img_label = new JLabel("Images: ");
            img_label.setSize(250, 30);
            img_label.setLocation(350, 425);
            createListingPanel.add(img_label);

            JLabel img = new JLabel("No image selected");
            img.setSize(250, 30);
            img.setLocation(550, 425);
            createListingPanel.add(img);

            JButton upload = new JButton("Upload Images");
            upload.setSize(150, 30);
            upload.setLocation(850, 425);

            upload.addActionListener(e -> {
                String fileName;
                String filePath;
                final JFileChooser imgFiles = new JFileChooser();
                int returnVal = imgFiles.showOpenDialog(CreateListingPage.this);
                if (returnVal == imgFiles.APPROVE_OPTION){
                    fileName = imgFiles.getSelectedFile().getName();
                    filePath = imgFiles.getCurrentDirectory().toString();
                    System.out.println("File Name: " + fileName);
                    System.out.println("File Path: " + filePath);
                    img.setText(filePath + "/" + fileName);
                }
                if(returnVal == JFileChooser.CANCEL_OPTION){
                    fileName = "";
                    filePath = "";
                }


            });
            createListingPanel.add(upload);

            JButton submit = new JButton("Submit Listing");
            submit.setSize(250, 30);
            submit.setLocation(550, 550);
            submit.addActionListener(e -> {
                String listingTitle = listing_text.getText();
                DecimalFormat df = new DecimalFormat("0.00");
                float listingPrice = Float.parseFloat(df.format(Float.parseFloat(price_text.getText())));
                String listingDesc = desc_text.getText();

                //use Class to submit information

                //String sellerUsername = "temp"; //temp variable while we figure out current user
                //CreateListingForm clf = new CreateListingForm(listingTitle, listingPrice, sellerUsername, listingDesc, images);
                //String message = clf.getMessage();
                //System.out.println(message);
               // submit.setText(message);

            });
            createListingPanel.add(submit);


            createListingPanel.setMinimumSize(new Dimension(1280, 570));
            createListingPanel.setMaximumSize(new Dimension(1280, 570));


            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Create a listing");
            this.setMinimumSize(new Dimension(1280, 720));
            this.add(createListingPanel);
//            this.pack();
//            this.setResizable(false);
            this.setVisible(true);


        }
}

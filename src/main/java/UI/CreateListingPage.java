package UI;
import javax.swing.*;
import java.awt.*;

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

            JList imgs = new JList(); // put img array as argument
            imgs.setSize(250, 60);
            imgs.setLocation(550, 425);
            createListingPanel.add(imgs);

            JButton upload = new JButton("Upload Images");
            upload.setSize(150, 30);
            upload.setLocation(850, 425);
            createListingPanel.add(upload);

            JButton submit = new JButton("Submit Listing");
            submit.setSize(250, 30);
            submit.setLocation(550, 550);
            createListingPanel.add(submit);


            createListingPanel.setMinimumSize(new Dimension(1280, 570));
            createListingPanel.setMaximumSize(new Dimension(1280, 570));


            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Create a listing");
            this.setMinimumSize(new Dimension(1280, 720));
            this.add(createListingPanel);
            this.pack();
            this.setVisible(true);


        }
}

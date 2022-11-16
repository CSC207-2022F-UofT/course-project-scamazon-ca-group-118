package UI;
import javax.swing.*;
import java.awt.*;

public class CreateListingPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(6, 1));
        JPanel title = new JPanel();
        title.setLayout(new GridLayout(0, 2));
        JButton back = new JButton("Back");
        JLabel add_your_listing = new JLabel("Add your listing!");
        title.add(back);
        title.add(add_your_listing);

        JPanel listing_title = new JPanel();
        listing_title.setLayout(new GridLayout(0, 2));
        JLabel listing_title_label = new JLabel("Listing Title:");
        JTextField listing_text = new JTextField();
        listing_title.add(listing_title_label);
        listing_title.add(listing_text);

        JPanel listing_price = new JPanel();
        listing_price.setLayout(new GridLayout(0, 2));
        JLabel price_label = new JLabel("Listing Price:");
        JTextField price_text = new JTextField();
        listing_price.add(price_label);
        listing_price.add(price_text);

        JPanel listing_desc = new JPanel();
        listing_desc.setLayout(new GridLayout(0, 2));
        JLabel desc_label = new JLabel("Listing Description:");
        JTextField desc_text = new JTextField();
        listing_desc.add(desc_label);
        listing_desc.add(desc_text);


        frame.add(title);
        frame.add(listing_title);
        frame.add(listing_price);
        frame.add(listing_desc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Create a listing");
        frame.setSize(new Dimension(1280, 720));
        frame.pack();
        frame.setVisible(true);
    }

}

package UI;
import entities.Cart;
import entities.Listing;
import database.DatabaseController;
import entities.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Graphics2D;

import static javax.swing.SwingUtilities.paintComponent;

public class ListingListPage extends JFrame implements ActionListener {
    private String example_images;
    private User example_user = new User(0, "sam", "pass", "gmail",
            new ArrayList<>(), new ArrayList<>(), new Cart());
    // Components of the Form
    private Container c;
    private JLabel title;
    private int length = 1280;
    private int height = 570;
    private static int x_length = 100;
    private static int y_height = 75;

    private int box_length = 1080;
    private int box_height = 100;
    public ListingListPage() {
        setTitle("Listings");
        setBounds(length / 2, 90, length, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Listings");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(500, 30);
        c.add(title);


        Listing listing = new Listing(example_user, "basketball", 40.0f,
                "basketball for indoor use", example_images);

        // Listing searchResults = new ArrayList<Listing>();
        List <Listing> searchResults = new ArrayList<Listing>();

        searchResults.add(listing);



        for (Listing result : searchResults) {
            JLabel resultTitle = new JLabel(result.getTitle());
            JLabel resultPrice = new JLabel("Price: " + String.valueOf(result.getPrice()));
            JLabel resultImage = new JLabel(result.getImagePath());
            JLabel resultDescription = new JLabel(result.getDescription());
            JLabel resultDate = new JLabel(result.getDate().toString());

            resultTitle.setFont(new Font("Arial", Font.PLAIN, 10));
            resultPrice.setFont(new Font("Arial", Font.PLAIN, 10));
            resultDescription.setFont(new Font("Arial", Font.PLAIN, 10));
            resultDate.setFont(new Font("Arial", Font.PLAIN, 10));

            resultTitle.setLocation(x_length + 200, y_height);
            resultPrice.setLocation(x_length + 1000, y_height);
            resultImage.setLocation(x_length, y_height);
            resultDate.setLocation(x_length + 200, y_height + 25);
            resultDescription.setLocation(x_length + 200, y_height + 50);


            resultTitle.setSize(300, 30);
            resultPrice.setSize(100, 30);
            resultImage.setSize(100, 100);
            resultDescription.setSize(300, 100);
            resultDate.setSize(200, 30);

            y_height += 200;



            c.add(resultTitle);
            c.add(resultDate);
            c.add(resultDescription);
            c.add(resultImage);
            c.add(resultPrice);
        }


        setVisible(true);
    }
//    public void paint(Graphics g) {
//        for (int i = 0; i < 10; i++) {
//            g.drawRect(x_length, y_height + i * 100 - 200, box_length, box_height);
//        }
//    }


    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == logo) {
//              reroute to listings page
//        } else if (e.getSource() == cart) {
//              reroute to cart
//        }
//        else if (e.getSource() == checkout) {}
        // reroute to checkout
//        else if (e.getSource() == profile) {}
        // reroute to profile
//        else if (e.getSource() == listing) {}
        // reroute to listing detail

    }
    public List<Listing> showListings() throws IOException {
        DatabaseController controller = new DatabaseController();
        List <Listing> listings = controller.getAllListings();
        return listings;
    }


    public static void main(String[] args) {
        ListingListPage listingPage = new ListingListPage();
    }
}

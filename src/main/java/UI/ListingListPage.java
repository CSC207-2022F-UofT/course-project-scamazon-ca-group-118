package UI;
import entities.Listing;
import database.DatabaseController;

import java.awt.*;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.*;

public class ListingListPage extends Page {


    public ListingListPage(String title) {
        super(title);
        JFrame container = new JFrame();
        container.setSize(400, 300);
        showListings(container);
    }
    public void showListings(JFrame container) {
        DatabaseController controller = new DatabaseController();
//        List <Listing> listings = controller.getListingDefault();
//        for (Listing listing : listings) {
//            TextArea display = new TextArea(listing.getTitle());
//            container.add(display);
      //  }
    }

    public static void main(String[] args) {
        new ListingListPage("Listings");


    }

}

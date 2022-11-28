package UI;
import Main.Main;
import forms.CreateListingForm;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateListingPage extends Page{

        private final JLabel add_your_listing = new JLabel("Add your listing!");
        private final JLabel listing_title_label = new JLabel("Listing Title:");
        private final JTextField listing_text = new JTextField();
        private final JLabel price_label = new JLabel("Listing Price:");
        private final JTextField price_text = new JTextField();
        private final JLabel desc_label = new JLabel("Listing Description:");
        private final JTextField desc_text = new JTextField();
        private final JLabel img_label = new JLabel("Images: ");
        private final JLabel img = new JLabel("No image selected");
        private final JButton upload = new JButton("Upload Images");
        JLabel picLabel = new JLabel();
        private final JButton submit = new JButton("Submit Listing");

        private BufferedImage myPicture = null;
        private String fileName;
        private String filePath;
        private String responseModel;
        public CreateListingPage() {
            super("Create Listing Page");

            this.setLayout(null);

            setUpPanel();
        }
        public void setUpPanel(){

//            NavBar nb = new NavBar();
//            nb.setLocation(0, 0);
//            nb.setSize(1280, 100);
//            this.add(nb);
            add_your_listing.setFont(new Font("Arial", Font.PLAIN, 30));
            add_your_listing.setSize(300, 30);
            add_your_listing.setLocation(550, 150);
            this.add(add_your_listing);


            listing_title_label.setSize(250, 30);
            listing_title_label.setLocation(350, 200);
            this.add(listing_title_label);


            listing_text.setSize(250, 30);
            listing_text.setLocation(550, 200);
            this.add(listing_text);



            price_label.setSize(250, 30);
            price_label.setLocation(350, 250);
            this.add(price_label);


            price_text.setSize(250, 30);
            price_text.setLocation(550, 250);
            this.add(price_text);


            desc_label.setSize(250, 30);
            desc_label.setLocation(350, 300);
            this.add(desc_label);


            desc_text.setSize(250, 90);
            desc_text.setLocation(550, 300);
            this.add(desc_text);


            img_label.setSize(250, 30);
            img_label.setLocation(350, 425);
            this.add(img_label);


            img.setSize(250, 40);
            img.setLocation(550, 425);
            this.add(img);

            picLabel.setSize(300, 150);
            picLabel.setLocation(550, 475);
            this.add(picLabel);

            upload.setSize(150, 30);
            upload.setLocation(850, 425);

            upload.addActionListener(e -> {

                final JFileChooser imgFiles = new JFileChooser();
                int returnVal = imgFiles.showOpenDialog(CreateListingPage.this);
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    fileName = imgFiles.getSelectedFile().getName();
                    filePath = imgFiles.getCurrentDirectory().toString();
                    img.setText("<html>" + filePath + "/" + fileName + "</html>");


                    try {
                        myPicture = ImageIO.read(new File(filePath + "/" + fileName));
                        BufferedImage scaled_pic = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
                        Graphics g = scaled_pic.createGraphics();
                        g.drawImage(myPicture, 0, 0, 300, 150, null);
                        g.dispose();
                        picLabel.setIcon(new ImageIcon(scaled_pic));

                        // TODO Work on resizing
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }



                }
                if(returnVal == JFileChooser.CANCEL_OPTION){
                    fileName = "";
                    filePath = "";
                }


            });
            this.add(upload);


            submit.setSize(250, 30);
            submit.setLocation(550, 650);

            submit.addActionListener(e -> {
                String listingTitle = listing_text.getText();

                String listingPrice = price_text.getText();


                String listingDesc = desc_text.getText();
                CreateListingForm form = new CreateListingForm(listingTitle, listingPrice, Main.getCurrentUser(), listingDesc, filePath + "/" + fileName);

                try {
                    responseModel = form.getMessage();
                } catch (IOException ex) {
                    responseModel = "Response model error";
                    throw new RuntimeException(ex);
                }

                submit.setText(responseModel);

                //use Class to submit information

                //String sellerUsername = "temp"; //temp variable while we figure out current user
                //CreateListingForm clf = new CreateListingForm(listingTitle, listingPrice, sellerUsername, listingDesc, images);
                //String message = clf.getMessage();
                //System.out.println(message);
                // submit.setText(message);

            });
            this.add(submit);
            this.setPreferredSize(new Dimension(1280, 720)); //570
            this.setMaximumSize(new Dimension(1280, 720));
//            this.setMinimumSize(new Dimension(1280, 570));
//


//            this.setTitle("Create a listing");
//            this.setMinimumSize(new Dimension(1280, 720));
//            this.add(this);
//            this.setVisible(true);

        }




}

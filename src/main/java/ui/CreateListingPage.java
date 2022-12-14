package ui;
import main.Main;
import forms.CreateListingForm;
import use_case.create_listing.CreateListingPresenter;


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

        private final JLabel RESPONSE_TEXT = new JLabel();
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


            RESPONSE_TEXT.setSize(250, 100);
            RESPONSE_TEXT.setLocation(900, 250);
            RESPONSE_TEXT.setFont(new Font("Arial", Font.PLAIN, 20));
            this.add(RESPONSE_TEXT);

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
                        Image scaled_img = myPicture.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                        picLabel.setIcon(new ImageIcon(scaled_img));

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

                
                CreateListingPresenter pres;

                CreateListingForm form;
                if((filePath == ""  && fileName == "")||(filePath == null && fileName == null)){
                    form = new CreateListingForm(listingTitle, listingPrice, Main.getCurrentUser(), listingDesc, "images/noimage.jpg");

                }
                else{
                    form = new CreateListingForm(listingTitle, listingPrice, Main.getCurrentUser(), listingDesc, filePath + "/" + fileName);
                }

                try {
                    pres = new CreateListingPresenter(form);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                responseModel = pres.getMessage();

                RESPONSE_TEXT.setText("<html>"+ responseModel +"</html>");
                if(responseModel.equals("Listing Created!")){
                    listing_text.setText("");
                    price_text.setText("");
                    desc_text.setText("");
                    fileName = "";
                    filePath = "";
                    img.setText("No image selected");
                    picLabel.setIcon(null);

                }

            });
            this.add(submit);
            this.setPreferredSize(new Dimension(1280, 720));
            this.setMaximumSize(new Dimension(1280, 720));


        }




}

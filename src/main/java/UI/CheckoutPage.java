package UI;

import Main.Main;
import com.opencsv.exceptions.CsvException;
import forms.CheckoutForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class CheckoutPage extends Page implements ActionListener {
    private final JButton SUBMIT = new JButton("Submit");
    private final JTextField JT_NAME = new JTextField(16);
    private final JTextField JT_CARD_NUMBER = new JTextField(16);
    private final JTextField JT_CVV = new JTextField(16);
    private final JTextField JT_EXPIRATION = new JTextField(16);
    private final JTextField JV_ADDRESS = new JTextField(15);
    private final SpringLayout LAYOUT;
    private LabelTextPanel nameInfo;
    private LabelTextPanel cardNumberInfo;
    private LabelTextPanel cvvInfo;
    private LabelTextPanel expirationInfo;
    private LabelTextPanel addressInfo;
    private JLabel titleLabel;

    public CheckoutPage() {
        super("Scamazon.ca");
        this.LAYOUT = new SpringLayout();

        setUpPanel();
        setUpLayout();
    }

    private void setUpPanel() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(LAYOUT);

        //listener for SUBMIT button
        SUBMIT.addActionListener(this);

        //JLabels
        JLabel nameLabel = new JLabel("Name: ");
        JLabel cardNumberLabel = new JLabel("Card Number: ");
        JLabel cvvLabel = new JLabel("CVV: ");
        JLabel expirationLabel = new JLabel("Card Expiration: ");
        JLabel addressLabel = new JLabel("Address: ");
        titleLabel = new JLabel("Checkout");

        //LabelTextPanels
        nameInfo = new LabelTextPanel(nameLabel, JT_NAME);
        cardNumberInfo = new LabelTextPanel(cardNumberLabel, JT_CARD_NUMBER);
        cvvInfo = new LabelTextPanel(cvvLabel, JT_CVV);
        expirationInfo = new LabelTextPanel(expirationLabel, JT_EXPIRATION);
        addressInfo = new LabelTextPanel(addressLabel, JV_ADDRESS);

        //add all elements to the panel
        this.add(titleLabel);
        this.add(nameInfo);
        this.add(cardNumberInfo);
        this.add(cvvInfo);
        this.add(expirationInfo);
        this.add(addressInfo);
        this.add(SUBMIT);
    }

    private void setUpLayout() {
        //align everything near the middle
        LAYOUT.putConstraint(SpringLayout.WEST, titleLabel, 500, SpringLayout.WEST, this);
        LAYOUT.putConstraint(SpringLayout.WEST, nameInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.WEST, cardNumberInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.WEST, cvvInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.WEST, expirationInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.WEST, addressInfo, 0, SpringLayout.WEST, titleLabel);
        LAYOUT.putConstraint(SpringLayout.EAST, SUBMIT, 0, SpringLayout.EAST, addressInfo);

        //align everything vertically
        LAYOUT.putConstraint(SpringLayout.NORTH, titleLabel, 150, SpringLayout.NORTH, this);
        LAYOUT.putConstraint(SpringLayout.NORTH, nameInfo, 30, SpringLayout.SOUTH, titleLabel);
        LAYOUT.putConstraint(SpringLayout.NORTH, cardNumberInfo, 30, SpringLayout.SOUTH, nameInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, cvvInfo, 30, SpringLayout.SOUTH, cardNumberInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, expirationInfo, 30, SpringLayout.SOUTH, cvvInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, addressInfo, 30, SpringLayout.SOUTH, expirationInfo);
        LAYOUT.putConstraint(SpringLayout.NORTH, SUBMIT, 30, SpringLayout.SOUTH, addressInfo);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SUBMIT) {
            try {
                String expDate = JT_EXPIRATION.getText();

                final Pattern expPattern = Pattern.compile("(0[1-9]|1[0-2])\\/[0-9]{2}");
                Matcher m = expPattern.matcher(expDate);
                if(!m.matches()){
                    JOptionPane.showMessageDialog(this, "Please enter a valid date MM/YY");
                }
                else{
                    String[] exp = expDate.split("/", 2);
                    expDate = "20" + exp[1] + "-" + exp[0] + "-" + "01";
                }

                CheckoutForm form = new CheckoutForm(Main.getCurrentUser(), JT_NAME.getText(), JT_CARD_NUMBER.getText(), JT_CVV.getText(),
                        LocalDate.parse(expDate), JV_ADDRESS.getText());
                form.getResponseModel();
                String message = form.getMessage();
                if(!message.equals("")) {
                    JOptionPane.showMessageDialog(new JFrame(), message);
                }
            }

            catch (IOException | CsvException ex) {

                throw new RuntimeException(ex);
            }
        }
    }
}

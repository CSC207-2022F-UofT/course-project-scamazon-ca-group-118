package UI;

import Main.Main;
import forms.CheckoutForm;
import useCase.checkout.CheckoutResponseModel;
import useCase.login.LoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class CheckoutPage extends Page implements ActionListener {
    private final JButton SUBMIT = new JButton("Submit");
    private JTextField jtName = new JTextField(16);
    private JTextField jtCardNumber = new JTextField(16);
    private JTextField jtCVV = new JTextField(16);
    private JTextField jtExpiration = new JTextField(16);
    private JTextField jtAddress = new JTextField(15);
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
        nameInfo = new LabelTextPanel(nameLabel, jtName);
        cardNumberInfo = new LabelTextPanel(cardNumberLabel, jtCardNumber);
        cvvInfo = new LabelTextPanel(cvvLabel, jtCVV);
        expirationInfo = new LabelTextPanel(expirationLabel, jtExpiration);
        addressInfo = new LabelTextPanel(addressLabel, jtAddress);

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
                CheckoutForm form = new CheckoutForm(jtName.getText(), jtCardNumber.getText(), jtCVV.getText(),
                        LocalDate.parse(jtExpiration.getText()), jtAddress.getText());
                form.getResponseModel();
            }
            catch (DateTimeParseException error) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid date yyyy-MM-dd");
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

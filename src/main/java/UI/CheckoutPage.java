package UI;

import forms.CheckoutForm;
import useCase.checkout.CheckoutResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CheckoutPage extends Page implements ActionListener {
    final int WIDTH = 800;
    final int HEIGHT = 600;
    private final JButton SUBMIT;
    JTextField jtUsername = new JTextField();
    JTextField jtName = new JTextField();
    JTextField jtCardNumber = new JTextField(16);
    JTextField jtCVV = new JTextField(3);
    JTextField jtExpiration = new JTextField();
    JTextField jtAddress = new JTextField();

    public CheckoutPage() {
        super("Checkout");

        JPanel form = new JPanel();
        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setTitle("Scamazon.ca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(form);

        form.setLayout(new BorderLayout(10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel username = new JLabel("Username");
        username.setBounds(10, 20, 80, 25);
        form.add(username);
        jtUsername.setBounds(100, 20, 165, 25);
        form.add(jtUsername);

        JLabel name = new JLabel("Name");
        name.setBounds(10, 20, 80, 25);
        form.add(name);
        jtName.setBounds(100, 20, 165, 25);
        form.add(jtName);

        JLabel cardNumber = new JLabel("Card Number");
        cardNumber.setBounds(10, 20, 80, 25);
        form.add(cardNumber);
        jtCardNumber.setBounds(100, 20, 165, 25);
        form.add(jtCardNumber);

        JLabel cvv = new JLabel("CVV");
        cvv.setBounds(10, 20, 80, 25);
        form.add(cvv);
        jtCVV.setBounds(100, 20, 165, 25);
        form.add(jtCVV);

        JLabel expiration = new JLabel("Expiration Date");
        expiration.setBounds(10, 20, 80, 25);
        form.add(expiration);
        jtExpiration.setBounds(100, 20, 165, 25);
        form.add(jtExpiration);

        JLabel address = new JLabel("Address");
        address.setBounds(10, 20, 80, 25);
        form.add(address);
        jtAddress.setBounds(100, 20, 165, 25);
        form.add(jtAddress);

        SUBMIT = new JButton("Submit");
        SUBMIT.addActionListener(this);
        SUBMIT.setBounds(10, 80, 80, 25);
        form.add(SUBMIT);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.SUBMIT) {
            CheckoutForm form = new CheckoutForm(jtUsername.getText(), jtName.getText(),
                    jtCardNumber.getText(), jtCVV.getText(), LocalDate.parse(jtExpiration.getText()),
                    jtAddress.getText() );
            CheckoutResponseModel responseModel = form.getResponseModel();
        }
    }
}

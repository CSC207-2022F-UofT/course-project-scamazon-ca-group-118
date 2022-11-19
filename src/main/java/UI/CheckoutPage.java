package UI;

import forms.CheckoutForm;

import javax.swing.*;
import java.awt.*;

public class CheckoutPage extends Page {
    private final JFrame FRAME;

    public CheckoutPage() {
        super("Checkout");
        CheckoutForm form = new CheckoutForm();
        this.FRAME = new JFrame();
        this.FRAME.add(form);
        this.FRAME.setSize(900, 600);
        this.FRAME.setMinimumSize(new Dimension(450, 300));
        this.FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.FRAME.setVisible(true);
    }
}

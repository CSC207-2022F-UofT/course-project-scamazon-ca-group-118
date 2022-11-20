package pages;

import entities.User;
import features.Cart;
import features.Listing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CartPage extends Page implements ActionListener {

    final int WIDTH = 1280;
    final int HEIGHT = 720;
    private final JButton REMOVE;
    private final JButton CHECKOUT;
    private final JTable itemTable;
    private JLabel priceTotal;
    public Cart itemCart;


    public CartPage() {
        super(User.getCurrentUser().getUsername() + "'s Cart");
        this.setLayout(new GridLayout(1, 2));
        this.itemCart = User.getCurrentUser().getCart();

        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setTitle("Scamazon.ca");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.itemTable = this.createItemTable();
        JScrollPane scrollPane = new JScrollPane(this.itemTable);

        //Create appropriate buttons and label
        this.priceTotal = new JLabel("Total Price: $" + itemCart.getPrice());
        this.REMOVE = new JButton("Remove Item");
        this.REMOVE.addActionListener(this);
        this.CHECKOUT = new JButton("Checkout");
        this.CHECKOUT.addActionListener(this);

        //Assign appropriate buttons to new button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 20));
        buttonPanel.add(this.REMOVE);
        buttonPanel.add(this.CHECKOUT);
        buttonPanel.add(this.priceTotal);

        //Assigning all panes and panels to frame
        this.add(scrollPane);
        this.add(buttonPanel);
    }

    public JTable createItemTable() {
        ArrayList<Listing> items = this.itemCart.getItems();
        String[][] data = new String[itemCart.countItems()][];
        String[] list = new String[2];
        for (int i = 0; i < this.itemCart.countItems(); i++) {
            list = new String[]{items.get(i).getTitle(), String.valueOf(items.get(i).getPrice())};
            data[i] = list;
        }
        String[] columnNames = {"Item", "Price"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable itemTable = new JTable(model);
        itemTable.setFont(new Font("Serif", Font.PLAIN, 20));
        return itemTable;
    }

    public Page goToCheckout() {
        return new CheckoutPage("Checkout");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.REMOVE) {
            DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
            int row = this.itemTable.getSelectedRow();
            itemCart.removeItem(row);
            model.removeRow(row);
            priceTotal.setText("Total Price: $" + itemCart.getPrice());
        } else {
            this.goToCheckout();
        }
    }
}

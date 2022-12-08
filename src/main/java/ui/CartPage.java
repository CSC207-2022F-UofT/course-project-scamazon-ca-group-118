package ui;

import main.Main;
import database.DatabaseController;
import entities.Cart;
import entities.Listing;
import entities.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class CartPage extends Page implements ActionListener {

    final int WIDTH = 1280;
    final int HEIGHT = 720;
    private final JButton REMOVE;
    private final JButton CHECKOUT;
    private final JTable itemTable;
    private JLabel priceTotal;
    public Cart itemCart;

    /**
     * Cart Page constructor that creates the CartPage Panel.
     */
    public CartPage() throws IOException {
        super(Main.getCurrentUser().getUsername() + "'s Cart");
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        DatabaseController db = new DatabaseController();
        this.itemCart = db.getUserWithUsername(Main.getCurrentUser().getUsername()).getCart();

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.itemTable = this.createItemTable();
        JScrollPane scrollPane = new JScrollPane(this.itemTable);
        scrollPane.setPreferredSize(new Dimension(500, 500));

        //Create appropriate buttons and label
        this.priceTotal = new JLabel("Total Price: $" + itemCart.getPrice());
        this.REMOVE = new JButton("Remove Item");
        this.REMOVE.addActionListener(this);
        this.CHECKOUT = new JButton("Checkout");
        this.CHECKOUT.addActionListener(this);

        //Assign appropriate buttons to new button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.add(this.REMOVE);
        buttonPanel.add(this.CHECKOUT);
        buttonPanel.add(this.priceTotal);
        buttonPanel.setPreferredSize(new Dimension(500, 500));

        //Assigning all panes and panels to CartPage Panel
        layout.putConstraint(SpringLayout.EAST, scrollPane, -50, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.WEST, buttonPanel, 120, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, scrollPane, 170, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, buttonPanel, 170, SpringLayout.NORTH, this);

        this.add(buttonPanel);
        this.add(scrollPane);
    }

    /**
     * Creates a JTable of all the items in the Users cart.
     *
     * @return JTable of the items in cart.
     */
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


    /**
     * Creates a new instance of CheckoutPage and sets it as the current page.
     */
    public void goToCheckout() {
        Main.setCurrentPage(new CheckoutPage());
    }

    /**
     * Preforms the removal of a cart item or moves the User to the checkout page based on input e.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.REMOVE) {
            DatabaseController db = new DatabaseController();
            DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
            int row = this.itemTable.getSelectedRow();
            Listing listing = itemCart.getItems().get(row);
            try {
                db.removeFromCartByID(listing.getId());
                itemCart = db.getUserWithUsername(Main.getCurrentUser().getUsername()).getCart();
                model.removeRow(row);
                priceTotal.setText("Total Price: $" + itemCart.getPrice());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            this.goToCheckout();
        }
    }

}

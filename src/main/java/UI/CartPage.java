package UI;

import Main.Main;
import entities.Cart;
import entities.Listing;
import entities.User;

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

    /**
     * Cart Page constructor that creates the CartPage Panel.
     */
    public CartPage() {
        super(Main.getCurrentUser().getUsername() + "'s Cart");
        this.setLayout(new GridLayout(1, 2, 150, 320));
        this.itemCart = Main.getCurrentUser().getCart();

        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setTitle("Scamazon.ca");

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

        //Assigning all panes and panels to CartPage Panel
        this.add(scrollPane);
        this.add(buttonPanel);
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

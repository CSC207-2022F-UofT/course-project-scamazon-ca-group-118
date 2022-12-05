package entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class CartUnitTests {
    @Test
    void testAddItem() {

        //cart1.countItems() is simple and known to work, making it good for testing that the items are added.
        //the amount of listings should have no effect on the performance of the method, so additional testing is not
        //required for other lengths.

        Cart cart1 = new Cart();
        Listing listing1 = new Listing(1,
                "test",
                "item1",
                LocalDate.now(),
                (float) 1.99,
                "A test item",
                "testPath");
        Listing listing2 = new Listing(2,
                "test",
                "item2",
                LocalDate.now(),
                (float) 2.99,
                "A test item",
                "testPath");
        Listing listing3 = new Listing(3,
                "test",
                "item3",
                LocalDate.now(),
                (float) 3.99,
                "A test item",
                "testPath");
        cart1.addItem(listing1);
        cart1.addItem(listing2);
        cart1.addItem(listing3);
        cart1.addItem(listing3);
        assert cart1.countItems() == 3;
    }

    @Test
    void testSetItems() {

        //cart1.countItems() is simple and known to work, making it good for testing that the items are added.
        //the amount of listings should have no effect on the performance of the method, so additional testing is not
        //required for other lengths.

        Cart cart1 = new Cart();
        Listing listing1 = new Listing(1,
                "test",
                "item1",
                LocalDate.now(),
                (float) 1.99,
                "A test item",
                "testPath");
        Listing listing2 = new Listing(2,
                "test",
                "item2",
                LocalDate.now(),
                (float) 2.99,
                "A test item",
                "testPath");
        Listing listing3 = new Listing(3,
                "test",
                "item3",
                LocalDate.now(),
                (float) 3.99,
                "A test item",
                "testPath");
        ArrayList<Listing> list = new ArrayList<Listing>();
        list.add(listing1);
        list.add(listing2);
        list.add(listing3);
        cart1.setItems(list);
        assert cart1.countItems() == 3;
    }

    @Test
    void testGetItems() {

        //the amount of listings should have no effect on the performance of the method, so additional testing is not
        //required for other lengths.

        Cart cart1 = new Cart();
        Listing listing1 = new Listing(1,
                "test",
                "item1",
                LocalDate.now(),
                (float) 1.99,
                "A test item",
                "testPath");
        Listing listing2 = new Listing(2,
                "test",
                "item2",
                LocalDate.now(),
                (float) 2.99,
                "A test item",
                "testPath");
        Listing listing3 = new Listing(3,
                "test",
                "item3",
                LocalDate.now(),
                (float) 3.99,
                "A test item",
                "testPath");
        ArrayList<Listing> list = new ArrayList<Listing>();
        list.add(listing1);
        list.add(listing2);
        list.add(listing3);
        cart1.setItems(list);
        ArrayList<Listing> items = cart1.getItems();
        Listing item1 = items.get(0);
        Listing item2 = items.get(1);
        Listing item3 = items.get(2);
        assert ((items != list) && (list.contains(item1) && list.contains(item2) && list.contains(item3)));
    }

    @Test
    void testRemoveItemAtIndex() {

        //cart1.countItems() is simple and known to work, making it good for testing that the items are added.
        //the amount of listings should have no effect on the performance of the method, so additional testing is not
        //required for other lengths.

        Cart cart1 = new Cart();
        Listing listing1 = new Listing(1,
                "test",
                "item1",
                LocalDate.now(),
                (float) 1.99,
                "A test item",
                "testPath");
        Listing listing2 = new Listing(2,
                "test",
                "item2",
                LocalDate.now(),
                (float) 2.99,
                "A test item",
                "testPath");
        Listing listing3 = new Listing(3,
                "test",
                "item3",
                LocalDate.now(),
                (float) 3.99,
                "A test item",
                "testPath");
        ArrayList<Listing> list = new ArrayList<Listing>();
        list.add(listing1);
        list.add(listing2);
        list.add(listing3);
        cart1.setItems(list);
        cart1.removeItem(2);
        ArrayList<Listing> cartItems = cart1.getItems();
        assert (cart1.countItems() == 2) && (cartItems.contains(listing1) && cartItems.contains(listing2));
    }

    @Test
    void testRemoveItemByListing() {

        //the amount of listings should have no effect on the performance of the method, so additional testing is not
        //required for other lengths.

        Cart cart1 = new Cart();
        Listing listing1 = new Listing(1,
                "test",
                "item1",
                LocalDate.now(),
                (float) 1.99,
                "A test item",
                "testPath");
        Listing listing2 = new Listing(2,
                "test",
                "item2",
                LocalDate.now(),
                (float) 2.99,
                "A test item",
                "testPath");
        Listing listing3 = new Listing(3,
                "test",
                "item3",
                LocalDate.now(),
                (float) 3.99,
                "A test item",
                "testPath");
        ArrayList<Listing> list = new ArrayList<Listing>();
        list.add(listing1);
        list.add(listing2);
        list.add(listing3);
        cart1.setItems(list);
        cart1.removeItem(listing3);
        ArrayList<Listing> cartItems = cart1.getItems();
        assert (cart1.countItems() == 2) && (cartItems.contains(listing1) && cartItems.contains(listing2));
    }

    @Test
    void testGetPrice() {

        //the amount of listings should have no effect on the performance of the method, so additional testing is not
        //required for other lengths.

        Cart cart1 = new Cart();
        Listing listing1 = new Listing(1,
                "test",
                "item1",
                LocalDate.now(),
                (float) 1.99,
                "A test item",
                "testPath");
        Listing listing2 = new Listing(2,
                "test",
                "item2",
                LocalDate.now(),
                (float) 2.99,
                "A test item",
                "testPath");
        Listing listing3 = new Listing(3,
                "test",
                "item3",
                LocalDate.now(),
                (float) 3.99,
                "A test item",
                "testPath");
        ArrayList<Listing> list = new ArrayList<Listing>();
        list.add(listing1);
        list.add(listing2);
        list.add(listing3);
        cart1.setItems(list);
        assert (cart1.getPrice() == (listing1.getPrice() + listing2.getPrice() + listing3.getPrice()));
    }
}

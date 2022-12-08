# Group 118 - Scamazon

Welcome to Scamazon! This app is a marketplace where you can buy and sell a wide variety of items.

## Group Members
- Samuel Liu
- Luke Cheseldine
- Daniel Qiu
- Clare Gillis
- Mark Voronovych
- Helen Yu
- Ethan Stark
- Eric Guo

## Features

- Browse and search for items to buy
- Click into interesting items to learn more about them
- Add items of interest to cart
- Remove items from cart that are no longer wanted
- Sell your own items by creating a listing
- Check out with card information
- Leave ratings for other users

## How to Use

1. Clone the repository
2. Download the library https://sourceforge.net/projects/opencsv/ 
3. Locate the main.java in the main package and run that file
4. Create an account or log in to your existing account
5. Start browsing or search for specific items
6. Add items of interest to cart
7. If you want to sell an item, click the "Create Listing" button in the app and create a listing
8. When you're ready to check out, enter your card information

## Customer Support

If you have any issues with the app or need help with a purchase, please contact our customer support team at (707) 873-7862. We're here to help and we'll do our best to resolve any issues you may have.

Thank you for using Scamazon!

## Some Design patterns used
- Facade
  - Databased implemented using Facade design pattern (Database Controller)
- Factory
  - Listing Creator factory to create all listings
- Singleton
  - View. Ensured that the view is accessed once in the main (one instance). But can be accessed by the pages via main
- Adapter
  - The database controller allows CSV files to interact with our Java code. Allows us to source and store data in CSV files while manipulating it in Java.
- Memento
  - Create Listing Factory adds the listing to the database by calling the Database controller rather than writing to the CSV itself.
- Template
  - We used template in Page and Form. We have a parent class page and form. Each use case modifies its page and form for its specific case.
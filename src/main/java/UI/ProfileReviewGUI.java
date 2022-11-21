package UI;

import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProfileReviewGUI extends JFrame implements ActionListener {
    private SpringLayout layout;
    private JTextField usernameReviewd;
    private JButton rating1;
    private JButton rating2;
    private JButton rating3;
    private JButton rating4;
    private JButton rating5;
    private JButton submit;
    private JPanel usernameInfo;
    private JLabel ratingLabel;
    private JPanel ratingInfo;
    private int rating = 0;
    private JLabel errorMessage;

    public ProfileReviewGUI() {
        this.layout = new SpringLayout();
        setUpPanel();
        setUpLayout();
    }

    private void setUpPanel() {
        this.setPreferredSize(new Dimension(640, 240));
        this.setLayout(layout);
        //set size?
        JLabel usernameLabel = new JLabel("Username of the user you wish to review: ");
        usernameReviewd = new JTextField(15);
        usernameInfo = new JPanel();
        usernameInfo.add(usernameLabel);
        usernameInfo.add(usernameReviewd);


        ratingLabel = new JLabel("Give them a rating between 1 and 5");

        rating1 = new JButton("1");
        rating2 = new JButton("2");
        rating3 = new JButton("3");
        rating4 = new JButton("4");
        rating5 = new JButton("5");

        rating1.addActionListener(this);
        rating2.addActionListener(this);
        rating3.addActionListener(this);
        rating4.addActionListener(this);
        rating5.addActionListener(this);

        submit = new JButton("Submit review");
        submit.addActionListener(this);

        ratingInfo = new JPanel();
        ratingInfo.add(ratingLabel);
        ratingInfo.add(rating1);
        ratingInfo.add(rating2);
        ratingInfo.add(rating3);
        ratingInfo.add(rating4);
        ratingInfo.add(rating5);

        this.add(usernameInfo);
        this.add(ratingInfo);
        this.add(submit);
    }

    private void setUpLayout() {
        layout.putConstraint(SpringLayout.NORTH, usernameInfo, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, ratingInfo, 30, SpringLayout.SOUTH, usernameInfo);
        layout.putConstraint(SpringLayout.NORTH, submit, 30, SpringLayout.SOUTH, ratingInfo);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rating1) {
            rating = 1;
        } else if (e.getSource() == rating2) {
            rating = 2;
        } else if (e.getSource() == rating3) {
            rating = 3;
        } else if (e.getSource() == rating4) {
            rating = 4;
        } else if (e.getSource() == rating5) {
            rating = 5;
        } else if (e.getSource() == submit) {
            if (rating == 0) {

            }
        }
    }

    public static void main(String[] args) {
        new ProfileReviewGUI();
    }
}

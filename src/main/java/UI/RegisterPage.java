package UI;

import forms.RegisterForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends Page implements ActionListener{
    //Components of the form
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel mno;
    private JTextField email;
    private JLabel add;
    private JTextField tadd;
    private JLabel confirm;
    private JTextField confirmPass;
    private JButton sub;
    private JButton reset;
    private JLabel res;
    private JPanel form;
    //Register form will be embedded inside
    private RegisterForm forms;

    public RegisterPage(String title) {
        super(title);
        this.forms = new RegisterForm(title);
        setUpPage();
    }

    public RegisterPage(String title, RegisterForm form) {
        super(title);
        this.forms = form;
        //TODO implement this so that I can somehow get a JPanel with graphics from View
    }
    // Components of the Form

    private void setUpPage() {

        this.form = new JPanel();
//        setBounds(300, 90, 1280, 720);


        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(175, 100);
        this.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 200);
        this.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(250, 200);
        this.add(tname);

        mno = new JLabel("Email");
        mno.setFont(new Font("Arial", Font.PLAIN, 20));
        mno.setSize(100, 20);
        mno.setLocation(100, 250);
        this.add(mno);

        email = new JTextField();
        email.setFont(new Font("Arial", Font.PLAIN, 15));
        email.setSize(190, 20);
        email.setLocation(250, 250);
        this.add(email);


        add = new JLabel("Password");
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setSize(100, 20);
        add.setLocation(100, 300);
        this.add(add);

        tadd = new JTextField();
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(190, 20);
        tadd.setLocation(250, 300);
        this.add(tadd);

        confirm = new JLabel("Confirm Password");
        confirm.setFont(new Font("Arial", Font.PLAIN, 17));
        confirm.setSize(150, 20);
        confirm.setLocation(100, 350);
        this.add(confirm);

        confirmPass = new JTextField();
        confirmPass.setFont(new Font("Arial", Font.PLAIN, 15));
        confirmPass.setSize(190, 20);
        confirmPass.setLocation(250, 350);
        this.add(confirmPass);


        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(100, 450);
        sub.addActionListener(this);
        this.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(300, 450);
        reset.addActionListener(this);
        this.add(reset);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 400);
        this.add(res);

    }

    public JPanel getForm() {
        return this.form;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            String data
                    = "Name : "
                    + tname.getText() + "\n"
                    + "Email : "
                    + email.getText() + "\n";
            String data1 = "Password : " + tadd.getText();
            res.setText("Account Created!");
        } else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            tadd.setText(def);
            email.setText(def);
            confirmPass.setText(def);
            res.setText(def);
        }
    }
}

//submit page override


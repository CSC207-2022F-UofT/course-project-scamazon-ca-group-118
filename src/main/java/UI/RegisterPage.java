package UI;

import forms.RegisterForm;
import useCase.Register.RegisterFailed;
import useCase.Register.RegisterResponseModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegisterPage extends Page implements ActionListener{
    //Components of the form
    private JLabel title;
    private JLabel name;
    private JTextField input_name;
    private JLabel email;
    private JTextField input_email;
    private JLabel password;
    private JTextField input_password;
    private JLabel confirmPass;
    private JTextField input_confirmPass;
    private JButton sub;
    private JButton reset;
    private JLabel res;
    private RegisterForm form;

    public RegisterPage(String title) {
        super(title);
        this.setLayout(null);
        setUpPage();
    }

    private void setUpPage() {
        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(550, 150);
        this.add(title);

        name = new JLabel("Username");
        name.setFont(new Font("Arial", Font.PLAIN, 17));
        name.setSize(100, 20);
        name.setLocation(500, 250);
        this.add(name);

        input_name = new JTextField();
        input_name.setFont(new Font("Arial", Font.PLAIN, 15));
        input_name.setSize(190, 20);
        input_name.setLocation(650, 250);
        this.add(input_name);

        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 17));
        email.setSize(100, 20);
        email.setLocation(500, 300);
        this.add(email);

        input_email = new JTextField();
        input_email.setFont(new Font("Arial", Font.PLAIN, 15));
        input_email.setSize(190, 20);
        input_email.setLocation(650, 300);
        this.add(input_email);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 17));
        password.setSize(100, 20);
        password.setLocation(500, 350);
        this.add(password);

        input_password = new JTextField();
        input_password.setFont(new Font("Arial", Font.PLAIN, 15));
        input_password.setSize(190, 20);
        input_password.setLocation(650, 350);
        this.add(input_password);

        confirmPass = new JLabel("Confirm Password");
        confirmPass.setFont(new Font("Arial", Font.PLAIN, 17));
        confirmPass.setSize(150, 20);
        confirmPass.setLocation(500, 400);
        this.add(confirmPass);

        input_confirmPass = new JTextField();
        input_confirmPass.setFont(new Font("Arial", Font.PLAIN, 15));
        input_confirmPass.setSize(190, 20);
        input_confirmPass.setLocation(650, 400);
        this.add(input_confirmPass);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(550, 500);
        sub.addActionListener(this);
        this.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(675, 500);
        reset.addActionListener(this);
        this.add(reset);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 17));
        res.setSize(475, 25);
        res.setLocation(550, 450);
        this.add(res);

        this.setPreferredSize(new Dimension(1280, 720)); //570
        this.setMaximumSize(new Dimension(1280, 720));

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            try{
                this.form = new RegisterForm(this.getTitle(), input_email.getText(), input_name.getText(),
                        input_password.getText(), input_confirmPass.getText());
                RegisterResponseModel response = this.form.getResponseModel();
                res.setText(response.getMessage());
            }catch (RegisterFailed error){
                res.setText(error.getMessage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == reset) {
            String def = "";
            input_name.setText(def);
            input_password.setText(def);
            input_email.setText(def);
            input_confirmPass.setText(def);
            res.setText(def);
        }
    }
}


package forms;

import database.RegisterDatabaseGateway;
import useCase.Register.RegisterRequestModel;
import useCase.Register.RegisterResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyForm
        extends JPanel
        implements ActionListener {

    // Components of the Form
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

    public MyForm() {

        this.form = new JPanel();
        setBounds(300, 90, 600, 650);
        form.setLayout(null);


        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(175, 100);
        form.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 200);
        form.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(250, 200);
        form.add(tname);

        mno = new JLabel("Email");
        mno.setFont(new Font("Arial", Font.PLAIN, 20));
        mno.setSize(100, 20);
        mno.setLocation(100, 250);
        form.add(mno);

        email = new JTextField();
        email.setFont(new Font("Arial", Font.PLAIN, 15));
        email.setSize(190, 20);
        email.setLocation(250, 250);
        form.add(email);


        add = new JLabel("Password");
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setSize(100, 20);
        add.setLocation(100, 300);
        form.add(add);

        tadd = new JTextField();
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(190, 20);
        tadd.setLocation(250, 300);
        form.add(tadd);

        confirm = new JLabel("Confirm Password");
        confirm.setFont(new Font("Arial", Font.PLAIN, 17));
        confirm.setSize(150, 20);
        confirm.setLocation(100, 350);
        form.add(confirm);

        confirmPass = new JTextField();
        confirmPass.setFont(new Font("Arial", Font.PLAIN, 15));
        confirmPass.setSize(190, 20);
        confirmPass.setLocation(250, 350);
        form.add(confirmPass);


        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(100, 450);
        sub.addActionListener(this);
        form.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(300, 450);
        reset.addActionListener(this);
        form.add(reset);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 400);
        form.add(res);

        setVisible(true);

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


public class RegisterForm extends Form{

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private RegisterResponseModel responseModel;
    private MyForm form;

    public RegisterForm(String title) {
        super(title);
        this.form = new MyForm();
    }

    public MyForm getForm(){
        return this.form;
    }
    @Override
    protected boolean validateForm() {
        //password and confirm password
        if (!((this.password.equals(this.confirmPassword)) && (password.length() >= 8))) {
            //output error msg
            return false;
        }

        //email validation
        if (!this.email.contains("@")) {
            //error msg
            return false;
        }

        return true;

    }

    @Override
    protected void submitForm() {
        if (this.validateForm()){
            RegisterRequestModel requestModel = new RegisterRequestModel(username, email, password);
            this.responseModel = new RegisterResponseModel(requestModel);
        }
    }

}

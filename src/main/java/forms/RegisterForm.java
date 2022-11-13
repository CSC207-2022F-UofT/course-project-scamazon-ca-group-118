package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyFrame
        extends JFrame
        implements ActionListener {

    // Components of the Form
    private Container c;
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
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

    public MyFrame() {
        setTitle("Registration Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(75, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);

        mno = new JLabel("Email");
        mno.setFont(new Font("Arial", Font.PLAIN, 20));
        mno.setSize(100, 20);
        mno.setLocation(75, 150);
        c.add(mno);

        email = new JTextField();
        email.setFont(new Font("Arial", Font.PLAIN, 15));
        email.setSize(150, 20);
        email.setLocation(200, 150);
        c.add(email);


        add = new JLabel("Password");
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setSize(100, 20);
        add.setLocation(75, 200);
        c.add(add);

        tadd = new JTextField();
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(150, 20);
        tadd.setLocation(200, 200);
        c.add(tadd);

        confirm = new JLabel("Confirm Password");
        confirm.setFont(new Font("Arial", Font.PLAIN, 15));
        confirm.setSize(125, 20);
        confirm.setLocation(75, 250);
        c.add(confirm);

        confirmPass = new JTextField();
        confirmPass.setFont(new Font("Arial", Font.PLAIN, 15));
        confirmPass.setSize(150, 20);
        confirmPass.setLocation(200, 250);
        c.add(confirmPass);


        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        reset.addActionListener(this);
        c.add(reset);

        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        resadd.setLineWrap(true);
        c.add(resadd);

        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            String data
                    = "Name : "
                    + tname.getText() + "\n"
                    + "Email : "
                    + email.getText() + "\n";
            String data1 = "Password : " + tadd.getText();
            tout.setText(data + data1);
            tout.setEditable(false);
            res.setText("Registration Successfully..");
        } else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            tadd.setText(def);
            email.setText(def);
            confirmPass.setText(def);
            res.setText(def);
            tout.setText(def);
            resadd.setText(def);
        }
    }
}

class Registration {

    public static void main(String[] args) throws Exception {
        MyFrame f = new MyFrame();
    }
}

public class RegisterForm extends Form {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public RegisterForm(String title) {
        super(title);
    }

    @Override
    boolean validateForm() {
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

        //use database interactor to see if input is unique

        return true;

    }

    @Override
    void submitForm() {

    }

    public void createUser(String username, String email, String password) {
        //TODO: call createUser method in user class using database interactor
    }

}

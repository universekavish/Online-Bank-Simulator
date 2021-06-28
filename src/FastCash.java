import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class FastCash extends JFrame implements ActionListener{
	JLabel l1, l2;
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    JTextField t1;
    String pin;
    FastCash(String pin){
    	this.pin = pin;
    	
    	l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("Rs 100");
        b1.setFont(new Font("System", Font.BOLD,18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b2 = new JButton("Rs 500");
        b2.setFont(new Font("System", Font.BOLD,18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b3 = new JButton("Rs 1000");
        b3.setFont(new Font("System", Font.BOLD,18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b4 = new JButton("Rs 2000");
        b4.setFont(new Font("System", Font.BOLD,18));
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        b5 = new JButton("Rs 5000");
        b5.setFont(new Font("System", Font.BOLD,18));
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        b6 = new JButton("Rs 10000");
        b6.setFont(new Font("System", Font.BOLD,18));
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.WHITE);
        b7 = new JButton("BACK");
        b7.setFont(new Font("System", Font.BOLD,18));
        b7.setBackground(Color.BLACK);
        b7.setForeground(Color.WHITE);
        
        setLayout(null);

        l1.setBounds(235, 400, 700, 35);
        add(l1);

        b1.setBounds(170, 499, 150, 35);
        add(b1);

        b2.setBounds(390, 499, 150, 35);
        add(b2);

        b3.setBounds(170, 543, 150, 35);
        add(b3);

        b4.setBounds(390, 543, 150, 35);
        add(b4);

        b5.setBounds(170, 588, 150, 35);
        add(b5);

        b6.setBounds(390, 588, 150, 35);
        add(b6);

        b7.setBounds(390, 633, 150, 35);
        add(b7);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        setSize(960, 1080);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = ((JButton)ae.getSource()).getText().substring(3); //k
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pin+"'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("mode").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            } String num = "17";
            if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insuffient Balance");
                return;
            }

            if (ae.getSource() == b7) {
                this.setVisible(false);
                new Transactions(pin).setVisible(true);
            }else{
                //Date date = new Date();
                c.s.executeUpdate("insert into bank values('"+pin+"', null, 'Withdrawl', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
                    
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

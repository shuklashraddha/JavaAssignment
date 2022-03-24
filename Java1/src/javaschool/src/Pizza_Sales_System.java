import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pizza_Sales_System extends JFrame {

	private JPanel contentPane;
	private JTextField txtCustfname;
	private JTextField txtCustlname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pizza_Sales_System frame = new Pizza_Sales_System();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Pizza_Sales_System() {	
		initialize();
		Connect();
		
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtCustid;
	
	public void Connect()
	{
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/salessystem","root","");
		}
		catch(ClassNotFoundException ex)
		{
			
		}
		catch(SQLException ex)
		{
			
		}
		
	}
	
	
	
	
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1550, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter the customer info");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(592, 70, 258, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Customer ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(496, 204, 138, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter First Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(496, 298, 128, 26);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Last Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(496, 397, 128, 26);
		contentPane.add(lblNewLabel_3);
		
		txtCustfname = new JTextField();
		txtCustfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCustfname.setBounds(679, 298, 192, 32);
		contentPane.add(txtCustfname);
		txtCustfname.setColumns(10);
		
		txtCustlname = new JTextField();
		txtCustlname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCustlname.setBounds(679, 397, 192, 32);
		contentPane.add(txtCustlname);
		txtCustlname.setColumns(10);
		
		final JButton btnNewButton = new JButton("Save Customer & Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                  String cfname,clname;
				
				cfname=txtCustfname.getText();
				clname=txtCustlname.getText();
				
				try {
					pst=con.prepareStatement("insert into customer(c_fname,c_lname)values(?,?)");
					pst.setString(1, cfname);
                  pst.setString(2, clname);
                   
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record successfully saved!");
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				Order third=new Order();
				third.setVisible(true);
			
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(777, 537, 212, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				txtCustfname.setText("");
				txtCustlname.setText("");
				txtCustfname.requestFocus();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(538, 537, 128, 40);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Pizza Sales System");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel_4.setBounds(559, 20, 385, 40);
		contentPane.add(lblNewLabel_4);
		
		txtCustid = new JTextField();
		txtCustid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCustid.setColumns(10);
		txtCustid.setBounds(679, 198, 192, 32);
		contentPane.add(txtCustid);
	}
}

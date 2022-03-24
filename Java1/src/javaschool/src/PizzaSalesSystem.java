import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class PizzaSalesSystem extends JFrame {

	private JPanel contentPane;
	private JTextField txtAdmin;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PizzaSalesSystem frame = new PizzaSalesSystem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PizzaSalesSystem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 100, 991, 764);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(474, 124, 91, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Admin");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(315, 217, 101, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(315, 336, 67, 22);
		contentPane.add(lblNewLabel_2);
		
		txtAdmin = new JTextField();
		txtAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtAdmin.setBounds(426, 220, 198, 28);
		contentPane.add(txtAdmin);
		txtAdmin.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				 
			//	String uname=txtAdmin.getText();
				//String psd=txtPassword.getText();
				
				//if (uname.equals("kiran") && psd.equals("1234"))
				//{
				//	JOptionPane.showMessageDialog(contentPane,"You are successfully logined");
				//	Pizza_Sales_System second=new Pizza_Sales_System();
			//		second.setVisible(true);
			//	}
			//	else
			//	{
			//		JOptionPane.showMessageDialog(contentPane,"Failed to login");
			//	}
				
				try { 
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/salessystem","root","");
					Statement stmt=con.createStatement();
					String sql="Select *from admin where a_name='"+txtAdmin.getText()+"' and password='"+txtPassword.getText()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(rs.next())
						JOptionPane.showMessageDialog(null,"Login Successfully...");
					Pizza_Sales_System second=new Pizza_Sales_System();
							second.setVisible(true);
				//else
					JOptionPane.showMessageDialog(null,"Incorrect Username and Password...");
				con.close();
				}catch(Exception e) { System.out.print(e);}
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(278, 519, 138, 39);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frame=new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame,"Confirm if you want to Exit","Exit",
					JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
				{
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(580, 520, 138, 39);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_3 = new JLabel("Pizza Sales System");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel_3.setBounds(350, 38, 495, 60);
		contentPane.add(lblNewLabel_3);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPassword.setBounds(426, 336, 198, 28);
		contentPane.add(txtPassword);
	}
}

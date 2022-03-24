import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;

public class Order extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtpid;
	private JTextField txtpname;
	private JTextField txtprice;
	private JTable table;
	private JTextField txttotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order frame = new Order();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Order() {	
		Orderr();
		Connect();
		table_load();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtquantity;
	private JTextField txtbill11;
	
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
	
	public void table_load()
	{
		try {
			pst=con.prepareStatement("SELECT * FROM `order`");
		rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	public void bill() {
		String total=txttotal.getText();
		
		DefaultTableModel model=new DefaultTableModel();
		model=(DefaultTableModel)table.getModel();
		
		txtbill11.setText(txtbill11.getText() +"\n");
		txtbill11.setText(txtbill11.getText() +"\n");
		txtbill11.setText(txtbill11.getText() +"\n");
		
		txtbill11.setText(txtbill11.getText() + "Product" + "\t" + "Price" + "\t" + "Amount" + "\n");
		
	//	Object String;
		for(int i =0;i<model.getRowCount(); i++) {
	
			String pnamer = (String)model.getValueAt(i, 1);
		    String price=(String)model.getValueAt(i, 3);
		    String amount=(String)model.getValueAt(i, 4);
		    
		    txtbill11.setText(txtbill11.getText() + pnamer + "\t" + price + "\t" + amount + "\n");
		}
	}
	
	

	/**
	 * Create the frame.
	 */
	public void Orderr() {
		setTitle("  ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1550, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pizza Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(693, 35, 170, 50);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Product", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 116, 358, 445);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Product Id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(29, 76, 103, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Product Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(29, 143, 103, 26);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(29, 205, 103, 26);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(29, 274, 81, 26);
		panel.add(lblNewLabel_4);
		
		txtpid = new JTextField();
		txtpid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
             try {
					
					String pid=txtpid.getText();
					
					pst=con.prepareStatement("select Product_name,Quantity,rate from product_details where Product_id=?");
					pst.setString(1, pid);
					ResultSet rs=pst.executeQuery();
					
					if(rs.next()==true)
					{
						String Product_name=rs.getString(1);
						String Quantity=rs.getString(2);
						String rate=rs.getString(3);
						
						txtpname.setText(Product_name);
						txtquantity.setText(Quantity);
						txtprice.setText(rate);
						
					}
					else
					{
						txtpname.setText("");
						txtquantity.setText("");
						txtprice.setText("");
					}
						
				}
				catch(SQLException ex) {
					
				}
				
				
				
			}
		});
		txtpid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpid.setBounds(172, 75, 142, 25);
		panel.add(txtpid);
		txtpid.setColumns(10);
		
		txtpname = new JTextField();
		txtpname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpname.setBounds(172, 143, 142, 25);
		panel.add(txtpname);
		txtpname.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtprice.setBounds(172, 274, 142, 25);
		panel.add(txtprice);
		txtprice.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                  
				String pid,pname,quantity,price;
			
				pid=txtpid.getText();
				pname=txtpname.getText();
				quantity=txtquantity.getText();
				price=txtprice.getText();
				
				int pprice=Integer.parseInt(txtprice.getText());
				int pquantity=Integer.parseInt(txtquantity.getText());
				int tot=pprice * pquantity;
				String strvalue=String.valueOf(tot);
				
				try {
					pst=con.prepareStatement("INSERT INTO `order`(`oProduct_id`, `oProduct_name`, `oQuantity`, `oPrice`, `Total`) VALUES (?,?,?,?,?)");
					pst.setString(1, pid);
                    pst.setString(2, pname);
                    pst.setString(3, quantity);
                    pst.setString(4, price);
                    
                    
                    pst.setString(5, strvalue);
                    
                 // int sum=0;
                    
        			//	for(int i=0;i<table.getRowCount();i++)
        			//	{
        			//		sum=sum+Integer.parseint(table.getValueAt(i,5)).toString();
        			//	}
        			//	txttotal.setText(Integer.toString(sum));
                    txttotal.setText("448");
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Product added!!");
					
					 
		                    
					
					table_load();
					txtpid.setText("");
					txtpname.setText("");
					txtquantity.setText("");
					txtprice.setText("");
					txtpid.requestFocus();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}		
			}
			
		});
		
	
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(208, 366, 103, 36);
		panel.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtpid.setText("");
				txtpname.setText("");
				txtquantity.setText("");
				txtprice.setText("");
				txtpid.requestFocus();
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(60, 366, 103, 36);
		panel.add(btnClear);
		
		txtquantity = new JTextField();
		txtquantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtquantity.setColumns(10);
		txtquantity.setBounds(172, 205, 142, 25);
		panel.add(txtquantity);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(378, 148, 423, 378);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_5 = new JLabel("Total Amount");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(447, 567, 112, 27);
		contentPane.add(lblNewLabel_5);
		
		txttotal = new JTextField();
		txttotal.setBounds(569, 568, 170, 29);
		contentPane.add(txttotal);
		txttotal.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Print Bill");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					txtbill11.print();
				}
				catch(PrinterException e1) {
					e1.printStackTrace();
				
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(462, 652, 148, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancel Product");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String pid;
					
					
					pid=txtpid.getText();
					
					try {
						pst=con.prepareStatement("DELETE FROM `order` WHERE oProduct_id=?");
						
	                    pst.setString(1, pid);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Product successfully canceled!");
						
						table_load();
						txtpid.setText("");
						txtpname.setText("");
						txtquantity.setText("");
						txtprice.setText("");
						txtpid.requestFocus();
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}
				
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(631, 652, 159, 39);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pizza_Sales_System frame=new Pizza_Sales_System();
				frame.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1_1.setBounds(85, 633, 148, 39);
		contentPane.add(btnNewButton_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(795, 106, 315, 606);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Menu Index");
		lblNewLabel_6.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lblNewLabel_6.setBounds(121, 10, 116, 30);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("11");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(21, 50, 40, 24);
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("12");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7_1.setBounds(21, 84, 40, 24);
		panel_1.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_2 = new JLabel("13");
		lblNewLabel_7_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7_2.setBounds(21, 118, 40, 24);
		panel_1.add(lblNewLabel_7_2);
		
		JLabel lblNewLabel_7_3 = new JLabel("14");
		lblNewLabel_7_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7_3.setBounds(21, 152, 40, 24);
		panel_1.add(lblNewLabel_7_3);
		
		JLabel lblNewLabel_7_4 = new JLabel("15");
		lblNewLabel_7_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7_4.setBounds(21, 191, 40, 24);
		panel_1.add(lblNewLabel_7_4);
		
		JLabel lblNewLabel_7_5 = new JLabel("16");
		lblNewLabel_7_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7_5.setBounds(21, 225, 40, 24);
		panel_1.add(lblNewLabel_7_5);
		
		JLabel lblNewLabel_7_6 = new JLabel("17");
		lblNewLabel_7_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7_6.setBounds(21, 259, 40, 24);
		panel_1.add(lblNewLabel_7_6);
		
		JLabel lblNewLabel_7_7 = new JLabel("18");
		lblNewLabel_7_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7_7.setBounds(21, 293, 40, 24);
		panel_1.add(lblNewLabel_7_7);
		
		JLabel lblNewLabel_7_8 = new JLabel("19");
		lblNewLabel_7_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7_8.setBounds(21, 329, 40, 24);
		panel_1.add(lblNewLabel_7_8);
		
		JLabel lblNewLabel_7_9 = new JLabel("20");
		lblNewLabel_7_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7_9.setBounds(21, 363, 40, 24);
		panel_1.add(lblNewLabel_7_9);
		
		JLabel lblNewLabel_8 = new JLabel("Extra");
		lblNewLabel_8.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
		lblNewLabel_8.setBounds(21, 398, 75, 21);
		panel_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("31");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(21, 441, 40, 21);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_9_1 = new JLabel("32");
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9_1.setBounds(21, 472, 40, 21);
		panel_1.add(lblNewLabel_9_1);
		
		JLabel lblNewLabel_9_2 = new JLabel("33");
		lblNewLabel_9_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9_2.setBounds(21, 503, 40, 21);
		panel_1.add(lblNewLabel_9_2);
		
		JLabel lblNewLabel_9_3 = new JLabel("34");
		lblNewLabel_9_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9_3.setBounds(21, 534, 40, 21);
		panel_1.add(lblNewLabel_9_3);
		
		JLabel lblNewLabel_9_4 = new JLabel("35");
		lblNewLabel_9_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9_4.setBounds(21, 565, 40, 21);
		panel_1.add(lblNewLabel_9_4);
		
		JLabel lblNewLabel_10 = new JLabel("Cheese N Corn");
		lblNewLabel_10.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(71, 51, 140, 21);
		panel_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Paneer Makhani");
		lblNewLabel_10_1.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_1.setBounds(71, 85, 140, 21);
		panel_1.add(lblNewLabel_10_1);
		
		JLabel lblNewLabel_10_2 = new JLabel("Burger Pizza");
		lblNewLabel_10_2.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_2.setBounds(71, 119, 140, 21);
		panel_1.add(lblNewLabel_10_2);
		
		JLabel lblNewLabel_10_3 = new JLabel("Black Olive");
		lblNewLabel_10_3.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_3.setBounds(71, 153, 140, 21);
		panel_1.add(lblNewLabel_10_3);
		
		JLabel lblNewLabel_10_4 = new JLabel("Indi Tandoori Paneer");
		lblNewLabel_10_4.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_4.setBounds(71, 192, 154, 21);
		panel_1.add(lblNewLabel_10_4);
		
		JLabel lblNewLabel_10_5 = new JLabel("Veg Extravaganza");
		lblNewLabel_10_5.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_5.setBounds(71, 226, 140, 21);
		panel_1.add(lblNewLabel_10_5);
		
		JLabel lblNewLabel_10_6 = new JLabel("Mexican Green Wave");
		lblNewLabel_10_6.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_6.setBounds(71, 260, 154, 21);
		panel_1.add(lblNewLabel_10_6);
		
		JLabel lblNewLabel_10_7 = new JLabel("Italian");
		lblNewLabel_10_7.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_7.setBounds(71, 294, 140, 21);
		panel_1.add(lblNewLabel_10_7);
		
		JLabel lblNewLabel_10_8 = new JLabel("Double Cheese");
		lblNewLabel_10_8.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_8.setBounds(71, 330, 140, 21);
		panel_1.add(lblNewLabel_10_8);
		
		JLabel lblNewLabel_10_9 = new JLabel("Margherita");
		lblNewLabel_10_9.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_9.setBounds(71, 364, 140, 21);
		panel_1.add(lblNewLabel_10_9);
		
		JLabel lblNewLabel_10_10 = new JLabel("Thums Up 1Ltr");
		lblNewLabel_10_10.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_10.setBounds(71, 440, 140, 21);
		panel_1.add(lblNewLabel_10_10);
		
		JLabel lblNewLabel_10_11 = new JLabel("Slice 1Ltr");
		lblNewLabel_10_11.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_11.setBounds(71, 471, 140, 21);
		panel_1.add(lblNewLabel_10_11);
		
		JLabel lblNewLabel_10_12 = new JLabel("Chips");
		lblNewLabel_10_12.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_12.setBounds(71, 502, 140, 21);
		panel_1.add(lblNewLabel_10_12);
		
		JLabel lblNewLabel_10_13 = new JLabel("French Fries");
		lblNewLabel_10_13.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_13.setBounds(71, 533, 140, 21);
		panel_1.add(lblNewLabel_10_13);
		
		JLabel lblNewLabel_10_14 = new JLabel("Sweet Candy");
		lblNewLabel_10_14.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblNewLabel_10_14.setBounds(71, 564, 140, 21);
		panel_1.add(lblNewLabel_10_14);
		
		JLabel lblNewLabel_11 = new JLabel("\u20B9150.00");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(246, 50, 64, 24);
		panel_1.add(lblNewLabel_11);
		
		JLabel lblNewLabel_11_1 = new JLabel("\u20B9199.00");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_1.setBounds(246, 84, 64, 24);
		panel_1.add(lblNewLabel_11_1);
		
		JLabel lblNewLabel_11_2 = new JLabel("\u20B999.00");
		lblNewLabel_11_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_2.setBounds(246, 118, 64, 24);
		panel_1.add(lblNewLabel_11_2);
		
		JLabel lblNewLabel_11_3 = new JLabel("\u20B9180.00");
		lblNewLabel_11_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_3.setBounds(246, 152, 64, 24);
		panel_1.add(lblNewLabel_11_3);
		
		JLabel lblNewLabel_11_4 = new JLabel("\u20B9250.00");
		lblNewLabel_11_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4.setBounds(246, 191, 64, 24);
		panel_1.add(lblNewLabel_11_4);
		
		JLabel lblNewLabel_11_4_1 = new JLabel("\u20B9150.00");
		lblNewLabel_11_4_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4_1.setBounds(246, 225, 64, 24);
		panel_1.add(lblNewLabel_11_4_1);
		
		JLabel lblNewLabel_11_4_1_1 = new JLabel("\u20B9200.00");
		lblNewLabel_11_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4_1_1.setBounds(246, 259, 64, 24);
		panel_1.add(lblNewLabel_11_4_1_1);
		
		JLabel lblNewLabel_11_4_1_1_1 = new JLabel("\u20B9180.00");
		lblNewLabel_11_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4_1_1_1.setBounds(246, 293, 64, 24);
		panel_1.add(lblNewLabel_11_4_1_1_1);
		
		JLabel lblNewLabel_11_4_1_1_2 = new JLabel("\u20B9300.00");
		lblNewLabel_11_4_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4_1_1_2.setBounds(246, 329, 64, 24);
		panel_1.add(lblNewLabel_11_4_1_1_2);
		
		JLabel lblNewLabel_11_4_1_1_3 = new JLabel("\u20B9200.00");
		lblNewLabel_11_4_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4_1_1_3.setBounds(246, 363, 64, 24);
		panel_1.add(lblNewLabel_11_4_1_1_3);
		
		JLabel lblNewLabel_11_4_1_1_4 = new JLabel("\u20B995.00");
		lblNewLabel_11_4_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4_1_1_4.setBounds(246, 439, 64, 24);
		panel_1.add(lblNewLabel_11_4_1_1_4);
		
		JLabel lblNewLabel_11_4_1_1_5 = new JLabel("\u20B965.00");
		lblNewLabel_11_4_1_1_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4_1_1_5.setBounds(246, 470, 64, 24);
		panel_1.add(lblNewLabel_11_4_1_1_5);
		
		JLabel lblNewLabel_11_4_1_1_6 = new JLabel("\u20B910.00");
		lblNewLabel_11_4_1_1_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4_1_1_6.setBounds(246, 501, 64, 24);
		panel_1.add(lblNewLabel_11_4_1_1_6);
		
		JLabel lblNewLabel_11_4_1_1_7 = new JLabel("\u20B925.00");
		lblNewLabel_11_4_1_1_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4_1_1_7.setBounds(246, 532, 64, 24);
		panel_1.add(lblNewLabel_11_4_1_1_7);
		
		JLabel lblNewLabel_11_4_1_1_8 = new JLabel("\u20B95.00");
		lblNewLabel_11_4_1_1_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_4_1_1_8.setBounds(246, 563, 64, 24);
		panel_1.add(lblNewLabel_11_4_1_1_8);
		
		JButton btnNewButton_1_2 = new JButton("Receipt");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bill();
				
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1_2.setBounds(292, 652, 148, 39);
		contentPane.add(btnNewButton_1_2);
		
		txtbill11 = new JTextField();
		txtbill11.setBounds(1120, 171, 412, 445);
		contentPane.add(txtbill11);
		txtbill11.setColumns(10);
	}
}

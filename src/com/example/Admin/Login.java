package com.example.Admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.admin.dbConneciton;
import com.detaile.SignupWork;
import com.sun.corba.se.impl.ior.JIDLObjectKeyTemplate;

public class Login extends JFrame{

	JPanel mainPanel=new JPanel();
	JPanel panelNorth=new JPanel();
	JPanel panelSouth=new JPanel();
	JPanel panelWest=new JPanel();
	JPanel panelCenter=new JPanel();	
	JLabel lblWlc=new JLabel("WELCOME TO SUPER SHOP"); 
	JLabel lblIcon=new JLabel(new ImageIcon("Image/change-password-box-icon.png"));
	JLabel lblBackGroundImage=new JLabel(new ImageIcon("Image/ImageBackground.jpg"));
	JLabel lblUserName=new JLabel("UserName: ");
	JLabel lblPass=new JLabel("Password: ");
	JLabel lblUserType=new JLabel("UserType: ");	
	JTextField txtUserName=new JTextField(20);
	JPasswordField textPass=new JPasswordField();
	JComboBox cmbUserType=new JComboBox();	
	JButton btnLogin=new JButton("Login", new ImageIcon("Image/Ok-icon.png"));
	JButton btnCencel=new JButton("Cencel", new ImageIcon("Image/cancel-icon.png"));
	JButton btnSignUp=new JButton("Sign Up", new ImageIcon("Image/edit-1282.png"));	
	JButton btnForgotPass=new JButton("Forgot Password?");
	Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();

	SessionBean sessionbean=new SessionBean();

	SignupWork signup=new SignupWork(this);
	ForgotPassword forgotPass=new ForgotPassword(this);
	int count=0;

	public Login(){		
		init();
		cmp();
		btnAction();
		txtUserName.setText("Abdul Ajij");
		textPass.setText("123");
		cmbUserType.setSelectedItem("General");
	}

	private void btnAction() {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidation()){ 
					sessionbean.setUserName(txtUserName.getText().trim().toString());
					sessionbean.setUserType(cmbUserType.getSelectedItem().toString());
					sessionbean.setCompanyName("Binary Care");
					sessionbean.setCompanyAddress("Chawk bazar ctg");
					sessionbean.setDeveloperAddress("Lalkhan Bazar");
					loginAction();	
				}					
			}
		});
		btnCencel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkconfirmation("do you want to Exit?")){
					System.exit(0);
				}
			}
		});
		this.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent arg0) {
			}
			public void windowIconified(WindowEvent arg0) {
			}
			public void windowDeiconified(WindowEvent arg0) {
			}
			public void windowDeactivated(WindowEvent arg0) {
			}
			public void windowClosing(WindowEvent arg0) {
				if(closeConfirmation()){
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else{
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
			public void windowClosed(WindowEvent arg0) {
			}
			public void windowActivated(WindowEvent arg0) {
			}
		});
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUpWork();
				signup.autoId();
			}
		});
		btnForgotPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forgottenPasswordWork();
			}
		});
		/*signup.close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUpCloseAction();
			}
		});*/

	}
	/*public void signUpCloseAction(){
		signup.setVisible(false);
		setSize(490,270);
		setLocationRelativeTo(null);
		mainPanel.setVisible(true);
	}*/
	public void forgottenPasswordWork(){
		mainPanel.setVisible(false);
		add(forgotPass);
		forgotPass.setVisible(true);
		setSize(480 ,270);
		setTitle("Forgot Password");
		setLocationRelativeTo(null);
		setResizable(false);
	}
	public void signUpWork(){
		mainPanel.setVisible(false);
		add(signup);
		signup.setVisible(true);
		setSize(500,500);
		setLocationRelativeTo(null);
		setTitle("signUp Please");
		signup.cmbRefrenceByDataLoad();
		setResizable(false);
	}
	public boolean closeConfirmation(){
		int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to close?",
				" confirmation",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public boolean checkValidation(){
		if(!txtUserName.getText().trim().isEmpty()){
			if(!textPass.getText().trim().isEmpty()){
				if(cmbUserType.getSelectedItem()!=null && cmbUserType.getSelectedIndex()!=0){
					if(checkUserName()){
						if(checkPassword()){
							if(checkUserType()){
								return true;
							}
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "incorrect User Name","Error",JOptionPane.WARNING_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Please select User Type","Error",JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Please insert Password","Error",JOptionPane.WARNING_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please insert User Name","Error",JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	public boolean checkUserName(){
		try {
			String sql="select userName from tbnewuserinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				if(txtUserName.getText().trim().toString().equals(rs.getString("userName"))){
					return true;
				}
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}
	public boolean checkPassword(){
		try {
			String sql="select pass from tbnewuserinfo where "
					+ "userName='"+txtUserName.getText().trim().toString()+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				if(textPass.getText().trim().toString().equals(rs.getString("pass"))){
					return true;
				}
				else{
					JOptionPane.showMessageDialog(null, "incorrect password");
					count++;
					if(count==3){
						count=0;
						if(checkconfirmation("Are you forgot your password?"+"\n"+
								"Don't worrry you can reset your password."+"\n"+
								"please click the 'YES' button and reset your password.")){		
							btnForgotPass.setEnabled(true);
							btnLogin.setEnabled(false);
						}
					}
				}
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}
	public boolean checkUserType(){
		try {
			String sql="select userType from tbnewuserinfo where "
					+ "userName='"+txtUserName.getText().trim().toString()+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				if(cmbUserType.getSelectedItem().toString().equals(rs.getString("userType"))){
					return true;
				}
				else{
					JOptionPane.showMessageDialog(null, "incorrect userType");
				}
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}
	public boolean checkconfirmation(String caption){
		int a=JOptionPane.showConfirmDialog(null, caption,"confirmation",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	private void loginAction(){
		mainPanel.setVisible(false);
		WorkingPanel wp=new WorkingPanel(this,sessionbean);
		add(wp);
		wp.setVisible(true);
		setResizable(false);
		setSize(screenSize);
		setTitle("Working Panel");
		setLocationRelativeTo(null);
	}
	private void cmp() {
		BorderLayout border=new BorderLayout();
		add(mainPanel);
		mainPanel.setLayout(border);
		border.setVgap(0);
		mainPanel.add(lblBackGroundImage,BorderLayout.CENTER);
		lblBackGroundImage.setLayout(new BorderLayout());		
		lblBackGroundImage.add(panelNorth,BorderLayout.NORTH);
		panelNorth.setOpaque(false);
		panelNorthWork();
		lblBackGroundImage.add(panelSouth,BorderLayout.SOUTH);
		panelSouth.setOpaque(false);
		panelSouthWork();
		lblBackGroundImage.add(panelCenter,BorderLayout.CENTER);
		panelCenter.setOpaque(false);
		panelCenterWork();
	}
	private void panelCenterWork() {
		GridBagConstraints c=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenter.setLayout(grid);
		panelCenter.setBackground(Color.decode("#BBFFFF"));

		c.gridx=0;
		c.gridy=0;
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(3, 3, 3, 3);
		panelCenter.add(lblUserName,c);
		lblUserName.setForeground(Color.BLACK);

		c.gridx=1;
		c.gridy=0;
		panelCenter.add(txtUserName,c);
		txtUserName.setBackground(Color.WHITE);

		c.gridx=0;
		c.gridy=1;
		panelCenter.add(lblPass,c);
		lblPass.setForeground(Color.BLACK);

		c.gridx=1;
		c.gridy=1;
		panelCenter.add(textPass,c);
		textPass.setBackground(Color.WHITE);

		c.gridx=0;
		c.gridy=3;
		panelCenter.add(lblUserType,c);
		lblUserType.setForeground(Color.BLACK);

		c.gridx=1;
		c.gridy=3;
		panelCenter.add(cmbUserType,c);
		cmbUserType.addItem("");
		cmbUserType.addItem("Admin");
		cmbUserType.addItem("Super Admin");
		cmbUserType.addItem("Executive");
		cmbUserType.addItem("General");
		cmbUserType.setBackground(Color.WHITE);
	}
	private void panelSouthWork() {
		panelSouth.setPreferredSize(new Dimension(0,55));
		FlowLayout flow=new FlowLayout();
		panelSouth.setLayout(flow);
		panelSouth.add(btnLogin);
		panelSouth.add(btnCencel);
		panelSouth.add(btnSignUp);
		panelSouth.add(btnForgotPass);
		btnForgotPass.setEnabled(false);
		btnLogin.setPreferredSize(new Dimension(90,32));
		btnCencel.setPreferredSize(new Dimension(100,32));
		btnSignUp.setPreferredSize(new Dimension(100,32));
		btnForgotPass.setPreferredSize(new Dimension(130,32));
	}
	private void panelNorthWork() {
		panelNorth.setPreferredSize(new Dimension(0,50));
		FlowLayout flow=new FlowLayout();
		panelNorth.setLayout(flow);
		flow.setVgap(10);
		panelNorth.add(lblWlc);
		lblWlc.setFont(new Font("clibari",Font.BOLD, 22));
		lblWlc.setForeground(Color.DARK_GRAY);
	}
	private void init() {
		setSize(490,270);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Password Checking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

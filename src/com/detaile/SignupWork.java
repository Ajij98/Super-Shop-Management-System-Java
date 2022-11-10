package com.detaile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.admin.dbConneciton;
import com.example.Admin.Login;
import com.toedter.calendar.JDateChooser;

public class SignupWork extends JPanel{
	JFrame frame;

	JPanel panelCenter=new JPanel();
	JPanel panelSouth=new JPanel();

	ImageIcon iconOk=new ImageIcon("Image/Ok-icon.png");
	JButton btnSignUp=new JButton("Sign Up",iconOk);
	ImageIcon iconRefresh=new ImageIcon("Images/btnRefresh.png");
	JButton btnRefresh=new JButton("Refresh",iconRefresh);
	ImageIcon iconCencel=new ImageIcon("Image/cancel-icon.png");
	JButton btnCencel=new JButton("Cencel",iconCencel);

	JLabel lblUserId=new JLabel("User Id");
	JLabel lblName=new JLabel("Name");
	JLabel lblUserName=new JLabel("UserName");
	JLabel lblDesignation=new JLabel("Designation");
	JLabel lblPassword=new JLabel("Password");
	JLabel lblDateofjoin=new JLabel("DateOfJoin");
	JLabel lblActivation=new JLabel("Activation");
	JLabel lblEmailAddress=new JLabel("EmailAddress");
	JLabel lblNationalId=new JLabel("NationalId");
	JLabel lblAddress=new JLabel("Address");
	JLabel lblMobileNumber=new JLabel("MobileNumber");
	JLabel lblUserType=new JLabel("UserType");
	JLabel lblRefrenceByUserName=new JLabel("RefrenceByUserName");
	JLabel lblPasswordUser=new JLabel("ReferenceUserPass");

	JTextField txtUserId=new JTextField(20);
	JTextField txtName=new JTextField();
	JTextField txtUserName=new JTextField();
	JTextField txtDesignation=new JTextField();
	JPasswordField txtPass=new JPasswordField();
	JDateChooser dateDateofJoin=new JDateChooser();
	JComboBox cmbActivation=new JComboBox();
	JTextField txtEmailAddress=new JTextField();
	JTextField txtNationalId=new JTextField();
	JTextArea txtAddress=new JTextArea(2,20);
	JTextField txtMobileNumber=new JTextField();
	JComboBox cmbUserType=new JComboBox();
	JComboBox cmbRefrenceByUserName=new JComboBox();
	JTextField txtPasswordUser=new JTextField();

	JScrollPane scrollAddress=new JScrollPane(txtAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public SignupWork(JFrame frame){
		//init();
		cmp();
		btnAction();
		this.frame=frame;
	}
	public void refreshWork(){
		txtUserId.setText("");
		txtName.setText("");
		txtUserName.setText("");
		txtDesignation.setText("");
		txtPass.setText("");
		dateDateofJoin.setDate(new Date());
		cmbActivation.setSelectedIndex(0);
		txtEmailAddress.setText("");
		txtNationalId.setText("");
		txtAddress.setText("");
		txtMobileNumber.setText("");
		cmbUserType.setSelectedIndex(0);
		cmbRefrenceByUserName.setSelectedIndex(0);
		txtPasswordUser.setText("");
		autoId();
		cmbRefrenceByDataLoad();
	}
	public void btnAction(){
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkValidation()){
					if(isExistingUserName()){
						if(checkReferenceUserPassword()){
							if(checkConfirmation("Sure to Save this Information?")){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "Data saved successfully");
									Login lg=new Login();
									lg.setVisible(true);
									frame.setVisible(false);
									refreshWork();
								}
							}
						}
					}
				}
			}
		});
		btnCencel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lg=new Login();
				lg.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshWork();
			}
		});
	}

	public void cmbRefrenceByDataLoad(){
		try {
			cmbRefrenceByUserName.removeAllItems();
			cmbRefrenceByUserName.addItem("");
			String sql="select userId,userName from tbnewuserinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbRefrenceByUserName.addItem(rs.getString("userId")+"#"+rs.getString("userName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbRefrenceBy Data Load from signUp");
		}
	}
	public void autoId(){
		try {
			String sql="select ifnull(max(cast(substring(userId,locate('-',userId)+1,length(userId)-"
					+ "locate('-',userId))as UNSIGNED)),0)+1 as id from tbnewuserinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				txtUserId.setText("userId-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"Auto Id from signUp");
		}
	}
	public boolean checkValidation(){
		if(!txtUserId.getText().trim().toString().isEmpty()){
			if(!txtName.getText().trim().toString().isEmpty()){
				if(!txtUserName.getText().trim().toString().isEmpty()){
					if(!txtDesignation.getText().trim().toString().isEmpty()){
						if(!txtPass.getText().trim().toString().isEmpty()){
							if(cmbActivation.getSelectedItem()!=null&& cmbActivation.getSelectedIndex()!=0){
								if(!txtEmailAddress.getText().trim().toString().isEmpty()){
									if(!txtNationalId.getText().trim().toString().isEmpty()){
										if(!txtAddress.getText().trim().toString().isEmpty()){
											if(!txtMobileNumber.getText().trim().toString().isEmpty()){
												if(cmbUserType.getSelectedItem()!=null&&
														cmbUserType.getSelectedIndex()!=0){
													if(cmbRefrenceByUserName.getSelectedItem()!=null&&
															cmbRefrenceByUserName.getSelectedIndex()!=0){
														if(!txtPasswordUser.getText().trim().toString().isEmpty()){
															return true;
														}
														else{
															JOptionPane.showMessageDialog(null,"insert Reference User Password Please");
														}
													}
													else{
														JOptionPane.showMessageDialog(null,"select Reference User Please");
													}
												}
												else{
													JOptionPane.showMessageDialog(null,"select User type Please");
												}
											}
											else{
												JOptionPane.showMessageDialog(null,"insert Mobile Number Please");
											}
										}
										else{
											JOptionPane.showMessageDialog(null,"insert Address Please");
										}
									}
									else{
										JOptionPane.showMessageDialog(null,"insert National Id Please");
									}
								}
								else{
									JOptionPane.showMessageDialog(null,"insert Email Address Please");
								}
							}
							else{
								JOptionPane.showMessageDialog(null,"select Activation Please");
							}
						}
						else{
							JOptionPane.showMessageDialog(null,"insert password Please");
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"insert Designation Please");
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"insert User Name Please");
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"insert Name Please");
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"insert UserId Please");
		}
		return false;
	}
	public boolean checkConfirmation(String caption){
		int a=JOptionPane.showConfirmDialog(null, caption,"confirmation...",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public boolean isExistingUserName(){
		try {
			String sql="select * from tbnewuserinfo where "
					+ "userName='"+txtUserName.getText().trim().toString()+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				JOptionPane.showMessageDialog(null, "User Name Already Exist!","Warning",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return true;
	}
	public boolean checkReferenceUserPassword(){
		try {
			StringTokenizer token=new StringTokenizer(cmbRefrenceByUserName.getSelectedItem().toString(),"#");
			String userId=token.nextToken();
			String sql="select pass from tbnewuserinfo where userId='"+userId+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				if(txtPasswordUser.getText().trim().equals(rs.getString("pass"))){
					return true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Reference Password not matched with"
							+ "Reference User Password","Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}
	public boolean insertData(){
		try {
			String dDateofJoin=new SimpleDateFormat("yyyy-MM-yy").format(dateDateofJoin.getDate());
			StringTokenizer token=new StringTokenizer(cmbRefrenceByUserName.getSelectedItem().toString(),"#");
			String userId=token.nextToken();
			String userName=token.nextToken();
			String sql="insert into tbnewuserinfo(userId,Name,userName,designation,pass,dateofJoin,"
					+ "Activation,EmailAddress,Nationalid,Address,mobile,userType,ReferenceUser,userip,"
					+ "entryTime)"
					+ "values('"+txtUserId.getText().trim().toString()+"',"
					+ "'"+txtName.getText().trim().toString()+"',"
					+ "'"+txtUserName.getText().trim().toString()+"',"
					+ "'"+txtDesignation.getText().trim().toString()+"',"
					+ "'"+txtPass.getText().trim().toString()+"',"
					+ "'"+dDateofJoin+"',"
					+ "'"+cmbActivation.getSelectedItem().toString()+"',"
					+ "'"+txtEmailAddress.getText().trim().toString()+"',"
					+ "'"+txtNationalId.getText().trim().toString()+"',"
					+ "'"+txtAddress.getText().trim().toString()+"',"
					+ "'"+txtMobileNumber.getText().trim().toString()+"',"
					+ "'"+cmbUserType.getSelectedItem().toString()+"',"
					+ "'"+userName+"','',now())";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"insert data from singUp");
		}
		return false;
	}
	public void cmp(){
		setLayout(new BorderLayout());
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
		add(panelSouth,BorderLayout.SOUTH);
		panelSouth();
	}
	public void panelCenter(){
		panelCenter.setBackground(Color.decode("#4682B4"));
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenter.setLayout(grid);
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(0,0,0,0);

		cn.gridx=0;
		cn.gridy=0;
		panelCenter.add(lblUserId,cn);

		cn.gridx=1;
		cn.gridy=0;
		panelCenter.add(txtUserId,cn);
		txtUserId.setEditable(false);

		cn.gridx=0;
		cn.gridy=1;
		panelCenter.add(lblName,cn);

		cn.gridx=1;
		cn.gridy=1;
		panelCenter.add(txtName,cn);

		cn.gridx=0;
		cn.gridy=2;
		panelCenter.add(lblUserName,cn);

		cn.gridx=1;
		cn.gridy=2;
		panelCenter.add(txtUserName,cn);

		cn.gridx=0;
		cn.gridy=3;
		panelCenter.add(lblDesignation,cn);

		cn.gridx=1;
		cn.gridy=3;
		panelCenter.add(txtDesignation,cn);

		cn.gridx=0;
		cn.gridy=4;
		panelCenter.add(lblPassword,cn);

		cn.gridx=1;
		cn.gridy=4;
		panelCenter.add(txtPass,cn);

		cn.gridx=0;
		cn.gridy=5;
		panelCenter.add(lblDateofjoin,cn);

		cn.gridx=1;
		cn.gridy=5;
		panelCenter.add(dateDateofJoin,cn);
		dateDateofJoin.setDate(new Date());
		dateDateofJoin.setDateFormatString("dd-MM-yyyy");
		dateDateofJoin.setOpaque(false);

		cn.gridx=0;
		cn.gridy=6;
		panelCenter.add(lblActivation,cn);

		cn.gridx=1;
		cn.gridy=6;
		panelCenter.add(cmbActivation,cn);
		cmbActivation.addItem("");
		cmbActivation.addItem("Yes");
		cmbActivation.addItem("No");

		cn.gridx=0;
		cn.gridy=7;
		panelCenter.add(lblEmailAddress,cn);

		cn.gridx=1;
		cn.gridy=7;
		panelCenter.add(txtEmailAddress,cn);	

		cn.gridx=0;
		cn.gridy=8;
		panelCenter.add(lblNationalId,cn);

		cn.gridx=1;
		cn.gridy=8;
		panelCenter.add(txtNationalId,cn);

		cn.gridx=0;
		cn.gridy=9;
		panelCenter.add(lblAddress,cn);

		cn.gridx=1;
		cn.gridy=9;
		panelCenter.add(scrollAddress,cn);

		cn.gridx=0;
		cn.gridy=10;
		panelCenter.add(lblMobileNumber,cn);

		cn.gridx=1;
		cn.gridy=10;
		panelCenter.add(txtMobileNumber,cn);

		cn.gridx=0;
		cn.gridy=11;
		panelCenter.add(lblUserType,cn);

		cn.gridx=1;
		cn.gridy=11;
		panelCenter.add(cmbUserType,cn);
		cmbUserType.addItem("");
		cmbUserType.addItem("General");

		cn.gridx=0;
		cn.gridy=12;
		panelCenter.add(lblRefrenceByUserName,cn);

		cn.gridx=1;
		cn.gridy=12;
		panelCenter.add(cmbRefrenceByUserName,cn);

		cn.gridx=0;
		cn.gridy=13;
		panelCenter.add(lblPasswordUser,cn);

		cn.gridx=1;
		cn.gridy=13;
		panelCenter.add(txtPasswordUser,cn);
	}
	public void panelSouth(){
		panelSouth.setPreferredSize(new Dimension(0, 50));
		panelSouth.setBackground(Color.decode("#000080"));
		FlowLayout flow=new FlowLayout();
		panelSouth.setLayout(flow);
		panelSouth.add(btnSignUp);
		btnSignUp.setPreferredSize(new Dimension(100, 35));
		panelSouth.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(100, 35));
		panelSouth.add(btnCencel);
		btnCencel.setPreferredSize(new Dimension(100, 35));	
	}
}

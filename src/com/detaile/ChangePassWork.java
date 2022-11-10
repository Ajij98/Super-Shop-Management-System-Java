package com.detaile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.omg.CORBA.ACTIVITY_COMPLETED;

import com.admin.dbConneciton;

public class ChangePassWork extends JPanel{

	JPanel panelChanegPassNew=new JPanel();

	JPanel panelChanegPassNewNorth=new JPanel();
	JPanel panelChanegPassNewCenter=new JPanel();


	JLabel lblUserName=new JLabel("User Name");
	JLabel lblOldPass=new JLabel("Old Password");
	JLabel lblNewPass=new JLabel("New Password");
	JLabel lblConfirmPass=new JLabel("Confirm Password");
	Font font=new Font("clibari", Font.BOLD, 14);

	JPasswordField txtOldPass=new JPasswordField(25);
	JPasswordField txtNewPass=new JPasswordField();
	JPasswordField txtConfirmPass=new JPasswordField(); 
	JComboBox  cmbUserName=new JComboBox();

	ImageIcon iconChangePass=new ImageIcon("Images/lock.png");
	JButton btnChangePass=new JButton("ChangePass",iconChangePass);
	ImageIcon iconRefresh=new ImageIcon("Images/refresh1.png");
	JButton btnRefresh=new JButton("Refresh",iconRefresh);

	public ChangePassWork() {
		init();
		cmp();
		btnAction();
	}
	public void refreshWork(){
		cmbUserName.setSelectedIndex(0);
		txtOldPass.setText("");
		txtNewPass.setText("");
		txtConfirmPass.setText("");
	}
	public void btnAction(){
		btnChangePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkValidation()){
					if(checkPassword()){
						if(confirmPass()){
							if(checkConfirmation()){
								if(updatePassword()){
									JOptionPane.showMessageDialog(null,"Your password change successfully");
									refreshWork();
								}
							}
						}
					}
				}
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshWork();
			}
		});
	}
	public void cmbUserNameDataLoad(){
		try {
			cmbUserName.removeAllItems();
			cmbUserName.addItem("");
			String sql="select userId,userName from tbnewuserinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbUserName.addItem(rs.getString("userId")+"#"+rs.getString("userName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbUserName data load from change Password");
		}
	}
	public boolean checkValidation(){
		if(cmbUserName.getSelectedItem()!=null && cmbUserName.getSelectedIndex()!=0){
			if(!txtOldPass.getText().trim().toString().isEmpty()){
				if(!txtNewPass.getText().trim().toString().isEmpty()){
					if(!txtConfirmPass.getText().trim().toString().isEmpty()){
						return true;
					}
					else{
						JOptionPane.showMessageDialog(null, "insert confirmPassword");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "insert NewPassword");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "insert OldPassword");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "select UserName");
		}
		return false;
	}
	public boolean checkPassword(){
		try {
			StringTokenizer token=new StringTokenizer(cmbUserName.getSelectedItem().toString().trim(),"#");
			String sql="select pass from tbnewuserinfo where userid like '"+token.nextToken()+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				if(txtOldPass.getText().trim().equals(rs.getString("pass"))){
					return true;
				}
				else{
					JOptionPane.showMessageDialog(null,"Your old password is not correct");
				}
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
		return false;
	}
	public boolean confirmPass(){
		if(txtNewPass.getText().trim().equals(txtConfirmPass.getText().trim())){
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "your confirm password is not matched with New Password");
		}
		return false;
	}
	public boolean checkConfirmation(){
		int a=JOptionPane.showConfirmDialog(null, "Do you want to save?","confirmation",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public boolean updatePassword()
	{
		StringTokenizer token=new StringTokenizer(cmbUserName.getSelectedItem().toString().trim(),"#");
		String sql="update tbnewuserinfo set password='"+txtConfirmPass.getText().trim()+"' where userid "
				+ "like '"+token.nextToken()+"'";
		try {
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}
	public void setOpaqueTrueFalse(boolean b){
		panelChanegPassNew.setOpaque(b);
		panelChanegPassNewCenter.setOpaque(b);
		panelChanegPassNewNorth.setOpaque(b);
	}
	private void init() {
		setPreferredSize(new Dimension(1158,714));	
		TitledBorder titleChangePass=BorderFactory.createTitledBorder("Change Password");
		setBorder(titleChangePass);
		titleChangePass.setTitleJustification(TitledBorder.CENTER);
		titleChangePass.setTitleColor(Color.decode("#8B0000"));
		titleChangePass.setTitleFont(new Font("clibari", Font.BOLD, 22));	
		FlowLayout flow=new FlowLayout();
		setLayout(flow);
		flow.setVgap(160);
	}
	private void cmp() {
		add(panelChanegPassNew);
		panelChanegPassNew();	
	}
	private void panelChanegPassNew() {
		panelChanegPassNew.setPreferredSize(new Dimension(560, 310));
		panelChanegPassNew.setLayout(new BorderLayout());	
		panelChanegPassNew.add(panelChanegPassNewNorth,BorderLayout.NORTH);
		panelChanegPassNewNorth();
		panelChanegPassNew.add(panelChanegPassNewCenter,BorderLayout.CENTER);
		panelChanegPassNewCenter();	
	}
	private void panelChanegPassNewCenter() {
		panelChanegPassNewCenter.setBackground(Color.LIGHT_GRAY);
		FlowLayout flow=new FlowLayout();
		panelChanegPassNewCenter.setLayout(flow);
		flow.setHgap(30);			
		panelChanegPassNewCenter.add(btnChangePass);
		btnChangePass.setPreferredSize(new Dimension(200, 50));
		panelChanegPassNewCenter.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(170, 50));
	}
	private void panelChanegPassNewNorth() {
		panelChanegPassNewNorth.setPreferredSize(new Dimension(0, 220));
		panelChanegPassNewNorth.setBackground(Color.LIGHT_GRAY);	
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelChanegPassNewNorth.setLayout(grid);
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);

		cn.gridx=0;
		cn.gridy=0;		
		panelChanegPassNewNorth.add(lblUserName,cn);
		lblUserName.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		panelChanegPassNewNorth.add(cmbUserName,cn);

		cn.gridx=0;
		cn.gridy=1;
		panelChanegPassNewNorth.add(lblOldPass,cn);
		lblOldPass.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		panelChanegPassNewNorth.add(txtOldPass,cn);

		cn.gridx=0;
		cn.gridy=2;
		panelChanegPassNewNorth.add(lblNewPass,cn);
		lblNewPass.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		panelChanegPassNewNorth.add(txtNewPass,cn);

		cn.gridx=0;
		cn.gridy=3;
		panelChanegPassNewNorth.add(lblConfirmPass,cn);
		lblConfirmPass.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		panelChanegPassNewNorth.add(txtConfirmPass,cn);
	}
}

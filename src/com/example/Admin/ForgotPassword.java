package com.example.Admin;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.admin.dbConneciton;
import com.lowagie.text.Jpeg;
import com.lowagie.text.pdf.codec.JBIG2Image;
import com.mysql.jdbc.log.Log;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;

public class ForgotPassword extends JPanel{
	
	JPanel mainPanel=new JPanel();
	JPanel panelCenter=new JPanel();
	JPanel panelSouth=new JPanel();
	
	JLabel lblUserName=new JLabel("UserName");
	JLabel lblEmail=new JLabel("Emailid");
	JLabel lblNationalId=new JLabel("Nationalid");
	JLabel lblMobileNumber=new JLabel("Mobile number");
	
	JTextField txtUserName=new JTextField(20);
	JTextField txtEmailid=new JTextField();
	JTextField txtNationalid=new JTextField();
	JTextField txtMobileNumber=new JTextField();
	
	JButton btnOk=new JButton("Ok");
	JButton btnCencel=new JButton("Cencel");
	JButton btnRefresh=new JButton("Refresh");
	
	JFrame frame=new JFrame();
	
	public ForgotPassword(JFrame frame){
		this.frame=frame;
		cmp();
		btnAction();
	}
	public void refreshWork(){
		txtUserName.setText("");
		txtEmailid.setText("");
		txtNationalid.setText("");
		txtMobileNumber.setText("");
	}
	public void btnAction(){
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValidation()){
					if(checkUserName()){
						if(checkOtherInfo()){
							JOptionPane.showMessageDialog(null, "All information is correct."+"\n"+"Now you"
									+ " can reset your password.");
							resetPasswordWork();
							refreshWork();
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "incorrect UserName");
					}
				}
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshWork();
			}
		});
		btnCencel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lg=new Login();
				lg.setVisible(true);	
				frame.setVisible(false);
			}
		});
	}
	public void resetPasswordWork(){
		ResetPassword setNewPass=new ResetPassword(frame,txtUserName.getText().toString().trim());
		mainPanel.setVisible(false);
		add(setNewPass);
		setNewPass.setVisible(true);
		setSize(480, 270);
	}
	public boolean checkValidation(){
		if(!txtUserName.getText().trim().toString().isEmpty()){
			if(!txtEmailid.getText().trim().toString().isEmpty()){
				if(!txtNationalid.getText().trim().toString().isEmpty()){
					if(!txtMobileNumber.getText().trim().toString().isEmpty()){
						return true;
					}
					else{
						JOptionPane.showMessageDialog(null, "insert Mobile Number please!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "insert Nationalid please!");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "insert Emailid please!");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "insert username please!");
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
			JOptionPane.showMessageDialog(null, e+"checkUserName()");
		}
		return false;
	}
	public boolean checkOtherInfo(){
		try {
			String sql="select EmailAddress,Nationalid,mobile from tbnewuserinfo where userName "
					+ "like '"+txtUserName.getText().toString().trim()+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				if(txtEmailid.getText().toString().trim().equals(rs.getString("EmailAddress"))){
					if(txtNationalid.getText().toString().trim().equals(rs.getString("Nationalid"))){
						if(txtMobileNumber.getText().toString().trim().equals(rs.getString("mobile"))){
							return true;
						}
						else{
							JOptionPane.showMessageDialog(null, "incorrect Mobile Number");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "incorrect NationalID");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "incorrect EmailID");
				}
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"checkOtherInfo()");
		}
		return false;
	}
	public void cmp(){
		BorderLayout border=new BorderLayout();
		setLayout(border);
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panelCenter, BorderLayout.CENTER);
		panelCenter();
		mainPanel.add(panelSouth, BorderLayout.SOUTH);	
		panelSouth();
	}
	public void panelCenter(){
		panelCenter.setBackground(Color.decode("#104E8B")); 
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenter.setLayout(grid);
		
		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(lblUserName, cn);
		lblUserName.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblUserName.setForeground(Color.WHITE);
		
		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(txtUserName, cn);
		
		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(lblEmail, cn);
		lblEmail.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblEmail.setForeground(Color.WHITE);
		
		cn.gridx=1;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(txtEmailid, cn);
		
		cn.gridx=0;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(lblNationalId, cn);
		lblNationalId.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblNationalId.setForeground(Color.WHITE);
		
		cn.gridx=1;
		cn.gridy=2;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(txtNationalid, cn);
		
		cn.gridx=0;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(lblMobileNumber, cn);
		lblMobileNumber.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblMobileNumber.setForeground(Color.WHITE);
		
		cn.gridx=1;
		cn.gridy=3;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(txtMobileNumber, cn);
	}
	public void panelSouth(){
		panelSouth.setPreferredSize(new Dimension(0, 50));
		panelSouth.setBackground(Color.BLACK);
		FlowLayout flow=new FlowLayout();
		panelSouth.setLayout(flow);
		
		panelSouth.setBackground(Color.decode("#104E8B"));  
		panelSouth.add(btnOk);
		panelSouth.add(btnCencel);
		panelSouth.add(btnRefresh);
		btnOk.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnOk.setPreferredSize(new Dimension(110, 35));
		btnOk.setBackground(Color.decode("#58C8C5"));
		btnCencel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnCencel.setPreferredSize(new Dimension(110, 35));
		btnCencel.setBackground(Color.decode("#58C8C5"));
		btnRefresh.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnRefresh.setPreferredSize(new Dimension(110, 35));
		btnRefresh.setBackground(Color.decode("#58C8C5"));
	}
}













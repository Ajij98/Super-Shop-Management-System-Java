package com.detaile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.ref.Reference;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.admin.dbConneciton;
import com.example.Admin.SessionBean;
import com.example.Admin.SuggestText;
import com.toedter.calendar.JDateChooser;

public class NewUserWork extends JPanel{

	JPanel panelCenter=new JPanel();
	JPanel panelWest=new JPanel();
	JPanel panelWestNorth=new JPanel();
	JPanel panelWestCenter=new JPanel();
	JPanel panelWestSouth=new JPanel();
	JPanel panelWestCenterCenter=new JPanel();
	JPanel panelWestCenterEast=new JPanel();
	SuggestText cmbSearch=new SuggestText();
	JLabel lblSearchIcon=new JLabel(new ImageIcon("Images/CenterImage.png"));

	ImageIcon iconAdd=new ImageIcon("Images/add1.png");
	JButton btnAdd=new JButton("Add",iconAdd);
	ImageIcon iconEdit=new ImageIcon("Images/btnEdit.png");
	JButton btnEdit=new JButton("Edit",iconEdit);
	ImageIcon iconRefresh=new ImageIcon("Images/btnRefresh.png");
	JButton btnRefresh=new JButton("Refresh",iconRefresh);
	ImageIcon iconDelete=new ImageIcon("Images/cancel-icon.png");
	JButton btnDelete=new JButton("Delete",iconDelete);
	ImageIcon iconReport=new ImageIcon("Images/Report.png");
	JButton btnReport=new JButton("Report",iconReport);	

	JLabel lblBordre=new JLabel();
	ImageIcon iconUpload=new ImageIcon("Images/upload22.png");
	JButton btnUpload=new JButton("Upload",iconUpload);

	JLabel lblUserID=new JLabel("UserId");
	JLabel lblName=new JLabel("Name");
	JLabel lblUserName=new JLabel("UserName");
	JLabel lblDesignation=new JLabel("Designation");
	JLabel lblPass=new JLabel("Password");
	JLabel lblDateOfJoin=new JLabel("DateofJoin");
	JLabel lblActivation=new JLabel("Activation");
	JLabel lblEmailAddress=new JLabel("Email Address");
	JLabel lblNationalId=new JLabel("NationalId");
	JLabel lblAddress=new JLabel("Address");
	JLabel lblMobile=new JLabel("Mobile");
	JLabel lblUserType=new JLabel("UserType");
	JLabel lblExecutive=new JLabel("Executive");
	JLabel lblReferenceByUserName=new JLabel("ReferenceByUserName");
	Font font=new Font("clibari", Font.BOLD, 12);

	JTextField txtUserId=new JTextField(18);
	JTextField txtName=new JTextField();
	JTextField txtUserName=new JTextField();
	JTextField txtDesignation=new JTextField();
	JPasswordField txtPass=new JPasswordField();
	JDateChooser dDateOfjoin=new JDateChooser();
	JComboBox  cmbActivition=new JComboBox();
	JTextField txtEmailAddress=new JTextField();
	JTextField txtNationalId=new JTextField();
	JTextArea txtAddress=new JTextArea(3,5);
	JTextField txtMobileNumber=new JTextField();
	JComboBox cmbUserType=new JComboBox();
	JTextField txtReferenceByUserName=new JTextField();
	JScrollPane scroll=new JScrollPane(txtAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String col[]={"User ID","User Name","Activation","Email"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row, col)
	{
		public boolean isCellEditable(int row,int col){
			return false;
		}
	};
	JTable table=new JTable(model);
	JScrollPane scrollTable=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	ImageIcon iconImage=new ImageIcon("Images/printer1.png");
	JButton btnPrint=new JButton("print",iconImage);

	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

	SessionBean sessionBean=new SessionBean();
	boolean isUpdate=false;

	JFileChooser fileChooser;

	public NewUserWork(SessionBean sessionBean) {
		this.sessionBean=sessionBean;
		txtReferenceByUserName.setText(sessionBean.getUserName());
		lblExecutive.setText(sessionBean.getUserType());
		init();
		cmp();
		btnAction();
		btnIni(true);
		editable(true);
	}
	public void setOpaqueTrueFalse(boolean b){
		panelWest.setOpaque(b);
		panelWestCenter.setOpaque(b);
		panelWestCenterCenter.setOpaque(b);
		panelWestCenterEast.setOpaque(b);
		panelWestNorth.setOpaque(b);
		panelWestSouth.setOpaque(b);
		panelCenter.setOpaque(b);
	}
	public void btnIni(boolean b){
		btnAdd.setEnabled(b);
		btnEdit.setEnabled(!b);
		btnUpload.setEnabled(b);
	}
	public void editable(boolean b){
		txtName.setEditable(b);
		txtDesignation.setEditable(b);
		txtPass.setEditable(b);
		dDateOfjoin.setEnabled(b);
		cmbActivition.setEnabled(b);
		txtEmailAddress.setEditable(b);
		txtNationalId.setEditable(b);
		txtAddress.setEditable(b);
		txtMobileNumber.setEditable(b);
		cmbUserType.setEnabled(b);
	}
	public void txtClear(){
		txtName.setText("");
		txtUserName.setText("");
		txtDesignation.setText("");
		txtPass.setText("");
		dDateOfjoin.setDate(new Date());
		cmbActivition.setSelectedIndex(0);
		txtEmailAddress.setText("");
		txtNationalId.setText("");
		txtAddress.setText("");
		txtMobileNumber.setText("+88");
		cmbUserType.setSelectedIndex(0);
		//txtReferenceByUserName.setText(sessionBean.getUserName());
	}
	public void refreshWork(){
		autoId();
		tableDataLoad();
		cmbSearchDataLoad();
		txtClear();
		btnIni(true);
		editable(true);
		isUpdate=false;
	}
	public void btnAction(){
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkValidation()){
					if(isUpdate){
						if(checkConfirmation("Are you sure you want to update?")){
							if(deleteData()){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "data updated successfully");
									refreshWork();
								}
							}
						}
					}
					else{
						if(isExistUserName()){
							if(checkConfirmation("do you want to insert?")){
								if(insertData()){
									JOptionPane.showMessageDialog(null, "data inserted successfully");
									refreshWork();
								}
							}
						}
					}
				}
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnIni(true);
				editable(true);
				isUpdate=true;
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshWork();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkValidation()){
					if(checkConfirmation("do you want to delete?")){
						if(deleteData()){
							JOptionPane.showMessageDialog(null, "data deleted successfuly");
							refreshWork();
						}
					}
				}
			}
		});
		table.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
				String id=model.getValueAt(table.getSelectedRow(), 0).toString();
				searchDataLoad(id);
				btnIni(false);
				editable(false);
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		table.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
				String id=model.getValueAt(table.getSelectedRow(), 0).toString();
				searchDataLoad(id);
				btnIni(false);
				editable(false);
			}
			public void keyPressed(KeyEvent arg0) {
			}
		});
		cmbSearch.cmbSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!cmbSearch.txtSuggest.getText().trim().isEmpty()){
					StringTokenizer token=new StringTokenizer(cmbSearch.txtSuggest.getText().trim(), "#");
					String id=token.nextToken();
					searchDataLoad(id);
					btnIni(false);
					editable(false);
				}
				else{
					refreshWork();
				}
			}
		});
		txtMobileNumber.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char ch=e.getKeyChar();
				if(!(Character.isDigit(ch)) || (ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE)){
					getToolkit().beep();
					e.consume();
				}
			}
			public void keyReleased(KeyEvent e) {
				if(txtMobileNumber.getText().trim().toString().length()<3){
					txtMobileNumber.setText("+88");
				}
			}
			public void keyPressed(KeyEvent e) {
			}
		});
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadAction();
			}
		});
	}
	public void uploadAction(){
		fileChooser=new JFileChooser();
		fileChooser.setDialogTitle("Image selection");
		fileChooser.showOpenDialog(this);
		FileNameExtensionFilter filter=new FileNameExtensionFilter("image","jpg","PNG","gif","jpeg");
		fileChooser.setFileFilter(filter);
	}
	public void autoId(){
		try {
			String sql="select ifnull(max(cast(SUBSTRING(userId,LOCATE('-',userId)+1,"
					+ "LENGTH(userId)-LOCATE('-',userId)) as UNSIGNED)),0)+1 as id from tbnewuserinfo ";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			if(rs.next()){
				txtUserId.setText("userId-"+rs.getString("id"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"autoid from NewUser");
		}
	}
	public boolean checkValidation(){
		if(!txtName.getText().trim().isEmpty()){
			if(!txtDesignation.getText().trim().isEmpty()){
				if(!txtPass.getText().trim().isEmpty()){
					if(cmbActivition.getSelectedItem()!=null&&cmbActivition.getSelectedIndex()!=0){
						if(!txtEmailAddress.getText().trim().isEmpty()){
							if(!txtNationalId.getText().trim().isEmpty()){
								if(!txtAddress.getText().trim().isEmpty()){
									if(!txtMobileNumber.getText().trim().isEmpty()){
										if(cmbUserType.getSelectedItem()!=null&&cmbUserType.getSelectedIndex()!=0){
											if(checkPassword()){
												if(checkEmail()){
													if(checkMobileNumber()){
														return true;
													}
												}
											}
										}
										else{
											JOptionPane.showMessageDialog(null, "please select userType");
										}
									}
									else{
										JOptionPane.showMessageDialog(null, "insert mobile Number");
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "insert Address");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "insert National Id");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "insert Email Address");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "please select Activation");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "insert Password");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "insert Designation");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "insert Name");
		}
		return false;
	}
	public boolean isExistUserName(){
		try {
			String sql="select * from tbnewuserinfo where "
					+ "userName='"+txtUserName.getText().trim().toString()+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				JOptionPane.showMessageDialog(null, "This User Name Already Exist!","Warning...",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return true;
	}
	public boolean checkEmail(){
		String email_format="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
				+ "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern email_pattern=Pattern.compile(email_format);
		Matcher email_match=email_pattern.matcher(txtEmailAddress.getText().trim());
		if(!email_match.matches()){
			JOptionPane.showMessageDialog(null, "Email Format is not correct."
					+ "please write Mail Address like this format (abcde12@gmail.com)","Error",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
	public boolean checkMobileNumber(){
		String number_format="^[+0-9]{14}$";
		Pattern number_pattern=Pattern.compile(number_format);
		Matcher number_matcher=number_pattern.matcher(txtMobileNumber.getText().trim());
		if(!number_matcher.matches()){
			JOptionPane.showMessageDialog(null, "Mobile Number is not valid...\n"
					+ "Must contain 11 digit,\n"
					+ "Not more than 11 digit,\n"
					+ "Not less than 11 digit","Error",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
	public boolean checkPassword(){
		String password_format="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$";
		Pattern password_pattern=Pattern.compile(password_format);
		Matcher password_match=password_pattern.matcher(txtPass.getText().trim());

		if(!password_match.matches()){
			JOptionPane.showMessageDialog(null, "Password not valid..must contains one digit from (0-9)\n"
					+ "must contains at least one lowercase character from(a-z)\n"
					+ "must contains  at least one uppercase character from(A-Z)\n"
					+ "must contains at least one special symbol in this list [@#$%^&+=]\n"
					+ "please write at least 8 character,maximum 20 character","Error",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
	public boolean checkConfirmation(String caption){
		int a=JOptionPane.showConfirmDialog(null, caption,"confirmation",JOptionPane.YES_NO_OPTION);
		if(a==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	public boolean insertData(){
		try {
			String dateofjoin=new SimpleDateFormat("yyyy-MM-dd").format(dDateOfjoin.getDate());
			String sql="insert into tbnewuserinfo(userId,Name,userName,designation,pass,dateofJoin,"
					+ "Activation,EmailAddress,Nationalid,Address,mobile,userType,ReferenceUser,userip,entryTime)"
					+ "values('"+txtUserId.getText().trim().toString()+"',"
					+ "'"+txtName.getText().trim().toString()+"',"
					+ "'"+txtUserName.getText().trim().toString()+"',"
					+ "'"+txtDesignation.getText().trim().toString()+"',"
					+ "'"+txtPass.getText().trim().toString()+"',"
					+ "'"+dateofjoin+"',"
					+ "'"+cmbActivition.getSelectedItem().toString()+"',"
					+ "'"+txtEmailAddress.getText().trim().toString()+"',"
					+ "'"+txtNationalId.getText().trim().toString()+"',"
					+ "'"+txtAddress.getText().trim().toString()+"',"
					+ "'"+txtMobileNumber.getText().trim().toString()+"',"
					+ "'"+cmbUserType.getSelectedItem().toString()+"',"
					+ "'"+txtReferenceByUserName.getText().trim().toString()+"','',now())";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"insertData from NewUser");
		}
		return false;
	}
	public void tableDataLoad(){
		try {
			for(int i=model.getRowCount()-1; i>=0; i--){
				model.removeRow(i);
			}
			String sql="select userId,userName,Activation,EmailAddress from tbnewuserinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("userId"),rs.getString("userName"),rs.getString("Activation"),
						rs.getString("EmailAddress")});
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"tableData Load New User");
		}
	}
	public void cmbSearchDataLoad(){
		try {
			cmbSearch.v.clear();
			cmbSearch.v.add("");
			String sql="select userId,userName from tbnewuserinfo";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				cmbSearch.v.add(rs.getString("userId")+"#"+rs.getString("userName"));
			}
			dbConneciton.con.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"cmbSearch Data load from New User");
		}
	}
	public void searchDataLoad(String userId){
		try {
			String sql="select userId,Name,userName,designation,pass,dateofJoin,Activation,"
					+ "EmailAddress,Nationalid,Address,mobile,userType,ReferenceUser from "
					+ "tbnewuserinfo where userId like '"+userId+"'";
			dbConneciton.connection();
			ResultSet rs=dbConneciton.sta.executeQuery(sql);
			while(rs.next()){
				txtUserId.setText(rs.getString("userId"));
				txtName.setText(rs.getString("Name"));
				txtUserName.setText(rs.getString("userName"));
				txtDesignation.setText(rs.getString("designation"));
				txtPass.setText(rs.getString("pass"));
				dDateOfjoin.setDate(rs.getDate("dateofJoin"));
				cmbActivition.setSelectedItem(rs.getString("Activation"));
				txtEmailAddress.setText(rs.getString("EmailAddress"));
				txtNationalId.setText(rs.getString("Nationalid"));
				txtAddress.setText(rs.getString("Address"));
				txtMobileNumber.setText(rs.getString("mobile"));
				cmbUserType.setSelectedItem(rs.getString("userType"));
				txtReferenceByUserName.setText("ReferenceUser");
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"search Data load from New User");
		}
	}
	public boolean deleteData(){
		try {
			String sql="delete from tbnewuserinfo where userId like "
					+ "'"+txtUserId.getText().trim().toString()+"'";
			dbConneciton.connection();
			dbConneciton.sta.executeUpdate(sql);
			dbConneciton.con.close();
			return true;
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e+"delete Data from New User");
		}
		return false;
	}
	private void init() {
		setPreferredSize(new Dimension(1158,714));		
		TitledBorder titleNewUser=BorderFactory.createTitledBorder("New User");
		setBorder(titleNewUser);
		titleNewUser.setTitleJustification(TitledBorder.CENTER);
		titleNewUser.setTitleColor(Color.decode("#8B0000"));
		titleNewUser.setTitleFont(new Font("clibari", Font.BOLD, 22));		
		BorderLayout border=new BorderLayout();
		setLayout(border);
		border.setHgap(0);
	}
	private void cmp() {
		add(panelWest,BorderLayout.WEST);
		panelWest();
		add(panelCenter,BorderLayout.CENTER);
		panelCenter();
	}
	private void panelCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelCenter.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(1, 1, 1, 1);
		panelCenter.add(scrollTable, cn);
		scrollTable.setPreferredSize(new Dimension(515, 587));
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(table.getRowHeight()+9);

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelCenter.add(btnPrint, cn);
		btnPrint.setPreferredSize(new Dimension(0, 60));
	}
	private void panelWest() {
		panelWest.setPreferredSize(new Dimension(610, 725));
		panelWest.setLayout(new BorderLayout());
		panelWest.add(panelWestNorth,BorderLayout.NORTH);
		panelWestNorth();
		panelWest.add(panelWestCenter, BorderLayout.CENTER);
		panelWestCenter();
		panelWest.add(panelWestSouth, BorderLayout.SOUTH);
		panelWestSouth();
	}
	private void panelWestNorth() {
		panelWestNorth.setPreferredSize(new Dimension(0, 100));
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestNorth.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(15, 5, 5, 5);
		panelWestNorth.add(cmbSearch.cmbSuggest, cn);
		cmbSearch.cmbSuggest.setPreferredSize(new Dimension(350, 35));

		cn.gridx=1;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(15, 5, 5, 5);
		panelWestNorth.add(lblSearchIcon, cn);
	}
	private void panelWestCenter() {
		panelWestCenter.setLayout(new BorderLayout());
		panelWestCenter.add(panelWestCenterCenter,BorderLayout.CENTER);
		panelWestCenterCenter();
		panelWestCenter.add(panelWestCenterEast, BorderLayout.EAST);
		panelWestCenterEast();
	}
	private void panelWestCenterEast() {
		panelWestCenterEast.setPreferredSize(new Dimension(180, 0));
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestCenterEast.setLayout(grid);

		cn.gridx=0;
		cn.gridy=0;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 5, 5, 5);
		panelWestCenterEast.add(lblBordre,cn);
		lblBordre.setPreferredSize(new Dimension(142, 165));
		lblBordre.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		cn.gridx=0;
		cn.gridy=1;
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(5, 20, 201, 20);
		panelWestCenterEast.add(btnUpload,cn);
		btnUpload.setPreferredSize(new Dimension(80, 35)); 
	}
	private void panelWestCenterCenter() {
		GridBagConstraints cn=new GridBagConstraints();
		GridBagLayout grid=new GridBagLayout();
		panelWestCenterCenter.setLayout(grid);
		cn.fill=GridBagConstraints.BOTH;
		cn.insets=new Insets(2, 5, 2, 5);

		cn.gridx=0;
		cn.gridy=0;
		panelWestCenterCenter.add(lblUserID,cn);
		lblUserID.setFont(font);

		cn.gridx=1;
		cn.gridy=0;
		panelWestCenterCenter.add(txtUserId,cn);
		txtUserId.setEditable(false);

		cn.gridx=0;
		cn.gridy=1;
		panelWestCenterCenter.add(lblName,cn);
		lblName.setFont(font);

		cn.gridx=1;
		cn.gridy=1;
		panelWestCenterCenter.add(txtName,cn);

		cn.gridx=0;
		cn.gridy=2;
		panelWestCenterCenter.add(lblUserName,cn);
		lblUserName.setFont(font);

		cn.gridx=1;
		cn.gridy=2;
		panelWestCenterCenter.add(txtUserName,cn);

		cn.gridx=0;
		cn.gridy=3;
		panelWestCenterCenter.add(lblDesignation,cn);
		lblDesignation.setFont(font);

		cn.gridx=1;
		cn.gridy=3;
		panelWestCenterCenter.add(txtDesignation,cn);

		cn.gridx=0;
		cn.gridy=4;
		panelWestCenterCenter.add(lblPass,cn);
		lblPass.setFont(font);

		cn.gridx=1;
		cn.gridy=4;
		panelWestCenterCenter.add(txtPass,cn);

		cn.gridx=0;
		cn.gridy=5;
		panelWestCenterCenter.add(lblDateOfJoin,cn);
		lblDateOfJoin.setFont(font);

		cn.gridx=1;
		cn.gridy=5;
		panelWestCenterCenter.add(dDateOfjoin,cn);
		dDateOfjoin.setDateFormatString("dd-MM-yyyy");
		dDateOfjoin.setDate(new Date());

		cn.gridx=0;
		cn.gridy=6;
		panelWestCenterCenter.add(lblActivation,cn);
		lblActivation.setFont(font);

		cn.gridx=1;
		cn.gridy=6;
		panelWestCenterCenter.add(cmbActivition,cn);
		cmbActivition.addItem("");
		cmbActivition.addItem("Yes");
		cmbActivition.addItem("No");

		cn.gridx=0;
		cn.gridy=7;
		panelWestCenterCenter.add(lblEmailAddress,cn);
		lblEmailAddress.setFont(font);

		cn.gridx=1;
		cn.gridy=7;
		panelWestCenterCenter.add(txtEmailAddress,cn);

		cn.gridx=0;
		cn.gridy=8;
		panelWestCenterCenter.add(lblNationalId,cn);
		lblNationalId.setFont(font);

		cn.gridx=1;
		cn.gridy=8;
		panelWestCenterCenter.add(txtNationalId,cn);

		cn.gridx=0;
		cn.gridy=9;
		panelWestCenterCenter.add(lblAddress,cn);
		lblAddress.setFont(font);

		cn.gridx=1;
		cn.gridy=9;
		panelWestCenterCenter.add(scroll,cn);

		cn.gridx=0;
		cn.gridy=10;
		panelWestCenterCenter.add(lblMobile,cn);
		lblMobile.setFont(font);

		cn.gridx=1;
		cn.gridy=10;
		panelWestCenterCenter.add(txtMobileNumber,cn);
		txtMobileNumber.setText("+88");

		cn.gridx=0;
		cn.gridy=11;
		panelWestCenterCenter.add(lblUserType,cn);
		lblUserType.setFont(font);

		cn.gridx=1;
		cn.gridy=11;
		panelWestCenterCenter.add(cmbUserType,cn);
		cmbUserType.addItem("");
		cmbUserType.addItem("Admin");
		cmbUserType.addItem("Super Admin");
		cmbUserType.addItem("Executive");
		cmbUserType.addItem("General");

		cn.gridx=0;
		cn.gridy=12;
		panelWestCenterCenter.add(lblReferenceByUserName, cn);
		lblReferenceByUserName.setFont(font);

		cn.gridx=1;
		cn.gridy=12;
		panelWestCenterCenter.add(txtReferenceByUserName, cn);
		txtReferenceByUserName.setEditable(false);

		cn.gridx=2;
		cn.gridy=12;
		panelWestCenterCenter.add(lblExecutive,cn);

	}
	private void panelWestSouth() {
		panelWestSouth.setPreferredSize(new Dimension(0, 80));
		FlowLayout flow=new FlowLayout();
		panelWestSouth.setLayout(flow);
		flow.setHgap(15);
		flow.setVgap(30);

		panelWestSouth.add(btnAdd);
		btnAdd.setPreferredSize(new Dimension(100, 35));
		panelWestSouth.add(btnEdit);
		btnEdit.setPreferredSize(new Dimension(100, 35));
		panelWestSouth.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(100, 35));
		panelWestSouth.add(btnDelete);
		btnDelete.setPreferredSize(new Dimension(100, 35));
		panelWestSouth.add(btnReport);
		btnReport.setPreferredSize(new Dimension(100, 35));
	}

}
